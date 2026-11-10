package codechicken.lib.raytracer;

import codechicken.lib.vec.Cuboid6;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.concurrent.TimeUnit;

/**
 * Created by covers1624 on 3/10/20.
 */
public class VoxelShapeCache {

    // Size bounds (https://github.com/TheCBProject/CodeChickenLib/issues/473). Callers are meant to pass cached
    // shapes, but a caller that builds a fresh VoxelShape per query (identity equality) misses every time, and
    // multipart tiles merge one set per distinct part combination on every shape query. With only an access expiry
    // each such key was held for 2 hours, so a busy world grew these caches without limit. Single-box shapes are
    // small, so their caches get a generous bound; merged shapes are larger and recomputing one is cheap.
    private static final int BOX_CACHE_SIZE = 16384;
    private static final int MERGE_CACHE_SIZE = 4096;

    private static final Cache<AABB, VoxelShape> bbToShapeCache = CacheBuilder.newBuilder()
            .maximumSize(BOX_CACHE_SIZE)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .build();
    private static final Cache<Cuboid6, VoxelShape> cuboidToShapeCache = CacheBuilder.newBuilder()
            .maximumSize(BOX_CACHE_SIZE)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .build();

    private static final Cache<ImmutableSet<VoxelShape>, VoxelShape> mergeShapeCache = CacheBuilder.newBuilder()
            .maximumSize(MERGE_CACHE_SIZE)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .build();

    public static VoxelShape getShape(AABB aabb) {
        VoxelShape shape = bbToShapeCache.getIfPresent(aabb);
        if (shape == null) {
            shape = Shapes.create(aabb);
            bbToShapeCache.put(aabb, shape);
        }
        return shape;
    }

    public static VoxelShape getShape(Cuboid6 cuboid) {
        VoxelShape shape = cuboidToShapeCache.getIfPresent(cuboid);
        if (shape == null) {
            shape = Shapes.box(cuboid.min.x, cuboid.min.y, cuboid.min.z, cuboid.max.x, cuboid.max.y, cuboid.max.z);
            cuboidToShapeCache.put(cuboid, shape);
        }
        return shape;
    }

    public static VoxelShape merge(ImmutableSet<VoxelShape> shapes) {
        VoxelShape shape = mergeShapeCache.getIfPresent(shapes);
        if (shape == null) {
            shape = shapes.stream().reduce(Shapes.empty(), Shapes::or);
            mergeShapeCache.put(shapes, shape);
        }
        return shape;
    }

}

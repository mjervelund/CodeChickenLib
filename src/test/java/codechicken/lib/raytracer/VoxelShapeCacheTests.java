package codechicken.lib.raytracer;

import codechicken.lib.vec.Cuboid6;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every distinct key used to be held for 2 hours with no size limit. Callers that build a fresh
 * {@link VoxelShape} per query (VoxelShape has identity equality) miss every time, so the merge
 * cache grew without bound (https://github.com/TheCBProject/CodeChickenLib/issues/473).
 */
public class VoxelShapeCacheTests {

    // More distinct keys than any cache may hold.
    private static final int INSERTS = 40_000;

    @Test
    public void testMergeCacheIsBounded() throws Exception {
        VoxelShape base = Shapes.box(0, 0, 0, 1, 0.125, 1);
        for (int i = 0; i < INSERTS; i++) {
            // A fresh shape each time, like a part that builds its shape on demand.
            VoxelShape fresh = Shapes.box(0.25, 0.125, 0.25, 0.75, 0.25 + (i % 7) / 10D, 0.75);
            VoxelShapeCache.merge(ImmutableSet.of(base, fresh));
        }
        assertBounded("mergeShapeCache");
    }

    @Test
    public void testBoxCachesAreBounded() throws Exception {
        for (int i = 0; i < INSERTS; i++) {
            double d = i / (double) INSERTS;
            VoxelShapeCache.getShape(new AABB(0, 0, 0, 0.5 + d / 4, 1, 1));
            VoxelShapeCache.getShape(new Cuboid6(0, 0, 0, 1, 0.5 + d / 4, 1));
        }
        assertBounded("bbToShapeCache");
        assertBounded("cuboidToShapeCache");
    }

    @Test
    public void testMergeStillCaches() {
        VoxelShape a = Shapes.box(0, 0, 0, 0.5, 0.5, 0.5);
        VoxelShape b = Shapes.box(0.5, 0.5, 0.5, 1, 1, 1);
        VoxelShape first = VoxelShapeCache.merge(ImmutableSet.of(a, b));
        assertSame(first, VoxelShapeCache.merge(ImmutableSet.of(a, b)));
        AABB bb = new AABB(0, 0, 0, 0.3, 0.3, 0.3);
        assertSame(VoxelShapeCache.getShape(bb), VoxelShapeCache.getShape(new AABB(0, 0, 0, 0.3, 0.3, 0.3)));
    }

    private static void assertBounded(String field) throws Exception {
        Field f = VoxelShapeCache.class.getDeclaredField(field);
        f.setAccessible(true);
        Cache<?, ?> cache = (Cache<?, ?>) f.get(null);
        cache.cleanUp();
        long size = cache.size();
        System.out.println("[VoxelShapeCacheTests] " + field + " size after " + INSERTS + " distinct inserts: " + size);
        assertTrue(size < INSERTS / 2, field + " holds " + size + " entries after " + INSERTS + " distinct inserts (unbounded)");
    }
}

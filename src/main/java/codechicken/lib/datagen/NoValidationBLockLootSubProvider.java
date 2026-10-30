package codechicken.lib.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

/**
 * A {@link BlockLootSubProvider} that does not require a loot table for every known block.
 * <p>
 * Created by covers1624 on 16/9/24.
 */
public abstract class NoValidationBLockLootSubProvider extends BlockLootSubProvider {

    protected NoValidationBLockLootSubProvider(LootTableSubProvider.Context output) {
        this(Set.of(), output);
    }

    protected NoValidationBLockLootSubProvider(Set<Item> explosionResistant, LootTableSubProvider.Context output) {
        this(explosionResistant, FeatureFlagSet.of(), output);
    }

    protected NoValidationBLockLootSubProvider(Set<Item> explosionResistant, FeatureFlagSet flags, LootTableSubProvider.Context output) {
        super(explosionResistant, flags, output);
    }

    // Since 26.3 the tables go straight to the bootstrap context, so run() only generates.
    @Override
    public void run() {
        generate();
    }

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        output.accept(block.getLootTable().orElseThrow(() -> new IllegalStateException("Block " + block + " does not have loot table")), builder);
    }
}

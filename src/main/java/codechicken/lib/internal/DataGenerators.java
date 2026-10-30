package codechicken.lib.internal;

import net.minecraft.world.item.DyeColor;
import codechicken.lib.datagen.LanguageProvider;
import codechicken.lib.util.CCLTags;
import net.covers1624.quack.util.CrashLock;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static codechicken.lib.CodeChickenLib.MOD_ID;

/**
 * Created by covers1624 on 13/12/20.
 */
public class DataGenerators {

    private static final CrashLock LOCK = new CrashLock("Already Initialized");

    public static void init(IEventBus modBus) {
        LOCK.lock();
        if (FMLEnvironment.getDist().isClient()) {
            modBus.addListener(DataGenerators::gatherDataGenerators);
        }
    }

    private static void gatherDataGenerators(GatherDataEvent.Client event) {
        event.createProvider(BlockTags::new);
        event.createProvider(ItemTags::new);
        event.createProvider(LangUS::new);
    }

    private static class BlockTags extends BlockTagsProvider {

        public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            tag(CCLTags.Blocks.WOOLS)
                    .addTag(CCLTags.Blocks.WOOLS_WHITE)
                    .addTag(CCLTags.Blocks.WOOLS_ORANGE)
                    .addTag(CCLTags.Blocks.WOOLS_MAGENTA)
                    .addTag(CCLTags.Blocks.WOOLS_LIGHT_BLUE)
                    .addTag(CCLTags.Blocks.WOOLS_YELLOW)
                    .addTag(CCLTags.Blocks.WOOLS_LIME)
                    .addTag(CCLTags.Blocks.WOOLS_PINK)
                    .addTag(CCLTags.Blocks.WOOLS_GRAY)
                    .addTag(CCLTags.Blocks.WOOLS_LIGHT_GRAY)
                    .addTag(CCLTags.Blocks.WOOLS_CYAN)
                    .addTag(CCLTags.Blocks.WOOLS_PURPLE)
                    .addTag(CCLTags.Blocks.WOOLS_BLUE)
                    .addTag(CCLTags.Blocks.WOOLS_BROWN)
                    .addTag(CCLTags.Blocks.WOOLS_GREEN)
                    .addTag(CCLTags.Blocks.WOOLS_RED)
                    .addTag(CCLTags.Blocks.WOOLS_BLACK);
            tag(CCLTags.Blocks.WOOLS_WHITE).add(Blocks.WOOL.pick(DyeColor.WHITE).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_ORANGE).add(Blocks.WOOL.pick(DyeColor.ORANGE).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_MAGENTA).add(Blocks.WOOL.pick(DyeColor.MAGENTA).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_LIGHT_BLUE).add(Blocks.WOOL.pick(DyeColor.LIGHT_BLUE).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_YELLOW).add(Blocks.WOOL.pick(DyeColor.YELLOW).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_LIME).add(Blocks.WOOL.pick(DyeColor.LIME).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_PINK).add(Blocks.WOOL.pick(DyeColor.PINK).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_GRAY).add(Blocks.WOOL.pick(DyeColor.GRAY).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_LIGHT_GRAY).add(Blocks.WOOL.pick(DyeColor.LIGHT_GRAY).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_CYAN).add(Blocks.WOOL.pick(DyeColor.CYAN).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_PURPLE).add(Blocks.WOOL.pick(DyeColor.PURPLE).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_BLUE).add(Blocks.WOOL.pick(DyeColor.BLUE).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_BROWN).add(Blocks.WOOL.pick(DyeColor.BROWN).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_GREEN).add(Blocks.WOOL.pick(DyeColor.GREEN).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_RED).add(Blocks.WOOL.pick(DyeColor.RED).builtInRegistryHolder().key());
            tag(CCLTags.Blocks.WOOLS_BLACK).add(Blocks.WOOL.pick(DyeColor.BLACK).builtInRegistryHolder().key());
        }

        @Override
        public String getName() {
            return "CodeChickenLib Block tags.";
        }
    }

    private static class ItemTags extends ItemTagsProvider {

        public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider, MOD_ID);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            tag(CCLTags.Items.WOOLS)
                    .addTag(CCLTags.Items.WOOLS_WHITE)
                    .addTag(CCLTags.Items.WOOLS_ORANGE)
                    .addTag(CCLTags.Items.WOOLS_MAGENTA)
                    .addTag(CCLTags.Items.WOOLS_LIGHT_BLUE)
                    .addTag(CCLTags.Items.WOOLS_YELLOW)
                    .addTag(CCLTags.Items.WOOLS_LIME)
                    .addTag(CCLTags.Items.WOOLS_PINK)
                    .addTag(CCLTags.Items.WOOLS_GRAY)
                    .addTag(CCLTags.Items.WOOLS_LIGHT_GRAY)
                    .addTag(CCLTags.Items.WOOLS_CYAN)
                    .addTag(CCLTags.Items.WOOLS_PURPLE)
                    .addTag(CCLTags.Items.WOOLS_BLUE)
                    .addTag(CCLTags.Items.WOOLS_BROWN)
                    .addTag(CCLTags.Items.WOOLS_GREEN)
                    .addTag(CCLTags.Items.WOOLS_RED)
                    .addTag(CCLTags.Items.WOOLS_BLACK);
            tag(CCLTags.Items.WOOLS_WHITE).add(Items.WOOL.pick(DyeColor.WHITE).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_ORANGE).add(Items.WOOL.pick(DyeColor.ORANGE).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_MAGENTA).add(Items.WOOL.pick(DyeColor.MAGENTA).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_LIGHT_BLUE).add(Items.WOOL.pick(DyeColor.LIGHT_BLUE).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_YELLOW).add(Items.WOOL.pick(DyeColor.YELLOW).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_LIME).add(Items.WOOL.pick(DyeColor.LIME).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_PINK).add(Items.WOOL.pick(DyeColor.PINK).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_GRAY).add(Items.WOOL.pick(DyeColor.GRAY).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_LIGHT_GRAY).add(Items.WOOL.pick(DyeColor.LIGHT_GRAY).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_CYAN).add(Items.WOOL.pick(DyeColor.CYAN).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_PURPLE).add(Items.WOOL.pick(DyeColor.PURPLE).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_BLUE).add(Items.WOOL.pick(DyeColor.BLUE).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_BROWN).add(Items.WOOL.pick(DyeColor.BROWN).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_GREEN).add(Items.WOOL.pick(DyeColor.GREEN).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_RED).add(Items.WOOL.pick(DyeColor.RED).builtInRegistryHolder().key());
            tag(CCLTags.Items.WOOLS_BLACK).add(Items.WOOL.pick(DyeColor.BLACK).builtInRegistryHolder().key());
        }

        @Override
        public String getName() {
            return "CodeChickenLib Item tags.";
        }
    }

    public static class LangUS extends LanguageProvider {

        public LangUS(PackOutput output) {
            super(output, MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            addServer("ccl.commands.gc.before", "Before:");
            addServer("ccl.commands.gc.performing", "Performing GC..");
            addServer("ccl.commands.gc.after", "After:");
            addServer("ccl.commands.killall.fail", "Found no entities.");
            addServer("ccl.commands.killall.fail.player", "You cannot kill players with this command.");
            addServer("ccl.commands.killall.success", "Killed %s entities.");
            addServer("ccl.commands.killall.success.line", "Killed %s");
            addServer("ccl.commands.count.fail", "Found no entities.");
            addServer("ccl.commands.count.total", "Found %s entities.");

            addServer("ccl.energy_bar.energy_storage", "Energy Storage");
            addServer("ccl.energy_bar.capacity", "Capacity:");
            addServer("ccl.energy_bar.stored", "Stored:");
            addServer("ccl.energy_bar.rf", "RF");
            addServer("ccl.fluid_tank.fluid_storage", "Fluid Storage");
            addServer("ccl.fluid_tank.capacity", "Capacity:");
            addServer("ccl.fluid_tank.stored", "Stored:");
            addServer("ccl.fluid_tank.mb", "mB");
            addServer("ccl.fluid_tank.contains", "Contains:");
        }
    }
}

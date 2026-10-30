package codechicken.lib.gui.modular.lib;

import codechicken.lib.CodeChickenLib;
import com.mojang.blaze3d.platform.cursor.CursorType;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

/**
 * Created by brandon3055 on 11/5/20.
 * <p>
 * 26.3 moved windowing to SDL3 and selects the cursor every frame from
 * {@link GuiGraphicsExtractor#requestCursor}, so custom GLFW image cursors are gone. The cursor ids map
 * onto SDL system cursors instead, and {@link #requestCursor} must be called while extracting the gui.
 */
public class CursorHelper {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final Identifier DRAG = Identifier.fromNamespaceAndPath(CodeChickenLib.MOD_ID, "textures/gui/cursors/drag.png");
    public static final Identifier RESIZE_H = Identifier.fromNamespaceAndPath(CodeChickenLib.MOD_ID, "textures/gui/cursors/resize_h.png");
    public static final Identifier RESIZE_V = Identifier.fromNamespaceAndPath(CodeChickenLib.MOD_ID, "textures/gui/cursors/resize_v.png");
    public static final Identifier RESIZE_TRBL = Identifier.fromNamespaceAndPath(CodeChickenLib.MOD_ID, "textures/gui/cursors/resize_diag_trbl.png");
    public static final Identifier RESIZE_TLBR = Identifier.fromNamespaceAndPath(CodeChickenLib.MOD_ID, "textures/gui/cursors/resize_diag_tlbr.png");

    // SDL_SystemCursor ids, as used by vanilla's CursorTypes.
    private static final int SDL_SYSTEM_CURSOR_NWSE_RESIZE = 5;
    private static final int SDL_SYSTEM_CURSOR_NESW_RESIZE = 6;

    private static final Map<Identifier, CursorType> cursors = new HashMap<>();
    private static @Nullable Identifier active = null;

    private static CursorType createCursor(Identifier cursor) {
        if (cursor.equals(DRAG)) return CursorTypes.RESIZE_ALL;
        if (cursor.equals(RESIZE_H)) return CursorTypes.RESIZE_EW;
        if (cursor.equals(RESIZE_V)) return CursorTypes.RESIZE_NS;
        if (cursor.equals(RESIZE_TLBR)) return CursorType.createStandardCursor(SDL_SYSTEM_CURSOR_NWSE_RESIZE, "ccl_resize_nwse", CursorTypes.RESIZE_ALL);
        if (cursor.equals(RESIZE_TRBL)) return CursorType.createStandardCursor(SDL_SYSTEM_CURSOR_NESW_RESIZE, "ccl_resize_nesw", CursorTypes.RESIZE_ALL);
        LOGGER.warn("Unknown cursor {}, using the default cursor", cursor);
        return CursorType.DEFAULT;
    }

    public static void setCursor(@Nullable Identifier cursor) {
        active = cursor;
    }

    public static void resetCursor() {
        active = null;
    }

    /**
     * Requests the active cursor for this frame. Call while extracting the gui.
     */
    public static void requestCursor(GuiGraphicsExtractor graphics) {
        if (active != null) {
            graphics.requestCursor(cursors.computeIfAbsent(active, CursorHelper::createCursor));
        }
    }

    public static void onResourceReload() {
        // System cursors don't depend on resources.
    }
}

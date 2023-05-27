package me.pl3xmapclaimsbentobox.hook.bentobox;

import java.util.Collection;
import me.pl3xmapclaimsbentobox.hook.bentobox.Hook;
import world.bentobox.bentobox.BentoBox;
import net.pl3x.map.core.markers.layer.WorldLayer;
import net.pl3x.map.core.markers.marker.Marker;
import net.pl3x.map.core.world.World;
import org.jetbrains.annotations.NotNull;

public class BentoBoxLayer extends WorldLayer {
    public static final String KEY = "bentobox";

    private final BentoBoxHook bentoBoxHook;

    public BentoBoxLayer(@NotNull BentoBoxHook bentoBoxHook, @NotNull World world) {
        super(KEY, world, () -> BentoBoxConfig.LAYER_LABEL);
        this.bentoBoxHook = bentoBoxHook;

        setShowControls(BentoBoxConfig.LAYER_SHOW_CONTROLS);
        setDefaultHidden(BentoBoxConfig.LAYER_DEFAULT_HIDDEN);
        setUpdateInterval(BentoBoxConfig.LAYER_UPDATE_INTERVAL);
        setPriority(BentoBoxConfig.LAYER_PRIORITY);
        setZIndex(BentoBoxConfig.LAYER_ZINDEX);
    }

    @Override
    public @NotNull Collection<Marker<?>> getMarkers() {
        return this.bentoBoxHook.getClaims(getWorld());
    }
}

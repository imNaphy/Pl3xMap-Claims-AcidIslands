package me.pl3xmapclaimsbentobox.hook.bentobox;

import world.bentobox.bentobox.BentoBox;
import me.pl3xmapclaimsbentobox.hook.bentobox.Hook;
import java.util.Collection;
import java.util.stream.Collectors;
import net.pl3x.map.core.markers.marker.Marker;
import net.pl3x.map.core.markers.option.Options;
import net.pl3x.map.core.util.Colors;
import net.pl3x.map.core.world.World;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class BentoBoxHook implements Listener, Hook {
    public BentoBoxHook() {
        BentoBoxConfig.reload();
    }

    private boolean isWorldAI(@NotNull String name) {
        //System.out.println("fdgndfjigfd dfmgfdglfd test");
        return name.equalsIgnoreCase("acidisland_world") || name.equalsIgnoreCase("acidisland_world_nether") || name.equalsIgnoreCase("acidisland_world_the_end");
    }

    @Override
    public void registerWorld(@NotNull World world) {
        if (isWorldAI(world.getName())) {
            world.getLayerRegistry().register(new BentoBoxLayer(this, world));
        }
    }

    @Override
    public void unloadWorld(@NotNull World world) {
        world.getLayerRegistry().unregister(BentoBoxLayer.KEY);
    }

    @Override
    public @NotNull Collection<Marker<?>> getClaims(@NotNull World world) {
        if (!isWorldAI(world.getName())) {
            //System.out.println("test2   3");
            return EMPTY_LIST;
        }
        //System.out.println("testtrt 3");
        return BentoBox.getInstance().getIslands().getIslands().stream()
                .map(island -> new BentoBoxClaim(world, island))
                .map(island -> {
                    String key = "bb-island-" + island.getOwnerId();
                    return Marker.rectangle(key, island.getMin(), island.getMax())
                            .setOptions(getOptions(island));
                })
                .collect(Collectors.toSet());
    }

    private @NotNull Options getOptions(@NotNull BentoBoxClaim island) {
        return Options.builder()
                .strokeWeight(BentoBoxConfig.MARKER_STROKE_WEIGHT)
                .strokeColor(Colors.fromHex(BentoBoxConfig.MARKER_STROKE_COLOR))
                .fillColor(Colors.fromHex(BentoBoxConfig.MARKER_FILL_COLOR))
                .popupContent(processPopup(BentoBoxConfig.MARKER_POPUP, island))
                .build();
    }

    private @NotNull String processPopup(@NotNull String popup, @NotNull BentoBoxClaim island) {
        return popup.replace("<world>", island.getWorld().getName())
                .replace("<ownername>", island.getOwnerName());
        //.replace("<owners>", getOwners(claim))
        //.replace("<members>", getMembers(claim))
        //.replace("<parent>", claim.getParent() == null ? "" : claim.getParent().getId())
        //.replace("<priority>", String.valueOf(claim.getPriority()))
        //.replace("<flags>", getFlags(claim));
    }
}

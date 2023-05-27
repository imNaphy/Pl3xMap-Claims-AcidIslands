package me.pl3xmapclaimsbentobox.hook.bentobox;

import net.pl3x.map.core.markers.Point;
import me.pl3xmapclaimsbentobox.hook.bentobox.Hook;
import net.pl3x.map.core.world.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import world.bentobox.bentobox.database.objects.Island;

import java.util.UUID;

public class BentoBoxClaim {
    private final World world;
    private final Island island;
    private final Point min;
    private final Point max;
    private UUID ownerId = null;
    private final String ownerName;

    public BentoBoxClaim(@NotNull World world, @NotNull Island island) {
        this.world = world;
        this.island = island;
        this.ownerId = island.getOwner();
        this.ownerName = Bukkit.getOfflinePlayer(island.getOwner()).getName();

        this.min = Point.of(island.getMinProtectedX(), island.getMinProtectedZ());
        this.max = Point.of(island.getMaxProtectedX(), island.getMaxProtectedZ());
        //System.out.println("test1 " + min + " " + max);
    }

    public @NotNull World getWorld() {
        return this.world;
    }

    public @NotNull Point getMin() {
        return this.min;
    }

    public @NotNull Point getMax() {
        return this.max;
    }
    public @NotNull UUID getOwnerId() {
        return this.ownerId;
    }
    public @NotNull String getOwnerName() {
        return this.ownerName;
    }
}

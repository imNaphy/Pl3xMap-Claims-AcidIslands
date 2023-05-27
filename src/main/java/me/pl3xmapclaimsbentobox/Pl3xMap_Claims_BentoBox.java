package me.pl3xmapclaimsbentobox;

import me.pl3xmapclaimsbentobox.hook.bentobox.Hook;
import me.pl3xmapclaimsbentobox.listener.Pl3xMapListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Pl3xMap_Claims_BentoBox extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!getServer().getPluginManager().isPluginEnabled("Pl3xMap")) {
            getLogger().severe("Pl3xMap not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Arrays.stream(Hook.Impl.values()).forEach(impl -> {
            if (getServer().getPluginManager().isPluginEnabled(impl.getPluginName())) {
                getLogger().info("Hooking into " + impl.getPluginName());
                Hook.add(impl);
            }
        });

        getServer().getPluginManager().registerEvents(new Pl3xMapListener(), this);
    }

    @Override
    public void onDisable() {
        Hook.clear();
    }
}

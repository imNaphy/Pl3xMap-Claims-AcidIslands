package me.pl3xmapclaimsbentobox.hook.bentobox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.pl3x.map.core.markers.marker.Marker;
import net.pl3x.map.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Hook {
    Collection<Marker<?>> EMPTY_LIST = new ArrayList<>();
    Map<String, Hook> HOOKS = new HashMap<>();

    static @NotNull Collection<Hook> hooks() {
        return HOOKS.values();
    }

    static void add(@NotNull String name) {
        add(Impl.get(name));
    }

    static void add(@Nullable Impl impl) {
        if (impl != null) {
            HOOKS.put(impl.name, impl.hook.get());
        }
    }

    static void remove(@NotNull String name) {
        HOOKS.remove(name);
    }

    static void clear() {
        HOOKS.clear();
    }

    void registerWorld(@NotNull World world);

    void unloadWorld(@NotNull World world);

    @NotNull Collection<Marker<?>> getClaims(@NotNull World world);

    enum Impl {
        BENTOBOX("BentoBox", BentoBoxHook::new);

        private final String name;
        private final Supplier<Hook> hook;

        Impl(@NotNull String name, @NotNull Supplier<Hook> hook) {
            this.name = name;
            this.hook = hook;
        }

        public @NotNull String getPluginName() {
            return this.name;
        }

        static final @NotNull Map<String, Impl> MAP = new HashMap<>();

        static {
            for (Impl impl : values()) {
                MAP.put(impl.name, impl);
            }
        }

        static @Nullable Impl get(@NotNull String name) {
            return MAP.get(name);
        }
    }
}

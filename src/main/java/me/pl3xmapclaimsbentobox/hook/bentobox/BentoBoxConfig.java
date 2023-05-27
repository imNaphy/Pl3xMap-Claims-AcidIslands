package me.pl3xmapclaimsbentobox.hook.bentobox;

import java.nio.file.Path;
import me.pl3xmapclaimsbentobox.Pl3xMap_Claims_BentoBox;
import net.pl3x.map.core.configuration.AbstractConfig;

@SuppressWarnings("CanBeFinal")
public final class BentoBoxConfig extends AbstractConfig {
    @Key("settings.layer.label")
    @Comment("Label for map layer")
    public static String LAYER_LABEL = "BentoBox";
    @Key("settings.layer.show-controls")
    @Comment("Show controls for map layer")
    public static boolean LAYER_SHOW_CONTROLS = true;
    @Key("settings.layer.default-hidden")
    @Comment("Whether map layer is hidden by default")
    public static boolean LAYER_DEFAULT_HIDDEN = false;
    @Key("settings.layer.update-interval")
    @Comment("Update interval for map layer")
    public static int LAYER_UPDATE_INTERVAL = 30;
    @Key("settings.layer.priority")
    @Comment("Priority for map layer")
    public static int LAYER_PRIORITY = 10;
    @Key("settings.layer.z-index")
    @Comment("zIndex for map layer")
    public static int LAYER_ZINDEX = 10;

    @Key("settings.claim.stroke.color")
    @Comment("Stroke color (#AARRGGBB)")
    public static String MARKER_STROKE_COLOR = "#FFCC66FF";
    @Key("settings.claim.stroke.weight")
    @Comment("Stroke weight")
    public static int MARKER_STROKE_WEIGHT = 3;
    @Key("settings.claim.fill.color")
    @Comment("Fill color (#AARRGGBB)")
    public static String MARKER_FILL_COLOR = "#33CC66FF";
    @Key("settings.claim.popup.tooltip")
    @Comment("Popup for islands")
    public static String MARKER_POPUP = """
            Owner: <span style="font-weight:bold;"><ownername></span><br/>""";

    private static final BentoBoxConfig CONFIG = new BentoBoxConfig();

    public static void reload() {
        Path mainDir = Pl3xMap_Claims_BentoBox.getPlugin(Pl3xMap_Claims_BentoBox.class).getDataFolder().toPath();
        CONFIG.reload(mainDir.resolve("bentobox.yml"), BentoBoxConfig.class);
    }
}

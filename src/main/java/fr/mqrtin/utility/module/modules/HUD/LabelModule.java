package fr.mqrtin.utility.module.modules.HUD;

import fr.mqrtin.utility.enums.LabelPreset;
import fr.mqrtin.utility.enums.LabelType;
import fr.mqrtin.utility.event.EventTarget;
import fr.mqrtin.utility.event.events.Render2DEvent;
import fr.mqrtin.utility.module.ModuleCategory;
import fr.mqrtin.utility.module.impl.Module;
import fr.mqrtin.utility.utils.TextUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class LabelModule extends Module {

    private List<String> labels = new ArrayList<>();

    public LabelModule() {
        super("Label", ModuleCategory.HUD, true);
        for (LabelPreset value : LabelPreset.values()) {
            labels.add(value.getFormat(LabelType.SEMI_ARROW));
        }
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;
        FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        for (int i = 0; i < labels.size(); i++) {
            fontRendererObj.drawStringWithShadow(TextUtils.format(labels.get(i)), 5,5 + fontRendererObj.FONT_HEIGHT * i, 0xFFFFFF);
        }

    }

}

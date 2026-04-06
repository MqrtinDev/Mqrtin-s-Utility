package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;
import net.minecraft.entity.EntityLivingBase;

public class RenderLivingEvent extends Event {

    private final EntityLivingBase entity;
    private final float partialTicks;

    public RenderLivingEvent(EntityLivingBase entity, float partialTicks) {
        this.entity = entity;
        this.partialTicks = partialTicks;
    }

    public EntityLivingBase getEntity() {
        return entity;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}


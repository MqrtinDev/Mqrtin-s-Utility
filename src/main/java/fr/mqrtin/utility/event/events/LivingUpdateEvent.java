package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;
import net.minecraft.entity.EntityLivingBase;

public class LivingUpdateEvent extends Event {

    private final EntityLivingBase entity;

    public LivingUpdateEvent(EntityLivingBase entity) {
        this.entity = entity;
    }

    public EntityLivingBase getEntity() {
        return entity;
    }
}


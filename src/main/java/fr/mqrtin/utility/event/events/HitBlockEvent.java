package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class HitBlockEvent extends CancellableEvent {

    private final BlockPos blockPos;
    private final EnumFacing side;

    public HitBlockEvent(BlockPos blockPos, EnumFacing side) {
        this.blockPos = blockPos;
        this.side = side;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public EnumFacing getSide() {
        return side;
    }
}


package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.entity.custom.MissileEntity;
import com.hbm.nucleartech.explosion.NuclearBombType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MissileLauncherBlockEntity extends BlockEntity implements MenuProvider {

    private double targetX = 0;
    private double targetY = 64;
    private double targetZ = 0;
    private String bombTypeName = "standard";
    private int cooldown = 0;

    public MissileLauncherBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.MISSILE_LAUNCHER_BE.get(), pos, state);
    }

    public void launch() {
        if (level == null || level.isClientSide) return;
        if (cooldown > 0) return;
        System.out.println("[Launcher] Firing at: " + targetX + " " + targetY + " " + targetZ);

        double x = worldPosition.getX() + 0.5;
        double y = worldPosition.getY() + 3.0;
        double z = worldPosition.getZ() + 0.5;

        NuclearBombType type = getBombType();
        MissileEntity missile = new MissileEntity(level, x, y, z, targetX, targetY, targetZ, type);
        level.addFreshEntity(missile);

        cooldown = 200;
        setChanged();
    }

    public void tick() {
        if (cooldown > 0) cooldown--;
    }

    public void setTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        setChanged();
    }

    public void setBombType(String name) {
        this.bombTypeName = name;
        setChanged();
    }

    public double getTargetX() { return targetX; }
    public double getTargetY() { return targetY; }
    public double getTargetZ() { return targetZ; }
    public String getBombTypeName() { return bombTypeName; }
    public int getCooldown() { return cooldown; }

    private NuclearBombType getBombType() {
        return switch (bombTypeName) {
            case "tactical" -> NuclearBombType.TACTICAL;
            case "thermonuclear" -> NuclearBombType.THERMONUCLEAR;
            case "bunker_buster" -> NuclearBombType.BUNKER_BUSTER;
            case "cobalt" -> NuclearBombType.COBALT;
            default -> NuclearBombType.STANDARD;
        };
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        targetX = tag.getDouble("targetX");
        targetY = tag.getDouble("targetY");
        targetZ = tag.getDouble("targetZ");
        bombTypeName = tag.getString("bombType");
        cooldown = tag.getInt("cooldown");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("targetX", targetX);
        tag.putDouble("targetY", targetY);
        tag.putDouble("targetZ", targetZ);
        tag.putString("bombType", bombTypeName);
        tag.putInt("cooldown", cooldown);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Missile Launcher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return null; // GUI добавим позже
    }
}

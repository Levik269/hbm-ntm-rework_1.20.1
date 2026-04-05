package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.block.menu.MissileLauncherMenu;
import com.hbm.nucleartech.entity.custom.MissileEntity;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.explosion.NuclearBombType;
import com.hbm.nucleartech.item.custom.MissileItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MissileLauncherBlockEntity extends BlockEntity implements MenuProvider, IHbmEnergy.Consumer {

    // ---- Energy ----
    private long energyStored = 0;
    public static final long MAX_ENERGY    = MissileLauncherMenu.MAX_ENERGY; // 50 000
    public static final long LAUNCH_ENERGY = 10_000L;

    // ---- Missile ammo slot ----
    private final SimpleContainer missileInventory = new SimpleContainer(1);

    // ---- State ----
    private double targetX = 0;
    private double targetY = 64;
    private double targetZ = 0;
    private int cooldown = 0;

    // ---- ContainerData — 3 ints: energy_hi, energy_lo, cooldown ----
    private final ContainerData containerData = new ContainerData() {
        @Override public int get(int index) {
            return switch (index) {
                case 0 -> (int)((energyStored >> 16) & 0xFFFFL); // energy high word
                case 1 -> (int)(energyStored & 0xFFFFL);          // energy low word
                case 2 -> cooldown;
                default -> 0;
            };
        }
        @Override public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStored = (energyStored & 0xFFFFL)        | ((long)(value & 0xFFFF) << 16);
                case 1 -> energyStored = (energyStored & 0xFFFF0000L)    | (long)(value & 0xFFFF);
                case 2 -> cooldown = value;
            }
        }
        @Override public int getCount() { return 3; }
    };

    public MissileLauncherBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.MISSILE_LAUNCHER_BE.get(), pos, state);
    }

    // ---- IHbmEnergy.Consumer ----
    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        long accepted = Math.min(maxReceive, MAX_ENERGY - energyStored);
        if (!simulate) {
            energyStored += accepted;
            setChanged();
        }
        return accepted;
    }

    @Override public long getEnergyStored()    { return energyStored; }
    @Override public long getMaxEnergyStored() { return MAX_ENERGY; }
    @Override public CableTier getInputTier()  { return CableTier.CONNECTOR_COPPER; }

    // ---- Launch ----
    public void launch() {
        if (level == null || level.isClientSide) return;
        if (cooldown > 0) return;

        ItemStack stack = missileInventory.getItem(0);
        if (stack.isEmpty() || !(stack.getItem() instanceof MissileItem missileItem)) {
            System.out.println("[Launcher] No missile loaded!");
            return;
        }
        if (energyStored < LAUNCH_ENERGY) {
            System.out.println("[Launcher] Not enough energy: " + energyStored + " / " + LAUNCH_ENERGY);
            return;
        }

        double x = worldPosition.getX() + 0.5;
        double y = worldPosition.getY() + 3.0;
        double z = worldPosition.getZ() + 0.5;

        NuclearBombType type = missileItem.getBombType();
        MissileEntity missile = new MissileEntity(level, x, y, z, targetX, targetY, targetZ, type);
        level.addFreshEntity(missile);

        // Consume resources
        stack.shrink(1);
        energyStored -= LAUNCH_ENERGY;
        cooldown = 200;
        setChanged();

        System.out.println("[Launcher] Fired " + type.name + " at " + targetX + " " + targetY + " " + targetZ);
    }

    public void tick() {
        if (cooldown > 0) { cooldown--; setChanged(); }
    }

    // ---- Targeting ----
    public void setTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        setChanged();
    }

    public double getTargetX() { return targetX; }
    public double getTargetY() { return targetY; }
    public double getTargetZ() { return targetZ; }
    public int    getCooldown() { return cooldown; }

    public SimpleContainer getMissileInventory() { return missileInventory; }
    public ContainerData   getContainerData()    { return containerData; }

    // ---- Validity check for the menu ----
    public boolean stillValid(Player player) {
        if (level == null || level.getBlockEntity(worldPosition) != this) return false;
        return player.distanceToSqr(
                worldPosition.getX() + 0.5,
                worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5) <= 64.0;
    }

    // ---- MenuProvider ----
    @Override
    public Component getDisplayName() {
        return Component.literal("Missile Launcher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new MissileLauncherMenu(id, inv, this);
    }

    // ---- NBT ----
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        targetX      = tag.getDouble("targetX");
        targetY      = tag.getDouble("targetY");
        targetZ      = tag.getDouble("targetZ");
        cooldown     = tag.getInt("cooldown");
        energyStored = tag.getLong("energyStored");

        // Load missile slot
        if (tag.contains("MissileInventory", Tag.TAG_LIST)) {
            ListTag list = tag.getList("MissileInventory", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag itemTag = list.getCompound(i);
                int slot = itemTag.getByte("Slot");
                if (slot >= 0 && slot < missileInventory.getContainerSize()) {
                    missileInventory.setItem(slot, ItemStack.of(itemTag));
                }
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("targetX", targetX);
        tag.putDouble("targetY", targetY);
        tag.putDouble("targetZ", targetZ);
        tag.putInt("cooldown", cooldown);
        tag.putLong("energyStored", energyStored);

        // Save missile slot
        ListTag list = new ListTag();
        for (int i = 0; i < missileInventory.getContainerSize(); i++) {
            ItemStack stack = missileInventory.getItem(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = stack.save(new CompoundTag());
                itemTag.putByte("Slot", (byte) i);
                list.add(itemTag);
            }
        }
        tag.put("MissileInventory", list);
    }
}

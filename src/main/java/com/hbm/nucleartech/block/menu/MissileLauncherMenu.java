package com.hbm.nucleartech.block.menu;

import com.hbm.nucleartech.block.entity.MissileLauncherBlockEntity;
import com.hbm.nucleartech.item.custom.MissileItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class MissileLauncherMenu extends AbstractContainerMenu {

    public static final long MAX_ENERGY = 50_000L;

    @Nullable private final MissileLauncherBlockEntity blockEntity;
    private final ContainerData data;

    // Target coordinates — sent through FriendlyByteBuf at open time
    public final double targetX;
    public final double targetY;
    public final double targetZ;

    // ---- Client-side constructor (called by IForgeMenuType factory) ----
    public MissileLauncherMenu(int id, Inventory playerInv, FriendlyByteBuf buf) {
        super(RegisterMenus.MISSILE_LAUNCHER.get(), id);

        BlockPos pos    = buf.readBlockPos();
        this.targetX    = buf.readDouble();
        this.targetY    = buf.readDouble();
        this.targetZ    = buf.readDouble();

        Level level = playerInv.player.level();
        BlockEntity be  = level.getBlockEntity(pos);
        this.blockEntity = be instanceof MissileLauncherBlockEntity l ? l : null;

        // Client uses a simple writable data container (filled by server packets)
        this.data = new SimpleContainerData(3);

        setupSlots(playerInv, blockEntity);
        addDataSlots(this.data);
    }

    // ---- Server-side constructor (called by MenuProvider.createMenu) ----
    public MissileLauncherMenu(int id, Inventory playerInv, MissileLauncherBlockEntity be) {
        super(RegisterMenus.MISSILE_LAUNCHER.get(), id);

        this.blockEntity = be;
        this.targetX     = be.getTargetX();
        this.targetY     = be.getTargetY();
        this.targetZ     = be.getTargetZ();
        this.data        = be.getContainerData();

        setupSlots(playerInv, be);
        addDataSlots(this.data);
    }

    private void setupSlots(Inventory playerInv, @Nullable MissileLauncherBlockEntity be) {
        // Missile ammo slot — only MissileItem accepted
        SimpleContainer inv = be != null ? be.getMissileInventory() : new SimpleContainer(1);
        addSlot(new Slot(inv, 0, 80, 31) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof MissileItem;
            }
        });

        // Player inventory (3 rows)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        // Hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    // ---- Data helpers (used by Screen) ----
    /** Energy stored, reconstructed from two 16-bit container data slots. */
    public long getEnergyStored() {
        return ((long)(data.get(0) & 0xFFFF) << 16) | (long)(data.get(1) & 0xFFFF);
    }

    /** Remaining cooldown ticks; 0 = ready. */
    public int getCooldown() {
        return data.get(2);
    }

    // ---- Shift-click ----
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy  = stack.copy();

        if (index == 0) {
            // Missile slot → player inventory
            if (!moveItemStackTo(stack, 1, 37, true)) return ItemStack.EMPTY;
        } else {
            // Player inventory → missile slot (only MissileItem)
            if (stack.getItem() instanceof MissileItem) {
                if (!moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
            } else {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        slot.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity != null && blockEntity.stillValid(player);
    }
}

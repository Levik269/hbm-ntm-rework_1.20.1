package com.hbm.nucleartech.block.screen;

import com.hbm.nucleartech.block.menu.MissileLauncherMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MissileLauncherScreen extends AbstractContainerScreen<MissileLauncherMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("hbm", "textures/gui/missile_launcher.png");

    public MissileLauncherScreen(MissileLauncherMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth  = 176;
        this.imageHeight = 166;
        // Move player inventory label down to standard position
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float delta) {
        renderBackground(gui);
        super.render(gui, mouseX, mouseY, delta);
        renderTooltip(gui, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(net.minecraft.client.renderer.GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        int x = leftPos;
        int y = topPos;

        // Draw the full GUI background (176×166 from top-left of texture)
        gui.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // ---- Energy bar fill (green, fills bottom→top) ----
        long energy    = menu.getEnergyStored();
        long maxEnergy = MissileLauncherMenu.MAX_ENERGY;
        int barInnerH  = 56;   // pixel height of the inner fill area
        int barInnerX  = x + 9;
        int barBottomY = y + 64; // y of the bottom of the inner area

        if (maxEnergy > 0 && energy > 0) {
            int fillH = (int)(barInnerH * energy / maxEnergy);
            // Draw from bottom upward
            gui.fill(barInnerX, barBottomY - fillH, barInnerX + 16, barBottomY, 0xFF00CC00);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
        // Title
        gui.drawString(font, title, 38, 6, 0x404040, false);

        // Inventory label (vanilla sets inventoryLabelY to imageHeight - 94 = 72)
        gui.drawString(font, playerInventoryTitle, 8, inventoryLabelY, 0x404040, false);

        // ---- Energy label below the bar ----
        long energy = menu.getEnergyStored();
        String energyStr = energy + " W";
        gui.drawString(font, energyStr, 8, 67, 0x404040, false);

        // ---- Ammo slot label ----
        gui.drawString(font, "Ammo", 72, 20, 0x404040, false);

        // ---- Target coordinates ----
        int tx = 104;
        gui.drawString(font, "Target:", tx, 8,  0x404040, false);
        gui.drawString(font, "X: " + (int) menu.targetX, tx, 18, 0x404040, false);
        gui.drawString(font, "Y: " + (int) menu.targetY, tx, 28, 0x404040, false);
        gui.drawString(font, "Z: " + (int) menu.targetZ, tx, 38, 0x404040, false);

        // ---- Cooldown / Ready status ----
        int cd = menu.getCooldown();
        if (cd > 0) {
            gui.drawString(font, "CD: " + cd + "t", tx, 52, 0xCC2222, false);
        } else {
            gui.drawString(font, "Ready!", tx, 52, 0x22AA22, false);
        }
    }
}

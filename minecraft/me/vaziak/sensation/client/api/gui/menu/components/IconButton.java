package me.vaziak.sensation.client.api.gui.menu.components;

import java.awt.Color;

import me.vaziak.sensation.utils.anthony.ColorCreator;
import me.vaziak.sensation.utils.anthony.Draw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class IconButton extends GuiButton {

    private final ResourceLocation iconLocation;
    private final int iconDimensions;

    public IconButton(int buttonId, int x, int y, int widthIn, int heightIn, ResourceLocation iconLocation, int iconDimensions) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.iconLocation = iconLocation;
        this.iconDimensions = iconDimensions;
    }

    protected int getHoverState(boolean mouseOver) {
        int i = 1;

        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        }

        return i;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            /*Draw.drawRectangle(this.xPosition - (this.hovered ? 1 : 0), this.yPosition - (this.hovered ? 1 : 0), this.xPosition + this.width + (this.hovered ? 1 : 0), this.yPosition + this.height + (this.hovered ? 1 : 0),
                    ColorCreator.create(10, 10, 10, 150));
*/
            if (id == 666) {
                // bypass
                GlStateManager.color(1f, 1f, 1f, 1f);
            } else
                GlStateManager.color(1f, 1f, 1f, this.hovered ? 0.85f : 0.5f);

            Draw.drawImg(iconLocation, this.xPosition + this.width / 2 - iconDimensions / 2, this.yPosition + this.height / 2 - iconDimensions / 2, iconDimensions, iconDimensions);
            /*if (hovered) {
                //left
                Draw.drawRectangle(xPosition - 1.1, yPosition, xPosition - 1.3, yPosition + height, new Color(255, 75,60).getRGB());
                //right
                Draw.drawRectangle(xPosition + width + 1.1, yPosition, xPosition + width + 1.3, yPosition + height, new Color(255, 75,60).getRGB());

                //bottom
                Draw.drawRectangle(xPosition - 1.1, yPosition + height, xPosition + width + 1.1, yPosition + height + .3, new Color(255, 75,60).getRGB());

                //top
                Draw.drawRectangle(xPosition - 1.1, yPosition - .2, xPosition + width + 1.1, yPosition - .4, new Color(255, 75,60).getRGB());

            }*/
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY) {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
    }

    public void playPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }

    public int getButtonWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

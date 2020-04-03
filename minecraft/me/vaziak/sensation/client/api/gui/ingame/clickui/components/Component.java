package me.vaziak.sensation.client.api.gui.ingame.clickui.components;

import me.vaziak.sensation.client.api.gui.ingame.clickui.Interface;

/**
 * @author antja03
 */
public class Component {

    protected Interface theInterface;
    public double positionX;
    public double positionY;
    public double maxWidth;
    public double maxHeight;

    public Component(Interface theInterface, final double x, final double y, final double maxWidth, final double maxHeight) {
        this.theInterface = theInterface;
        this.positionX = x;
        this.positionY = y;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void drawComponent(double x, double y) {

    }

    public void updatePosition(double lastDrawPosX, double lastDrawPosY) {
        this.positionX = lastDrawPosX - theInterface.getPositionX();
        this.positionY = lastDrawPosY - theInterface.getPositionY();
    }

    public boolean mouseButtonClicked(int button) {
        return false;
    }

    public void mouseReleased() {

    }

    public void mouseScrolled(int scrollDirection) {

    }

    public boolean handleMouseInput() {
        return false;
    }

    public boolean keyTyped(char typedChar, int keyCode) {
        return false;
    }

    public void onGuiClose() {

    }

}

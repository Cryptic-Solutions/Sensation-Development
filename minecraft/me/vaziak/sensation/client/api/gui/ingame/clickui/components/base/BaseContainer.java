package me.vaziak.sensation.client.api.gui.ingame.clickui.components.base;

import java.util.ArrayList;

import me.vaziak.sensation.client.api.gui.ingame.clickui.Interface;
import me.vaziak.sensation.client.api.gui.ingame.clickui.components.Component;

/**
 * @author antja03
 */
public abstract class BaseContainer extends Component {

    protected final ArrayList<Component> components = new ArrayList<>();

    public BaseContainer(Interface theInterface, double x, double y, double width, double height) {
        super(theInterface, x, y, width, height);
    }

    public abstract void drawComponent(double x, double y);

    public boolean mouseButtonClicked(int button) {
        return false;
    }

    public void mouseReleased() {
    }

    public void mouseScrolled(final int scrollDirection) {
    }

    public boolean handleMouseInput() {
        return false;
    }

    public boolean keyTyped(final int keyCode) {
        return false;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void onGuiClose() {
        for (Component component : this.components) {
            component.onGuiClose();
        }
    }

}

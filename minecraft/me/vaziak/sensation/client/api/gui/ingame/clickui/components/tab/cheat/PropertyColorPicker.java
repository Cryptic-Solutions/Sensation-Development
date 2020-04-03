package me.vaziak.sensation.client.api.gui.ingame.clickui.components.tab.cheat;

import java.awt.*;

import me.vaziak.sensation.client.api.gui.ingame.clickui.Interface;
import me.vaziak.sensation.client.api.property.impl.ColorProperty;
import me.vaziak.sensation.client.api.property.impl.Value;
import me.vaziak.sensation.utils.anthony.Draw;
import me.vaziak.sensation.utils.anthony.basicfont.Fonts;
import me.vaziak.sensation.utils.math.MathUtils;

/**
 * @author antja03
 */
public class PropertyColorPicker extends PropertyComponent {

    private ColorProperty property;
    private boolean extended;
    private boolean selectingHue;
    private boolean selectingSB;
    private boolean selectingAlpha;

    private boolean extraOptions;

    public PropertyColorPicker(Interface theInterface, Value property, double x, double y, double width, double height) {
        super(theInterface, property, x, y, width, height);
        if (property instanceof ColorProperty) {
            this.property = (ColorProperty) property;
        }
        selectingHue = false;
        selectingSB = false;
        selectingAlpha = false;
    }

    @SuppressWarnings("unused")
    @Override
    public void drawComponent(double x, double y) {
        this.positionX = x - theInterface.getPositionX();
        this.positionY = y - theInterface.getPositionY();

        double boxPosX = x + 80;
        double boxPosY = y + 2;
        double boxWidth = 115;
        double boxHeight = extended ? 115 : 10;

        if (theInterface.isClosing()) {
            selectingHue = false;
            selectingSB = false;
            selectingAlpha = false;
            return;
        }

        Fonts.f14.drawString(getProperty().getId(), x + 6, y + maxHeight / 2 - 5.5, theInterface.getColor(255, 255, 255));

        if (!extended) {
            Draw.drawRectangle(boxPosX + 51, boxPosY + 2, boxPosX + boxWidth - 51, boxPosY + boxHeight - 2, property.getValue().getRGB());
            selectingHue = false;
            selectingSB = false;
            selectingAlpha = false;

            if (extraOptions) {
                Draw.drawRectangle(boxPosX + boxWidth - 51, boxPosY + boxHeight - 2, boxPosX + boxWidth - 11, boxPosY + boxHeight + 14,
                        theInterface.getColor(40, 40, 40));

                if (theInterface.isMouseInBounds(boxPosX + boxWidth - 51, boxPosX + boxWidth - 11, boxPosY + boxHeight - 2, boxPosY + boxHeight + 6)) {
                    Draw.drawRectangle(boxPosX + boxWidth - 51, boxPosY + boxHeight - 2, boxPosX + boxWidth - 11, boxPosY + boxHeight + 6,
                            theInterface.getColor(30, 30, 30));
                }
                Fonts.f12.drawString("Copy", boxPosX + boxWidth - 48, boxPosY + boxHeight + 1, theInterface.getColor(220, 220, 220));

                if (theInterface.isMouseInBounds(boxPosX + boxWidth - 51, boxPosX + boxWidth - 11, boxPosY + boxHeight + 6, boxPosY + boxHeight + 14)) {
                    Draw.drawRectangle(boxPosX + boxWidth - 51, boxPosY + boxHeight + 6, boxPosX + boxWidth - 11, boxPosY + boxHeight + 14,
                            theInterface.getColor(30, 30, 30));
                }
                Fonts.f12.drawString("Paste", boxPosX + boxWidth - 48, boxPosY + boxHeight + 9, theInterface.getColor(220, 220, 220));
            }
        } else {
            Draw.drawRectangle(boxPosX + 1, boxPosY + 1, boxPosX + boxWidth - 1, boxPosY + boxHeight - 1, new Color(24, 24, 24).getRGB());

            float inc = 1.0f / 100;

            SB:
            {
                float currentSaturation;
                float currentBrightness;

                for (int i = 0; i < 100; i++) {
                    currentSaturation = i * inc;
                    Draw.drawGradientRect(boxPosX + 2 + i, boxPosY + 2, boxPosX + 3 + i, boxPosY + boxHeight - 14, Color.getHSBColor(property.getHue(), currentSaturation, 1.0f).getRGB(), theInterface.getColor(0, 0, 0));
                    for (int i2 = 0; i2 < 100; i2++) {
                        if (selectingSB) {
                            if (theInterface.isMouseInBounds(boxPosX + 2 + i, boxPosX + 4 + i, boxPosY + boxHeight - 13 - i2, boxPosY + boxHeight - 15 - i2)) {
                                property.setSaturation(i * inc);
                                property.setBrightness(i2 * inc);
                            }
                        }
                    }
                }

                for (int i = 0; i < 100; i++) {
                    for (int i2 = 0; i2 < 100; i2++) {
                        currentSaturation = i * inc;
                        currentBrightness = i2 * inc;
                        if (Math.abs(currentSaturation - property.getSaturation()) < 0.025 && Math.abs(currentBrightness - property.getBrightness()) < 0.025) {
                            Draw.drawRectangle(boxPosX + 2 + i, boxPosY + boxHeight - 14 - i2, boxPosX + 3 + i, boxPosY + boxHeight - 15 - i2, theInterface.getColor(0, 0, 0, 200));
                        }
                    }
                }
            }


            Hue:
            {
                if (selectingHue) {
                    double barHeight = boxHeight - 17;
                    double mousePosOnBar = theInterface.getCurrentFrameMouseY() - boxPosY + 2;
                    if (mousePosOnBar < 0)
                        mousePosOnBar = 0;
                    else if (mousePosOnBar > barHeight) {
                        mousePosOnBar = barHeight;
                    }
                    property.setHue((float) ((1.0f / barHeight) * mousePosOnBar));
                }

                for (int i = 0; i < 100; i++) {
                    Draw.drawRectangle(boxPosX + boxWidth - 11, boxPosY + 2 + i, boxPosX + boxWidth - 2, boxPosY + 3 + i, Color.getHSBColor(i * inc, 1.0f, 1.0f).getRGB());

                    if (Math.abs(i * inc - property.getHue()) <= 0.025) {
                        Draw.drawRectangle(boxPosX + boxWidth - 11, boxPosY + 2 + i, boxPosX + boxWidth - 2, boxPosY + 3 + i, theInterface.getColor(0, 0, 0, 200));
                    }
                }
            }

            Alpha:
            {
                Draw.drawRectangle(boxPosX + 2, boxPosY + boxHeight - 12, boxPosX + boxWidth - 2, boxPosY + boxHeight - 2, theInterface.getColor(40, 40, 40, 255));
                Draw.drawRectangle(boxPosX + 2.5, boxPosY + boxHeight - 11.5, boxPosX + boxWidth - 2.5, boxPosY + boxHeight - 2.5, property.value.getRGB());
                double barWidth = boxWidth - 4;
                if (selectingAlpha) {
                    double mousePosOnBar = theInterface.getCurrentFrameMouseX() - boxPosX + 2;
                    if (mousePosOnBar < 0)
                        mousePosOnBar = 0;
                    else if (mousePosOnBar > barWidth) {
                        mousePosOnBar = barWidth;
                    }
                    property.setAlpha(MathUtils.clamp((255 / barWidth) * mousePosOnBar, 0, 255).intValue());
                }

                double pos = (double) property.getAlpha() / 255 * barWidth;
                Draw.drawRectangle(boxPosX + pos + 1, boxPosY + boxHeight - 11.5, boxPosX + pos + 2, boxPosY + boxHeight - 2.5, theInterface.getColor(0, 0, 0, 200));
            }
        }
    }

    @Override
    public boolean mouseButtonClicked(int button) {

        double boxPosX = theInterface.getPositionX() + this.positionX + 80;
        double boxPosY = theInterface.getPositionY() + this.positionY + 2;
        double boxWidth = 115;
        double boxHeight = extended ? 115 : 10;

        if (button == 0) {
            if (!extended) {
                if (theInterface.isMouseInBounds(boxPosX + boxWidth / 2 - 20, boxPosX + boxWidth / 2 + 20,
                        boxPosY + 1, boxPosY + boxHeight - 1)) {
                    extended = true;
                    extraOptions = false;
                    return true;
                }

                if (extraOptions) {
                    if (theInterface.isMouseInBounds(boxPosX + boxWidth - 51, boxPosX + boxWidth - 10, boxPosY + boxHeight - 2, boxPosY + boxHeight + 6)) {
                        ColorProperty.copiedValue = property.getValueAsString();
                        extraOptions = false;
                        return true;

                    } else if (theInterface.isMouseInBounds(boxPosX + boxWidth - 51, boxPosX + boxWidth - 10, boxPosY + boxHeight + 6, boxPosY + boxHeight + 14)) {
                        if (!ColorProperty.copiedValue.equals("")) {
                            property.setValue(ColorProperty.copiedValue);
                        }
                        extraOptions = false;
                        return true;
                    }
                }
            } else if (extended) {
                if (theInterface.isMouseInBounds(boxPosX + 2, boxPosX + 102, boxPosY + 2, boxPosY + boxHeight - 15)) {
                    selectingSB = true;
                    return true;
                } else if (theInterface.isMouseInBounds(boxPosX + boxWidth - 11, boxPosX + boxWidth - 2, boxPosY + 2, boxPosY + 4 + 100)) {
                    selectingHue = true;
                    return true;
                } else if (theInterface.isMouseInBounds(boxPosX + 2, boxPosX + boxWidth - 2, boxPosY + boxHeight - 15, boxPosY + boxHeight - 2)) {
                    selectingAlpha = true;
                    return true;
                }
            }
        } else if (button == 1) {
            if (theInterface.isMouseInBounds(boxPosX + boxWidth / 2 - 20, boxPosX + boxWidth / 2 + 20,
                    boxPosY + 1, boxPosY + boxHeight - 1)) {
                extended = false;
                extraOptions = true;
                return true;
            }
        }

        extended = false;
        selectingHue = false;
        selectingSB = false;
        selectingAlpha = false;
        extraOptions = false;
        return false;
    }

    @Override
    public void mouseReleased() {
        selectingHue = false;
        selectingSB = false;
        selectingAlpha = false;
    }

    public void onGuiClose() {
        extended = false;
        selectingHue = false;
        selectingSB = false;
        selectingAlpha = false;
        extraOptions = false;
    }

    public boolean isExtended() {
        return extended;
    }
}

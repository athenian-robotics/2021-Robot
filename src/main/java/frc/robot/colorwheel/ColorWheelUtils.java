package frc.robot.colorwheel;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import static frc.robot.colorwheel.WheelColors.*;

public class ColorWheelUtils {

    //Init basic variables
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private boolean isYellow;
    private boolean isGreen;
    private boolean isBlue;
    private boolean isRed;

    //Create class constructor
    public ColorWheelUtils() {

    }

    //A method that puts Strings onto SmartDashboard for organization purposes
    private void updateString(String currentColor) {
        SmartDashboard.putString("Current Color", currentColor);
    }

    private void resetIsColorBooleans() {
        isRed = false;
        isBlue = false;
        isGreen = false;
        isYellow = false;
    }

    /**
     * =-=-=-=-=-=-=-=-=-=
     * COLOR WHEEL METHODS
     * =-=-=-=-=-=-=-=-=-=
     */
    public WheelColors currentColor() {
        //If RED values are larger than green or blue, return RED
        WheelColors currentColor;
        Color detectedColor = colorSensor.getColor();
        double checkColorRed = detectedColor.red * 255;
        double checkColorGreen = detectedColor.green * 255;
        double checkColorBlue = detectedColor.blue * 255;

        //Range for RED
        if ((checkColorRed >= checkColorGreen + 30) && (checkColorRed >= checkColorBlue + 30)) {
            updateString("RED");
            currentColor = RED;
            isRed = true;
        }
        //Range for BLUE
        else if ((checkColorBlue >= checkColorGreen) && (checkColorBlue - 60 <= checkColorGreen)
                || (checkColorBlue <= checkColorGreen) && (checkColorBlue + 60 >= checkColorGreen)) {
            updateString("BLUE");
            currentColor = BLUE;
            isBlue = true;
        }
        //Range for YELLOW
        else if ((checkColorRed >= 90) && (checkColorRed <= checkColorGreen) && (checkColorRed + 20 >= checkColorGreen)) {
            updateString("YELLOW");
            currentColor = YELLOW;
            isYellow = true;
        }
        //Assume GREEN
        else {
            updateString("GREEN");
            currentColor = GREEN;
            isGreen = true;
        }
        //Update the color value,
        updateColorsOnDashboard();
        resetIsColorBooleans();
        return currentColor;
    }

    private void updateColorsOnDashboard() {
        //Update boolean boxes for each color for visual representation
        SmartDashboard.putBoolean("RED", isRed);
        SmartDashboard.putBoolean("BLUE", isBlue);
        SmartDashboard.putBoolean("YELLOW", isYellow);
        SmartDashboard.putBoolean("GREEN", isGreen);
    }

    /**
     * @param currentColor Pass in current color so we know what to go off of
     * @return Return the left color, and assume null
     */
    private WheelColors getLeftColor(WheelColors currentColor) {
        //Take the current color, and return ONE color to the left
        switch (currentColor) {
            case RED:
                return YELLOW;
            case BLUE:
                return GREEN;
            case GREEN:
                return RED;
            case YELLOW:
                return BLUE;
            default:
                return null;
        }
    }

    /**
     * @param currentColor Pass in current color so we know what to go off of
     * @return Return the right color, and assume null
     */
    private WheelColors getRightColor(WheelColors currentColor) {
        //Take the current color and return 1 color to the right
        switch (currentColor) {
            case RED:
                return YELLOW;
            case BLUE:
                return GREEN;
            case GREEN:
                return RED;
            case YELLOW:
                return BLUE;
            default:
                return null;
        }
    }

    private int getTilesMoved() {
        //Init local tilesPassed
        int tilesPassed = 0;
        //Grab the current color
        WheelColors oldColor = currentColor();
        //Wait 0.05 seconds to assure a difference in old and new color
        Timer.delay(0.05);
        //Grab the current color after waiting
        WheelColors newColor = currentColor();

        //If there is a difference, add 1 to the tiles moved value
        if (newColor != oldColor) {
            tilesPassed++;
        }
        //Return and print the value repeatedly
        System.out.println(tilesPassed);
        return tilesPassed;
    }

    /**
     * @param tilesToMove take in the number of tiles we need to move
     */
    public void moveTiles(int tilesToMove) {
        //Grab the current color
        WheelColors currentColor1 = currentColor();
        //Wait 0.05 seconds to assure a difference
        Timer.delay(0.2);
        //Grab the current color after waiting
        WheelColors currentColor2 = currentColor();

        //While we haven't reached the goal tiles to move, check for a difference
        for (int i = 0; i < tilesToMove; i++) {
            if (currentColor1 != currentColor2) {
                tilesToMove--;
            }
            //If we haven't hit the goal, keep motors on
            //Otherwise, turn them off
            if (tilesToMove != 0) {
                //TURN MOTORS ON
            } else {
                //TURN MOTORS OFF
            }
        }
    }

    /**
     * @param colorWanted pass in the color wanted as a point of reference
     */
    public void distanceToColor(WheelColors colorWanted) {
        //Init local tilesToGo
        int tilesToGo;
        //Grab the current color
        WheelColors currentColor = currentColor();
        //If the color wanted is the left color of the current one, return -1 (left 1)
        if (currentColor == getLeftColor(colorWanted)) {
            tilesToGo = -1;
        } else {
            //Otherwise, return # of tiles to go until we are there
            tilesToGo = (32 + colorWanted.i - currentColor.i) % 4;
        }
        //Print the values so we can see them and do something with them.
        Timer.delay(0.05);
        System.out.println(tilesToGo);
    }
}

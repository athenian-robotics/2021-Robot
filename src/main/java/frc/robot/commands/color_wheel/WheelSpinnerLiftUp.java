package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class WheelSpinnerLiftUp extends CommandBase {
    private final ColorWheelSubsystem colorWheel;

    public WheelSpinnerLiftUp(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;
    }

    public void initialize() {
        colorWheel.spinnerLiftUp();
        colorWheel.sensorLiftUp();
    }

    public boolean isFinished() {
        return true;
    }
}

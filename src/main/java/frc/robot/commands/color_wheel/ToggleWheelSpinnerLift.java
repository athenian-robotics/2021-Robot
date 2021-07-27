package frc.robot.commands.color_wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ToggleWheelSpinnerLift extends CommandBase {
    private final ColorWheelSubsystem colorWheel;

    public ToggleWheelSpinnerLift(ColorWheelSubsystem colorWheel) {
        this.colorWheel = colorWheel;
        addRequirements(colorWheel);
    }

    public void initialize() {
        colorWheel.toggleSpinnerLift();
    }

    public boolean isFinished() {
        return true;
    }
}

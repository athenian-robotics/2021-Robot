package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOff extends CommandBase {
    private final IntakeSubsystem intake;

    public AutoIntakeOff(IntakeSubsystem intake) {
        this.intake = intake;
    }

    public void initialize() {
        intake.stopIntake();
    }

    public boolean isFinished() {
        return true;
    }
}

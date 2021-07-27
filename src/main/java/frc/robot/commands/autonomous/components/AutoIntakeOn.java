package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoIntakeOn extends CommandBase {
    private final IntakeSubsystem intake;

    public AutoIntakeOn(IntakeSubsystem intake) {
        this.intake = intake;
    }

    public void initialize() {
        intake.startIntake();
    }

    public boolean isFinished() {
        return true;
    }
}

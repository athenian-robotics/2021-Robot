package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeBackward extends CommandBase {
    public static long start = 0;
    private final IntakeSubsystem intake;

    public IntakeBackward(IntakeSubsystem intakeSubsystem) {
        this.intake = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    public void initialize() {
        start = System.currentTimeMillis();
        intake.invert();
    }

    public boolean isFinished() {
        return System.currentTimeMillis() - start > 200;
    }

    public void end(boolean interrupted) {
        intake.invert();
    }
}

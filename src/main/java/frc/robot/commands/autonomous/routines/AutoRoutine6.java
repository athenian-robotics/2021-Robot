package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRoutine6 extends SequentialCommandGroup {
    public AutoRoutine6(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, ShooterSubsystem shooter, LimeLightSubsystem limelight) {
        addRequirements(drivetrain, intake, shooter, limelight);
        addCommands(
            new AutoRoutine6Base(drivetrain, intake, shooter, limelight)
        );

    }
}

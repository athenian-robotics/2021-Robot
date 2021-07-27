package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRoutine3 extends SequentialCommandGroup {
    public AutoRoutine3(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter, IntakeSubsystem intake) {
        addRequirements(drivetrain, shooter, intake);
        addCommands(
                new AutoIntakeOn(intake),
                new AutoForwardDistance(drivetrain, 4),
                new AutoDelay(1000),
                new AutoIntakeOff(intake),
                new AutoAngleTurn(drivetrain, -20),
                new AutoForwardDistance(drivetrain, -5.31, 2000),
                new AutoAngleTurn(drivetrain, 19),
                new AutoDriveForwardUltrasonic(drivetrain, 8),
                new AutoIntakeOn(intake),
                new AutoDelay(2500),
                new AutoIntakeOff(intake),
                new AutoDumperUp(shooter, 300),
                new AutoShooterTime(shooter, 1700),
                new AutoForwardDistance(drivetrain, 4.5)
        );
    }
}

package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRoutine4 extends SequentialCommandGroup {
    public AutoRoutine4(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter, IntakeSubsystem intake) {
        addRequirements(drivetrain, shooter);
        addCommands(
//                new AutoAngleTurn(drivetrain, -22),
                new AutoForwardDistance(drivetrain, 2.8, 2000), //2.75
                new AutoAngleTurn(drivetrain, 81),
                new AutoIntakeOn(intake),
                new AutoForwardDistance(drivetrain, 1.28),
                new AutoDelay(450),
                new AutoIntakeOff(intake),
                new AutoForwardDistance(drivetrain, -5.96),
                new AutoAngleTurn(drivetrain, -58),
                new AutoIntakeOn(intake),
                new AutoDriveForwardUltrasonic(drivetrain, 8),
                new AutoDelay(400),
                new AutoIntakeOff(intake),
                new AutoDumperUp(shooter, 0),
                new AutoShooterTime(shooter, 1000)
//                new AutoForwardDistance(drivetrain, 4.5)
//                new AutoAngleTurn(drivetrain,-68),
//                new AutoForwardDistance(drivetrain, 2)
        );
    }
}

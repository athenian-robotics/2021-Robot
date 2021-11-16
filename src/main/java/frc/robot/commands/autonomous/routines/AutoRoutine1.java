package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


//goes back until close to wall, dumps the balls, then drives past teh white line
public class AutoRoutine1 extends SequentialCommandGroup {
    public AutoRoutine1(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter, IntakeSubsystem intake) {
        addRequirements(drivetrain, shooter, intake);
        addCommands(
                new AutoDumperUp(shooter, 300),
                new AutoDriveForwardUltrasonic(drivetrain, 8),
                new AutoShooterTime(shooter, 1000),
                new AutoForwardDistance(drivetrain, 4)
        );
    }
}

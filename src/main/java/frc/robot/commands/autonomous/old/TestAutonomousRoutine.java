package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoDriveForwardUltrasonic;
import frc.robot.subsystems.DrivetrainSubsystem;


public class TestAutonomousRoutine extends SequentialCommandGroup {

    DrivetrainSubsystem drivetrain;

    /**
     * Turns then moves forward till a wall.
     * x
     *
     * @param drivetrain         The drive subsystem this command will run on
     * @param angleToTurn        Desired angle to turn
     * @param distanceToStop     Desired distance from the wall to stop
     * @param firstDistance      Initial distance from balls from left side of field
     * @param distanceFromMiddle How far the robot is from the middle of the field
     */
    public TestAutonomousRoutine(DrivetrainSubsystem drivetrain, double angleToTurn, double distanceToStop, double firstDistance, double distanceFromMiddle) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
        addCommands(
                new AutoDriveForwardDistanceCustomTrapezoid(drivetrain, firstDistance),
                new AutoDriveForwardDistanceCustomTrapezoid(drivetrain, -firstDistance),
                new AutoTurnAngle(drivetrain, angleToTurn),
                new AutoDriveForwardDistanceCustomTrapezoid(drivetrain, distanceFromMiddle),
                new AutoTurnAngle(drivetrain, angleToTurn),
                new AutoDriveForwardUltrasonic(drivetrain, distanceToStop)
        );
    }
}
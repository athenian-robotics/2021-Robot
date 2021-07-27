package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.List;

public class FollowTrajectory extends CommandBase {

    private final DrivetrainSubsystem drivetrain;

    public FollowTrajectory(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        ;
        addRequirements(drivetrain);
    }

    public void initialize() {
        drivetrain.resetOdometry(new Pose2d());
    }

    public boolean isFinished() {
        drivetrain.tankDriveVolts(0, 0);
        return false;
    }

    public void end(boolean interrupted) {
        System.out.println("Reached setpoint");
    }

    public Command ExampleAutonomousCommand() {

        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint =
                new DifferentialDriveVoltageConstraint(
                        new SimpleMotorFeedforward(Constants.AutonomousConstants.ksVolts,
                                Constants.AutonomousConstants.kvVoltSecondsPerMeter,
                                Constants.AutonomousConstants.kaVoltSecondsSquaredPerMeter),
                        Constants.AutonomousConstants.kDriveKinematics,
                        10);

        // Create config for trajectory
        TrajectoryConfig config =
                new TrajectoryConfig(Constants.AutonomousConstants.kMaxSpeedMetersPerSecond,
                        Constants.AutonomousConstants.kMaxAccelerationMetersPerSecondSquared)
                        // Add kinematics to ensure max speed is actually obeyed
                        .setKinematics(Constants.AutonomousConstants.kDriveKinematics)
                        // Apply the voltage constraint
                        .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow.  All units in meters.
        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                        new Translation2d(1, 1),
                        new Translation2d(2, 2),
                        new Translation2d(2, 0)
                ),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass config
                config
        );

        RamseteCommand ramseteCommand = new RamseteCommand(
                exampleTrajectory,
                drivetrain::getPose,
                new RamseteController(Constants.AutonomousConstants.kRamseteB, Constants.AutonomousConstants.kRamseteZeta),
                new SimpleMotorFeedforward(Constants.AutonomousConstants.ksVolts,
                        Constants.AutonomousConstants.kvVoltSecondsPerMeter,
                        Constants.AutonomousConstants.kaVoltSecondsSquaredPerMeter),
                Constants.AutonomousConstants.kDriveKinematics,
                drivetrain::getWheelSpeeds,
                new PIDController(Constants.AutonomousConstants.kPDriveVel, 0, 0),
                new PIDController(Constants.AutonomousConstants.kPDriveVel, 0, 0),
                // RamseteCommand passes volts to the callback
                drivetrain::tankDriveVolts,
                drivetrain
        );

        // Run path following command, then stop at the end.
        return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
    }

}

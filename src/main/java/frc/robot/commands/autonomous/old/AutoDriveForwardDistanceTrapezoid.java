package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistanceTrapezoid extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double metersToDrive;
    ProfiledPIDController pid = new ProfiledPIDController(
            0.35, 0.0, 0.0, //0.35 , 0, 0.01
            new TrapezoidProfile.Constraints(0.5, 0.1));

    double setpoint;

    public AutoDriveForwardDistanceTrapezoid(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.05);
        addRequirements(drivetrain);
    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
        this.setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        System.out.println("Current right encoder distance: " + drivetrain.getRightEncoderDistance());
        System.out.println("Setting setpoint to " + setpoint);
    }

    public void execute() {
        double power = pid.calculate(drivetrain.getRightEncoderDistance(), setpoint);
        System.out.println(power);
        drivetrain.tankDrive(power, power);
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}

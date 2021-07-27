package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistance extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double metersToDrive;
    PIDController pid = new PIDController(0.55, 0.0, 0.01); // 0.39, 0.0, 0.01
    double setpoint;

    public AutoDriveForwardDistance(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
        this.setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        System.out.println("Current right encoder distance: " + drivetrain.getRightEncoderDistance());
        System.out.println("Setting setpoint to " + setpoint);
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        double power = pid.calculate(drivetrain.getRightEncoderDistance());
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

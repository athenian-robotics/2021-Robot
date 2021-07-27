package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardDistanceCustomTrapezoid extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    PIDController pid = new PIDController(0.6, 0.0, 0.01); // 0.39, 0.0, 0.01
    private double metersToDrive;
    private long startTime;
    private double encoderSetPoint;

    public AutoDriveForwardDistanceCustomTrapezoid(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
        System.out.println("In Constructor");
    }

    public void initialize() {

        startTime = System.currentTimeMillis();
        driveTimer.reset();
        driveTimer.start();
        double setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        encoderSetPoint = drivetrain.getRightEncoderDistance() - drivetrain.getLeftEncoderDistance();
        //System.out.println("Current right encoder distance: " + drivetrain.getRightEncoderDistance());
        //System.out.println("Setting setpoint to " + setpoint);
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        //System.out.println("Time: " + elapsedTime);
        double power;
        double trapezoidTime = 2000;
        if (elapsedTime <= trapezoidTime) {
            power = pid.calculate(drivetrain.getRightEncoderDistance()) >= 0 ?
                    Math.min(pid.calculate(drivetrain.getRightEncoderDistance()) * (elapsedTime / trapezoidTime), Constants.DriveConstants.maxDriveSpeed) :
                    Math.max(pid.calculate(drivetrain.getRightEncoderDistance()) * (elapsedTime / trapezoidTime), -Constants.DriveConstants.maxDriveSpeed);
        } else {
            power = pid.calculate(drivetrain.getRightEncoderDistance()) >= 0 ?
                    Math.min(pid.calculate(drivetrain.getRightEncoderDistance()), Constants.DriveConstants.maxDriveSpeed) :
                    Math.max(pid.calculate(drivetrain.getRightEncoderDistance()), -Constants.DriveConstants.maxDriveSpeed);

        }
        //System.out.println(power);
        drivetrain.tankDrive(power + drivetrain.leftEncoderCorrection(encoderSetPoint), power + drivetrain.rightEncoderCorrection(encoderSetPoint));
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        //System.out.println("Reached setpoint");
    }

}

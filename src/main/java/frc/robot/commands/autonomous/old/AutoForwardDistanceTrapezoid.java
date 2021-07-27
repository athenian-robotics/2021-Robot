package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoForwardDistanceTrapezoid extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    PIDController pid = new PIDController(5, 0.0, 0.00); // 0.39, 0.0, 0.01
    private double metersToDrive;
    private long startTime;
    private double encoderDifferenceSetpoint;

    public AutoForwardDistanceTrapezoid(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this.drivetrain = drivetrain;
        this.metersToDrive = metersToDrive;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
        driveTimer.reset();
        driveTimer.start();
        double setpoint = drivetrain.getRightEncoderDistance() + metersToDrive;
        encoderDifferenceSetpoint = drivetrain.getRightEncoderDistance() - drivetrain.getLeftEncoderDistance();
        pid.setSetpoint(setpoint);
    }

    public void execute() {
        double power = drivetrain.calculateTrapezoid(pid, startTime, Constants.DriveConstants.maxDriveSpeed, 2000);
        System.out.println(power);
        double leftCorrection = 0; //drivetrain.leftEncoderCorrection(encoderDifferenceSetpoint);
        double rightCorrection = 0; // drivetrain.rightEncoderCorrection(encoderDifferenceSetpoint);
        drivetrain.tankDrive(power + leftCorrection, power + rightCorrection);
    }

    public boolean isFinished() {
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        //System.out.println("Reached setpoint");
    }

}

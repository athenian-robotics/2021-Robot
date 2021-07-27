package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardOdometry extends CommandBase {

    private DrivetrainSubsystem drivetrain;


    //Odometry PID
    private PIDController pid = new PIDController(0.35, 0.0, 0.1); // 0.39, 0.0, 0.01
    private long startTime;
    private double tolerance = 1;
    private double trapezoidTime = 1000;
    private double distance;
    private double encoderSetPoint;

    public AutoDriveForwardOdometry(DrivetrainSubsystem drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        //Custom Trapezoid
        startTime = System.currentTimeMillis();

        //Setpoint for Odometry PID
        double setpoint = distance;
        pid.setSetpoint(setpoint);

        //Setpoint for Encoder PID
        encoderSetPoint = drivetrain.getRightEncoderDistance() - drivetrain.getLeftEncoderDistance();

    }

    public void execute() {

        long elapsedTime = System.currentTimeMillis() - startTime;
        double power;
        //PID calculations
        if (elapsedTime <= trapezoidTime) {
            power = Math.min(Math.abs(pid.calculate(drivetrain.getPose().getTranslation().getX()) * (elapsedTime / trapezoidTime)), 0.3);
        } else {
            power = Math.min(Math.abs(pid.calculate(drivetrain.getPose().getTranslation().getX())), 0.3);
        }
        //System.out.println("Right: "+drivetrain.RightEncoderCorrection(encoderSetPoint));
        //System.out.println("Left: "+drivetrain.LeftEncoderCorrection(encoderSetPoint));
        System.out.println("power: " + power);
        SmartDashboard.putNumber("Odometry Power", power);
        //if(power < Constants.DriveConstants.minDrivePower){

        drivetrain.tankDrive(power + drivetrain.leftEncoderCorrection(encoderSetPoint),
                power + drivetrain.rightEncoderCorrection(encoderSetPoint));
    }

    public boolean isFinished() {
        //Check if ultrasonic values are within tolerance to stop
        if (drivetrain.getPose().getTranslation().getX() <= distance + tolerance && drivetrain.getPose().getTranslation().getX() >= distance - tolerance) {
            return true;
        }
        return false;
    }

    public void end(boolean interrupted) {
        //Set speed to 0 after command
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }

}

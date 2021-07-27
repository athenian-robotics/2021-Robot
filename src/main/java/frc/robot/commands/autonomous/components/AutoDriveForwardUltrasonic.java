package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardUltrasonic extends CommandBase {

    private DrivetrainSubsystem drivetrain;


    //Ultrasonic PID
    private PIDController pid = new PIDController(0.046, 0.0, 0.01); // 0.022
    private long startTime;
    private double tolerance = 1;
    private double trapezoidTime = 2000;
    private double distance;
    private double encoderSetPoint;

    public AutoDriveForwardUltrasonic(DrivetrainSubsystem drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;
        pid.setTolerance(0.5);
        addRequirements(drivetrain);
    }

    public void initialize() {
        //Custom Trapezoid Initializations
        startTime = System.currentTimeMillis();

        //Setpoint for Ultrasonic PID
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
            power = Math.min(Math.abs(pid.calculate(drivetrain.getAverageUltrasonicDistance()) * (elapsedTime / trapezoidTime)), Constants.DriveConstants.maxDriveSpeed);
        } else {
            power = Math.min(Math.abs(pid.calculate(drivetrain.getAverageUltrasonicDistance())), Constants.DriveConstants.maxDriveSpeed);
        }


//        System.out.println(power);
        //System.out.println("Right: "+drivetrain.RightEncoderCorrection(encoderSetPoint));
        //System.out.println("Left: "+drivetrain.LeftEncoderCorrection(encoderSetPoint));

        drivetrain.tankDrive(-power + drivetrain.leftEncoderCorrection(encoderSetPoint),
                -power + drivetrain.rightEncoderCorrection(encoderSetPoint));
    }

    public boolean isFinished() {
        //Check if ultrasonic values are within tolerance to stop
        if (drivetrain.getUltrasonicDistance() <= distance + tolerance && drivetrain.getUltrasonicDistance() >= distance - tolerance) {
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

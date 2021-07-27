package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoAngleTurn extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double tolerance;
    double angleToTurn;
    double setpoint;
    double Kp = 0.016; // 0.02
    double Ki = 0.0;
    double Kd = 0.001; //0.001
    //Attempt at changing valued from Shuffleboard
    /*private ShuffleboardTab tabPID = Shuffleboard.getTab("PID");
    private NetworkTableEntry maxKp = tabPID.add("Max Kp", 0.001).getEntry();
    double Kp = maxKp.getDouble(0.001);

    private NetworkTableEntry maxKi = tabPID.add("Max Ki", 0.0).getEntry();
    double Ki = maxKi.getDouble(0.0);

    private NetworkTableEntry maxKd = tabPID.add("Max Kd", 0.001).getEntry();
    double Kd = maxKd.getDouble(0.01);*/
    PIDController pid = new PIDController(Kp, Ki, Kd);
    private long startTime;

    public AutoAngleTurn(DrivetrainSubsystem drivetrain, double angleToTurn) {
        this.tolerance = 0.5; //0.5
        this.drivetrain = drivetrain;
        this.angleToTurn = angleToTurn;
        pid.setTolerance(tolerance);
        addRequirements(drivetrain);
//        System.out.println(setpoint);
    }

    public void initialize() {
        setpoint = drivetrain.getGyroAngle() + angleToTurn;
        pid.setSetpoint(setpoint);
        startTime = System.currentTimeMillis();
    }

    public void execute() {

        long elapsedTime = System.currentTimeMillis() - startTime;
        double trapezoidTime = 1000;
        double power;

        if (elapsedTime <= trapezoidTime) {
            power = //pid.calculate(drivetrain.getGyroAngle()) >= setpoint ? // >= 0
                    Math.min(pid.calculate(drivetrain.getGyroAngle()) * (elapsedTime / trapezoidTime), Constants.DriveConstants.maxDriveSpeed); //:
            //Math.max(pid.calculate(drivetrain.getGyroAngle())*(elapsedTime/ trapezoidTime),-Constants.DriveConstants.maxDriveSpeed);
        } else {
            power = //pid.calculate(drivetrain.getGyroAngle()) >= setpoint ? // >= 0
                    Math.min(pid.calculate(drivetrain.getGyroAngle()), Constants.DriveConstants.maxDriveSpeed); //:
            //Math.max(pid.calculate(drivetrain.getGyroAngle()),-Constants.DriveConstants.maxDriveSpeed);
        }
        /*if(power < Constants.DriveConstants.minDrivePower){
            power = Constants.DriveConstants.minDrivePower;
        }*/
        drivetrain.tankDriveTurn(power, -power);
        System.out.println(power);
        SmartDashboard.putNumber("Angle PID Error:", pid.getPositionError());

    }

    public boolean isFinished() {

        if (drivetrain.getGyroAngle() <= setpoint + tolerance && drivetrain.getGyroAngle() >= setpoint - tolerance) {
            return true;
        }
        ;
        return false;
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("Reached setpoint");
    }


}

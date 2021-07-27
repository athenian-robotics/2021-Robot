package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoForwardDistance extends CommandBase {
    private final double trapezoidTime;
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    PIDController pid = new PIDController(1, 0.0, 0.02); // 0.39, 0.0, 0.01
    private double metersToDrive;
    private long startTime;
    private double encoderDifferenceSetpoint;

    public AutoForwardDistance(DrivetrainSubsystem drivetrain, double metersToDrive) {
        this(drivetrain, metersToDrive, 1000);
    }

    public AutoForwardDistance(DrivetrainSubsystem drivetrain, double metersToDrive, double trapezoidTime) {
        this.drivetrain = drivetrain;
        double tolerance = 0.01;
        this.metersToDrive = metersToDrive + tolerance;
        pid.setTolerance(tolerance);
        addRequirements(drivetrain);
        this.trapezoidTime = trapezoidTime;
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
        double power =
                drivetrain.calculateTrapezoid(pid, startTime, Constants.DriveConstants.maxDriveSpeed, this.trapezoidTime);
        //pid.calculate(drivetrain.getRightEncoderDistance());
//        System.out.println(power);
        double leftCorrection = drivetrain.leftEncoderCorrection(encoderDifferenceSetpoint);
        double rightCorrection = drivetrain.rightEncoderCorrection(encoderDifferenceSetpoint);
        drivetrain.tankDrive(power + leftCorrection, power + rightCorrection);
    }

    public boolean isFinished() {
//        System.out.println("finished");
        return pid.atSetpoint();
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        //System.out.println("Reached setpoint");
    }

}

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class TurnToBall extends CommandBase {
    private final LimeLightSubsystem limelight;
    private final DrivetrainSubsystem drivetrain;

    double tolerance;
    double setpoint;
    double validTarget;
    double Kp = 0.035;
    double Ki = 0.0;
    double Kd = 0.001;

    PIDController pid = new PIDController(Kp, Ki, Kd);
    private long startTime;

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    public TurnToBall(LimeLightSubsystem limelight, DrivetrainSubsystem drivetrain) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.limelight = limelight;
        addRequirements(this.limelight);
        this.drivetrain = drivetrain;
        addRequirements(this.drivetrain);
        startTime = System.currentTimeMillis();
        this.tolerance = 0.5;
        pid.setTolerance(tolerance);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        double[] list = this.limelight.grabValues();
        double angleToTurn = list[3];

        double validTarget = list[0];
        this.validTarget = validTarget;

        if (this.validTarget == 1) {
            setpoint = drivetrain.getGyroAngle() + angleToTurn + tolerance;
            pid.setSetpoint(setpoint);
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (this.validTarget == 1) {
            double power = pid.calculate(drivetrain.getGyroAngle());
            drivetrain.tankDrive(power, -power);
        }
        System.out.println(pid.getPositionError());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return ((pid.getPositionError() < 3) && (pid.getPositionError() > -3)) || (System.currentTimeMillis() - startTime > 150);
    }
}


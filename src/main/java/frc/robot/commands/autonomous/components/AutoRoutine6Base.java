package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.commands.autonomous.components.AutoDriveForwardUltrasonic;
import frc.robot.commands.autonomous.components.AutoDumperUp;
import frc.robot.commands.autonomous.components.AutoShooterTime;
import frc.robot.commands.autonomous.routines.AutoRoutine5;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.autonomous.components.AutoForwardDistance;

import java.util.Date;

public class AutoRoutine6Base extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final LimeLightSubsystem limelight;
    private final IntakeSubsystem intake;
    private final ShooterSubsystem shooter;
    private final PIDController pid = new PIDController(1, 0, 0.02);
    private double[] vals;
    private int counter = 0;
    private long time = 0;


    public AutoRoutine6Base(DrivetrainSubsystem drivetrain, IntakeSubsystem intake, ShooterSubsystem shooter, LimeLightSubsystem limelight) {
        addRequirements(drivetrain, intake, shooter, limelight);
        this.drivetrain = drivetrain;
        this.intake = intake;
        this.limelight = limelight;
        this.shooter = shooter;
        this.vals = limelight.grabValues();
    }

    @Override
    public void initialize() {
        new AutoRoutine5(drivetrain, intake);
        limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
        int count2 = 0;
        // while find low goal retro reflector not true, spinny boi
        while (vals[0] == 0) {
            // SPINNN
            drivetrain.tankDriveTurn(.3, -.3);
            vals = limelight.grabValues();
            if (count2 > 15) {
                counter = 250;
            }
            count2++;
        }

        while (vals[0] == 1) {
            // SPINNN
            drivetrain.tankDriveTurn(-.1, .1);
            vals = limelight.grabValues();
        }

        long time = System.currentTimeMillis();
        while (vals[0] == 0) {
            // SPINNN
            drivetrain.tankDriveTurn(.3, -.3);
            vals = limelight.grabValues();
            if (count2 > 15) {
                counter = 250;
            }
            count2++;
        }
        time = System.currentTimeMillis() - System.currentTimeMillis();
        // TODO: replace above with encoder stuff

        // PID
        pid.setSetpoint(0);
        pid.setTolerance(750);
        pid.enableContinuousInput(0, 10_000);
        drivetrain.resetEncoders();
    }

    public void execute() {
        counter++;
        if (vals[3] < 0) {
            drivetrain.tankTurn(-1, 1, (long) MathUtil.clamp(pid.calculate(-vals[3]), 0, 10000));
        } else {
            drivetrain.tankTurn(1, -1, (long) MathUtil.clamp(pid.calculate(vals[3]), 0, 10000));
        }
        vals = limelight.grabValues();
    }

    public boolean isFinished() {
        if (counter >= 250) {
            return true; // worst case scenario, frick
        }
        return pid.atSetpoint();
    }
    public void end() {
        // go forward until close to low goal n shoot
        drivetrain.tankTurn(0.3, -0.3, time);
        new AutoDriveForwardUltrasonic(drivetrain, 8); // goto score
        new AutoForwardDistance(drivetrain, 1); // level up against wall
        new AutoDumperUp(shooter, 300);  // dumper up
        new AutoShooterTime(shooter,1000); // shoot em balls
        // new AutoRoutine6(drivetrain, intake, shooter, limelight);
    }


}

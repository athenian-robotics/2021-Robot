package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShooterTime extends CommandBase {
    private final ShooterSubsystem shooter;
    private final double runtime;
    private long startTime = 0;

    public AutoShooterTime(ShooterSubsystem shooterSubsystem, double runtime) {
        addRequirements(shooterSubsystem);
        this.shooter = shooterSubsystem;
        this.runtime = runtime;
    }

    public void initialize() {
        shooter.startShooter();
        shooter.dumperUp();
        startTime = System.currentTimeMillis();
        System.out.println("start shooter command");
    }

    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= runtime;
    }

    public void end(boolean interrupted) {
        System.out.println("end shooter command");
        shooter.stopShooter();
        shooter.dumperDown();
    }
}

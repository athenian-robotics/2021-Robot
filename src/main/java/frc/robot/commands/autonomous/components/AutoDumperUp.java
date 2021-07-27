package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoDumperUp extends CommandBase {
    private final ShooterSubsystem shooter;
    private long startTime = 0;
    private int delay = 0;

    public AutoDumperUp(ShooterSubsystem shooter, int delay) {
        this.shooter = shooter;
        this.delay = delay;
    }

    public void initialize() {
        shooter.dumperUp();
        this.startTime = System.currentTimeMillis();
    }

    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= delay;
    }
}

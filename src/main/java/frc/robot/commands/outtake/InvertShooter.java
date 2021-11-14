package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class InvertShooter extends CommandBase {
    private final ShooterSubsystem shooter;

    public InvertShooter(ShooterSubsystem shooter) {
        this.shooter = shooter;
    }

    @Override
    public void initialize() {
        shooter.invertShooter();
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopShooter();
    }
}

package frc.robot.commands.outtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class DumperCommand extends CommandBase {

    private boolean isEnabled = false;
    private ShooterSubsystem shooter;

    public DumperCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.dumperUp();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println(isEnabled ? "Backward" : "Forward");
        if (isEnabled) {
            shooter.dumperUp();
        } else {
            shooter.dumperDown();
        }
        isEnabled = !isEnabled;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}

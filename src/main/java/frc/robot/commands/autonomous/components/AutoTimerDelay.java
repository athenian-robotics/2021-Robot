package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoTimerDelay extends CommandBase {
    private final double secondsToDelay;

    public AutoTimerDelay(double seconds) {
        this.secondsToDelay = seconds;
    }

    @Override
    public void initialize() {
        Timer.delay(secondsToDelay);
    }

    @Override
    public void execute() { }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}

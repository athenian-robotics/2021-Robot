package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoTimerDelay extends CommandBase {

    public AutoTimerDelay() { }

    @Override
    public void initialize() {
        Timer.delay(0.5);
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

    }
}

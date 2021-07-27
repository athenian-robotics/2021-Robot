package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDelay extends CommandBase {
    private final int delay;
    private long startTime;

    public AutoDelay(int delay) {
        this.delay = delay;
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
    }


    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > delay;
    }
}

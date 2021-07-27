package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightSubsystem;

public class ToggleLimelightLED extends CommandBase {
    LimeLightSubsystem limelight;

    public ToggleLimelightLED(LimeLightSubsystem limelight) {
        this.limelight = limelight;
    }

    public void initialize() {
        int currentPipeline = limelight.grabNetworkTable().getEntry("pipeline").getNumber(0).intValue();
        if (currentPipeline == 0) {
            this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(1);
        } else {
            this.limelight.grabNetworkTable().getEntry("pipeline").setNumber(0);
        }
    }
}

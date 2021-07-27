package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDeadReckoning extends CommandBase {
    private final DrivetrainSubsystem drive;
    private final double power;

    public AutoDeadReckoning(DrivetrainSubsystem drive, double power) {
        this.drive = drive;
        this.power = power;
    }

    public void execute() {
        System.out.println("Initialize");
        drive.tankDrive(power, power);
    }

    public void end(boolean interrupted) {
        drive.tankDrive(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}

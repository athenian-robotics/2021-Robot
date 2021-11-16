package frc.robot.commands.autonomous.components;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoFindTurnStaticFriction extends CommandBase {
    private final DrivetrainSubsystem drive;
    double power = 0.01;

    public AutoFindTurnStaticFriction(DrivetrainSubsystem drive) {
        this.drive = drive;
    }

    public void execute() {
        System.out.println("Initialize");
        drive.drive.tankDrive(power, -power);
        power += 0.0002;
        System.out.println(power);
    }

    public void end(boolean interrupted) {
        drive.tankDrive(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}

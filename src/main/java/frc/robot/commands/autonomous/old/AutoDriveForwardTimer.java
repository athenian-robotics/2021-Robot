package frc.robot.commands.autonomous.old;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveForwardTimer extends CommandBase {
    DrivetrainSubsystem drivetrain;
    Timer driveTimer = new Timer();
    double secondsTodrive;

    public AutoDriveForwardTimer(DrivetrainSubsystem drivetrain, double secondsToDrive) {
        this.drivetrain = drivetrain;
        this.secondsTodrive = secondsToDrive;
        addRequirements(drivetrain);

    }

    public void initialize() {
        driveTimer.reset();
        driveTimer.start();
    }

    public void execute() {
        drivetrain.tankDrive(0.4, 0.4);

    }

    public boolean isFinished() {
        return driveTimer.hasPeriodPassed(secondsTodrive);
    }

    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

}

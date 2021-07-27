package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class Abort extends CommandBase {
    public Abort(ShooterSubsystem shooterSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem,
                 ColorWheelSubsystem colorWheelSubsystem, LeftTelescopeSubsystem leftTelescopeSubsystem,
                 RightTelescopeSubsystem rightTelescopeSubsystem, LeftWinchSubsystem leftWinchSubsystem,
                 RightWinchSubsystem rightWinchSubsystem) {
        addRequirements(shooterSubsystem, drivetrainSubsystem, intakeSubsystem, colorWheelSubsystem, leftTelescopeSubsystem, leftWinchSubsystem, rightTelescopeSubsystem, rightWinchSubsystem);
    }

    public void initialize() {
    }

    public boolean isFinished() {
        return true;
    }

}

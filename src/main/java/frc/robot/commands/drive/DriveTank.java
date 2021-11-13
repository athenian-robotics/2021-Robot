package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class DriveTank extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final XboxController xboxController;


    public DriveTank(DrivetrainSubsystem drivetrain, XboxController xboxController) {
        this.drivetrainSubsystem = drivetrain;
        this.xboxController = xboxController;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        drivetrainSubsystem.tankDrive(-xboxController.getY(GenericHID.Hand.kLeft),
                -xboxController.getY(GenericHID.Hand.kRight)); //Set both parameters negative to invert
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
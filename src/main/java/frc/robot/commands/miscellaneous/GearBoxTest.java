package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class GearBoxTest extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DrivetrainSubsystem drivetrain;
    private final XboxController xboxController;
    private double leftdirection;
    private double rightdirection;

    public GearBoxTest(DrivetrainSubsystem drivetrain, XboxController xboxController, double leftdirection, double rightdirection) {
        this.drivetrain = drivetrain;
        this.xboxController = xboxController;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
        this.leftdirection = leftdirection;
        this.rightdirection = rightdirection;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrain.tankDrive(0, 0);
        Timer.delay(0.1);
        drivetrain.tankDrive(leftdirection, rightdirection);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
package frc.robot.commands.miscellaneous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class ButtonDriveTest extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DrivetrainSubsystem drivetrain;
    private final double leftMotorSpeed;
    private final double rightMotorSpeed;


    public ButtonDriveTest(DrivetrainSubsystem drivetrain, double leftMotorSpeed, double rightMotorSpeed) {
        this.drivetrain = drivetrain;
        this.leftMotorSpeed = leftMotorSpeed;
        this.rightMotorSpeed = rightMotorSpeed;
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drivetrain.tankDrive(0, 0);
        Timer.delay(0.1);
        drivetrain.tankDrive(leftMotorSpeed, rightMotorSpeed);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.tankDrive(0, 0);
        System.out.println("END");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
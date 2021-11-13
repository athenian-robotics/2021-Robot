package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoForwardDistance;
import frc.robot.commands.autonomous.components.AutoIntakeOn;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoRoutine5 extends SequentialCommandGroup {
    public AutoRoutine5(DrivetrainSubsystem drivetrain, IntakeSubsystem intake) {
        addRequirements(drivetrain);
        addCommands(
                new AutoIntakeOn(intake),
                new AutoForwardDistance(drivetrain, 6),
                new AutoForwardDistance(drivetrain, -4)
        );

    }
}

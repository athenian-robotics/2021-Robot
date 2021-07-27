package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoForwardDistance;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoRoutine0 extends SequentialCommandGroup {
    public AutoRoutine0(DrivetrainSubsystem drivetrain) {
        addRequirements(drivetrain);
        addCommands(
                new AutoForwardDistance(drivetrain, 0.75)
        );

    }


}

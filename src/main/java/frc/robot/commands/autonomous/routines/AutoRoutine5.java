package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoForwardDistance;
import frc.robot.commands.autonomous.components.AutoIntakeOff;
import frc.robot.commands.autonomous.components.AutoIntakeOn;
import frc.robot.commands.autonomous.components.AutoTimerDelay;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;



public class AutoRoutine5 extends SequentialCommandGroup {
    private final Timer timer = new Timer();
    public AutoRoutine5(DrivetrainSubsystem drivetrain, IntakeSubsystem intake) {
        addRequirements(drivetrain);
        addCommands(
                new AutoIntakeOn(intake),
                new AutoForwardDistance(drivetrain, 6),
                new AutoTimerDelay(0.5),
                new AutoForwardDistance(drivetrain, -4),
                new AutoIntakeOff(intake)
        );

    }
}

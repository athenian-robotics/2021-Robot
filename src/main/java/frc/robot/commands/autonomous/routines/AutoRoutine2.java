package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.components.AutoAngleTurn;
import frc.robot.commands.autonomous.components.AutoForwardDistance;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoRoutine2 extends SequentialCommandGroup {
    public AutoRoutine2(DrivetrainSubsystem drivetrain, ShooterSubsystem shooter, IntakeSubsystem intake) {
        addRequirements(drivetrain, shooter);

        final double FIELD_WIDTH = 8.21;
        final double POWER_PORT_LOCATION = 2.4;

        final double distanceFromWallInches = 129;
        double distanceFromWall = distanceFromWallInches * 0.0254;
        boolean rightTurn = false;
        double distanceFromPowerPort = rightTurn
                ? POWER_PORT_LOCATION - distanceFromWall
                : FIELD_WIDTH - POWER_PORT_LOCATION - distanceFromWall;

        if (distanceFromPowerPort == 0) {
            addCommands(new AutoRoutine1(drivetrain, shooter, intake));
        } else {
            addCommands(
                    new AutoForwardDistance(drivetrain, -distanceFromPowerPort),
                    new AutoAngleTurn(drivetrain, rightTurn ? 90 : -90),
                    new AutoRoutine1(drivetrain, shooter, intake)
            );
        }
    }
}

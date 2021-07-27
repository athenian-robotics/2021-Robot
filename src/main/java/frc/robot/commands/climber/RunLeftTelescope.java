package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LeftTelescopeSubsystem;

import static frc.robot.lib.controllers.FightStick.POVDown;
import static frc.robot.lib.controllers.FightStick.POVUp;

public class RunLeftTelescope extends CommandBase {
    LeftTelescopeSubsystem leftTelescopeSubsystem;

    public RunLeftTelescope(LeftTelescopeSubsystem leftTelescopeSubsystem) {
        this.leftTelescopeSubsystem = leftTelescopeSubsystem;
        addRequirements(leftTelescopeSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        int pov = RobotContainer.xboxController.getPOV();
        if (pov == 0) {
            leftTelescopeSubsystem.leftTelescopeUp();
        } else if (pov == 180) {
            leftTelescopeSubsystem.leftTelescopeDown();
            ;
        } else {
            leftTelescopeSubsystem.leftTelescopeStop();
            ;
        }
        if (POVUp.get()) {
            leftTelescopeSubsystem.leftTelescopeUp();
        } else if (POVDown.get()) {
            leftTelescopeSubsystem.leftTelescopeDown();
        } else {
            leftTelescopeSubsystem.leftTelescopeStop();
        }

    }

    public boolean isFinished() {
        return false;
    }


    public void end(boolean interrupted) {
        leftTelescopeSubsystem.leftTelescopeStop();
    }

}

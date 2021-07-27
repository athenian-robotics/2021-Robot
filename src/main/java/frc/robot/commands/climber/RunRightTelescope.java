package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RightTelescopeSubsystem;

import static frc.robot.lib.controllers.FightStick.*;


public class RunRightTelescope extends CommandBase {
    RightTelescopeSubsystem rightTelescopeSubsystem;

    public RunRightTelescope(RightTelescopeSubsystem rightTelescopeSubsystem) {
        this.rightTelescopeSubsystem = rightTelescopeSubsystem;
        addRequirements(rightTelescopeSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        int pov = RobotContainer.xboxController.getPOV();
        if (pov == 0) {
            rightTelescopeSubsystem.rightTelescopeUp();
        } else if (pov == 180) {
            rightTelescopeSubsystem.rightTelescopeDown();
            ;
        } else {
            rightTelescopeSubsystem.rightTelescopeStop();
            ;
        }
        if (POVCenter.get()) {
            rightTelescopeSubsystem.rightTelescopeStop();
        }
        if (POVUp.get()) {
            rightTelescopeSubsystem.rightTelescopeUp();
        }
        if (POVDown.get()) {
            rightTelescopeSubsystem.rightTelescopeDown();
        }
    }

    public boolean isFinished() {
        return false;
    }


    public void end(boolean interrupted) {
        rightTelescopeSubsystem.rightTelescopeStop();
    }
}

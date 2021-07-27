package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RightWinchSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunRightWinch extends CommandBase {
    RightWinchSubsystem rightWinchSubsystem;

    public RunRightWinch(RightWinchSubsystem rightWinchSubsystem) {
        this.rightWinchSubsystem = rightWinchSubsystem;
        addRequirements(rightWinchSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (POVCenter.get()) {
            rightWinchSubsystem.rightWinchStop();
        }
        if (POVDown.get()) {
            System.out.println("Right Winch Up");
            rightWinchSubsystem.rightWinchRetract();
        }
        if (POVUp.get()) {
            System.out.println("Right Winch Down");
            rightWinchSubsystem.rightWinchExtend();
        }
    }

    public void end(boolean interrupted) {
        rightWinchSubsystem.rightWinchStop();
    }

}

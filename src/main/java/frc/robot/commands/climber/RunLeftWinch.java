package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LeftWinchSubsystem;

import static frc.robot.lib.controllers.FightStick.*;

public class RunLeftWinch extends CommandBase {
    LeftWinchSubsystem leftWinchSubsystem;

    public RunLeftWinch(LeftWinchSubsystem leftWinchSubsystem) {
        this.leftWinchSubsystem = leftWinchSubsystem;
        addRequirements(leftWinchSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (POVCenter.get()) {
            leftWinchSubsystem.leftWinchStop();
        }
        if (POVDown.get()) {
            leftWinchSubsystem.leftWinchRetract();
            System.out.println("Left Winch Up");
        }
        if (POVUp.get()) {
            leftWinchSubsystem.leftWinchExtend();
            System.out.println("Left Winch Down");
        }
    }

    public void end(boolean interrupted) {
        leftWinchSubsystem.leftWinchStop();
    }
}

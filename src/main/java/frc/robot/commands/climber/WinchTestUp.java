//package frc.robot.commands.climber;
//
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.DrivetrainSubsystem;
//
//import static frc.robot.Constants.MechanismConstants.*;
//
//
//public class WinchTestUp extends CommandBase {
//
//    private static CANSparkMax leftWinch = new CANSparkMax(leftWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);
//    private static CANSparkMax rightWinch = new CANSparkMax(rightWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);
//    private boolean isEnabled = false;
//
//    @Override
//    public void initialize() {
//        System.out.println("Initialize");
//    }
//
//    // Called every time the scheduler runs while the command is scheduled.
//    @Override
//    public void execute() {
//        if(isEnabled){
//            leftWinch.set(0);
//            rightWinch.set(0);
//            isEnabled = !isEnabled;
//            System.out.println("Stop Winch");
//        }
//        else{
//            leftWinch.set(0.25);
//            rightWinch.set(0.25);
//            isEnabled = !isEnabled;
//            System.out.println("Go Winch");
//        }
//    }
//
//    // Called once the command ends or is interrupted.
//    @Override
//    public void end(boolean interrupted) {
//
//    }
//
//    // Returns true when the command should end.
//    @Override
//    public boolean isFinished() {
//        return true;
//    }
//}
//package frc.robot.commands.color_wheel;
//
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.ColorWheelSubsystem;
//
//import static frc.robot.Constants.MechanismConstants.colorWheelMotorPort;
//import static frc.robot.lib.controllers.FightStick.*;
//
//
//public class ColorWheelTest extends CommandBase {
//
//    private static CANSparkMax wheelMotor = new CANSparkMax(colorWheelMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
//    private boolean isEnabled = false;
//    public ColorWheelTest() {
//
//    }
//
//    @Override
//    public void initialize() {
//        //colorWheel.sensorLiftUp();
//    }
//
//    // Called every time the scheduler runs while the command is scheduled.
//    @Override
//    public void execute() {
//        if(isEnabled) {
//            wheelMotor.set(0);
//            isEnabled = !isEnabled;
//        }
//        else{
//            wheelMotor.set(1);
//            isEnabled = !isEnabled;
//        }
//    }
//
//    // Called once the command ends or is interrupted.
//    @Override
//    public void end(boolean interrupted) {
//        wheelMotor.set(0);
//    }
//
//    // Returns true when the command should end.
//    @Override
//    public boolean isFinished() {
//        return true;
//    }
//}

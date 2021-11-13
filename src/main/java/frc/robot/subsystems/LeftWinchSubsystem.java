package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.leftWinchMotorPort;

public class LeftWinchSubsystem extends SubsystemBase {

    private final CANSparkMax leftWinch = new CANSparkMax(leftWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);

    public LeftWinchSubsystem() {
    }

    public void leftWinchExtend() {
        leftWinch.set(-0.65);
    }

    public void leftWinchRetract() {
        leftWinch.set(0.65);
    }

    public void leftWinchStop() {
        leftWinch.set(0);
    }

}

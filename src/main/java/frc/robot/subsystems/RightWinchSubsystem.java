package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.rightWinchMotorPort;

public class RightWinchSubsystem extends SubsystemBase {

    private final CANSparkMax rightWinch = new CANSparkMax(rightWinchMotorPort, CANSparkMaxLowLevel.MotorType.kBrushed);

    public RightWinchSubsystem() {
    }

    public void rightWinchExtend() {
        rightWinch.set(0.5);
    }

    public void rightWinchRetract() {
        rightWinch.set(-0.5);
    }

    public void rightWinchStop() {
        rightWinch.set(0);
    }
}

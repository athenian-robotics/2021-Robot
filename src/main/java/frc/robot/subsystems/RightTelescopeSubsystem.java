package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.rightTelescopeMotorPort;

public class RightTelescopeSubsystem extends SubsystemBase {

    private final CANSparkMax rightTelescope = new CANSparkMax(rightTelescopeMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANEncoder rightTelescopeEncoder = rightTelescope.getEncoder();

    public RightTelescopeSubsystem() {
        rightTelescopeEncoder.setPosition(0.0);
    }

    public void rightTelescopeUp() {
        if (getEncoderValue() > -175) {
            rightTelescope.set(-0.6);
        } else {
            rightTelescope.set(0);
        }
    }

    public void rightTelescopeDown() {
        if (getEncoderValue() < 0) {
            rightTelescope.set(0.6);
        } else {
            rightTelescope.set(0);
        }
    }

    public void rightTelescopeStop() {
        rightTelescope.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Telescope Encoder", rightTelescopeEncoder.getPosition());
    }

    public double getEncoderValue() {
        return rightTelescopeEncoder.getPosition();
    }
}

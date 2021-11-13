package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.leftTelescopeMotorPort;

public class LeftTelescopeSubsystem extends SubsystemBase {

    private final CANSparkMax leftTelescope = new CANSparkMax(leftTelescopeMotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANEncoder leftTelescopeEncoder = leftTelescope.getEncoder();

    public LeftTelescopeSubsystem() {
        leftTelescopeEncoder.setPosition(0.0);
    }

    public void leftTelescopeUp() {
        if (getEncoderValue() > -175) {
            leftTelescope.set(-0.6);
        } else {
            leftTelescope.set(0);
        }
    }

    public void leftTelescopeDown() {
        if (getEncoderValue() < 0) {
            leftTelescope.set(0.6);
        } else {
            leftTelescope.set(0);
        }
    }

    public void leftTelescopeStop() {
        leftTelescope.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Telescope Encoder", leftTelescopeEncoder.getPosition());
    }

    public double getEncoderValue() {
        return leftTelescopeEncoder.getPosition();
    }
}

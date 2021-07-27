package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class VideoControl extends SubsystemBase {

    private final Servo limelight = new Servo(1);

    public void servoUp() {
        limelight.set(limelight.get() - 0.02);
    }

    public void servoDown() {
        limelight.set(limelight.get() + 0.02);
    }

    public void servoStop() {
        limelight.set(limelight.get());
    }

    @Override
    public void periodic() {
        int pov = RobotContainer.xboxController.getPOV();
        if (pov == 0) {
            if (limelight.get() > 0) {
                servoUp();
            }
        } else if (pov == 180) {
            if (limelight.get() < 0.35) {
                servoDown();
            }
        } else {
            servoStop();
        }
        SmartDashboard.putNumber("Limelight Angle", limelight.get());
    }

}

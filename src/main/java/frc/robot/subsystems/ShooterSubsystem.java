
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.MechanismConstants.shooterMotorPort;
import static frc.robot.Constants.PneumaticsConstants.dumperPort;
import static frc.robot.Constants.PneumaticsConstants.gatePort;

public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shooterMotor = new CANSparkMax(shooterMotorPort, kBrushless);
    private final Solenoid dumperSolenoid = new Solenoid(dumperPort);
    private final Solenoid gateSolenoid = new Solenoid(gatePort);
    private boolean isRunning = false;
    private boolean isOpen = false;
    private boolean isUp = false;

    public ShooterSubsystem() {
        shooterMotor.setInverted(false);
        //final motor_placeholder shooterMotor;
        //final pneumatic_placeholder shooterPiston;

    }

    public void startShooter() {
        startShooter(1.0);
    }

    public void startShooter(double power) {
        shooterMotor.set(power);
        System.out.println("Low goal shooter now shooting!");
        isRunning = true;
    }

    public void stopShooter() {
        shooterMotor.set(0);
        System.out.println("Low goal shooter now not shooting!");
        isRunning = false;
//        solenoidlift.set((DoubleSolenoid.Value.kReverse));
    }

    public void invertShooter() {
        if (shooterMotor.get() < 0) shooterMotor.set(1.0);
        else shooterMotor.set(-1.0);
        System.out.println("Inverting shooter!");
        isRunning = true;
    }

    public void dumperUp() {
        dumperSolenoid.set(true);
        isUp = true;
    }

    public void dumperDown() {
        dumperSolenoid.set(false);
        isUp = false;
    }

    public void gateUp() {
        gateSolenoid.set(true);
        isOpen = true;
    }

    public void gateDown() {
        gateSolenoid.set(false);
        isOpen = false;
    }

    public void toggleGate() {
        if (isOpen) {
            gateDown();
        } else {
            gateUp();
        }
    }

    public void toggleShooter() {
        if (isRunning) {
            stopShooter();
            dumperDown();
        } else {
            startShooter();
            dumperUp();
        }
    }

    public void toggleDumper() {
        if (isUp) {
            dumperDown();
        } else {
            dumperUp();
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void invert() {
        shooterMotor.setInverted(!shooterMotor.getInverted());
    }
}
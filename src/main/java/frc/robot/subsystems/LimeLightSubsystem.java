package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putNumber;

// See http://docs.limelightvision.io/en/latest/networktables_api.html

public class LimeLightSubsystem extends SubsystemBase {

    final NetworkTable limelight;

    double tv;
    double ta;
    double ts;
    double tx;
    double ty;
    NetworkTableEntry camTran;
    double[] helpme = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    //I'm really not sure if the names match with the values, we should check this later
    double targetX;
    double targetY;
    double targetZ;
    double targetPitch;
    double targetYaw;
    double targetRoll;

    public LimeLightSubsystem(String tableName) {
        this.limelight = NetworkTableInstance.getDefault().getTable(tableName);
    }

    public void periodic() {
        this.tv = limelight.getEntry("tv").getDouble(-1.1);
        this.ta = limelight.getEntry("ta").getDouble(-1.1);
        this.ts = limelight.getEntry("ts").getDouble(-1.1);
        this.tx = limelight.getEntry("tx").getDouble(-1.1);
        this.ty = limelight.getEntry("ty").getDouble(-1.1);
        NetworkTableEntry camTran = limelight.getEntry("camtran");
        double[] helpme = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] pnpvalues = camTran.getDoubleArray(helpme);

        //I'm really not sure if the names match with the values, we should check this later
        if (pnpvalues[0] != 0) {
            this.targetX = pnpvalues[0];
            this.targetY = pnpvalues[1];
            this.targetZ = pnpvalues[2];
            this.targetPitch = pnpvalues[3];
            this.targetYaw = pnpvalues[4];
            this.targetRoll = pnpvalues[5];
        }

        putNumber("Valid Target", this.tv);
        putNumber("Target Area", this.ta);
        putNumber("Image Rotation", this.ts);
        putNumber("Horizontal Crosshair Offset", this.tx);
        putNumber("Vertical Crosshair Offset", this.ty);
        putNumber("Target X", this.targetX);
        putNumber("Target Y", this.targetY);
        putNumber("Target Z", this.targetZ);
        putNumber("Target Pitch", this.targetPitch);
        putNumber("Target Yaw", this.targetYaw);
        putNumber("Target Roll", this.targetRoll);
    }

    public double[] grabValues() {
        double[] list = {this.tv, this.ta, this.ts, this.tx, this.ty, this.targetX, this.targetY, this.targetZ, this.targetPitch, this.targetYaw, this.targetRoll};
        return list;
    }

    public NetworkTable grabNetworkTable() {
        return this.limelight;
    }
}

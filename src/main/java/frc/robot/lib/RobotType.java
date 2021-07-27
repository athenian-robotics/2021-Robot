package frc.robot.lib;

public enum RobotType {
    JANKBOT(false), KITBOT(false), TESTBOT(true), OFFICIAL(false);

    private final boolean inverted;

    RobotType(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean isInverted() {
        return inverted;
    }
}



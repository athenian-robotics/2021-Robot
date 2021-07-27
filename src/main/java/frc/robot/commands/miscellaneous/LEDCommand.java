package frc.robot.commands.miscellaneous;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends CommandBase {


    private LEDSubsystem ledSubsystem;
    //private static final SmartDashboard sIsBlueTeam = new SmartDashboard(LEDCommand.class, "isBlueTeam", false);


    public LEDCommand(LEDSubsystem ledSubsystem) {
        addRequirements(ledSubsystem);
        this.ledSubsystem = ledSubsystem;//statusLEDs = RobotContainer.statusLEDs;
    }

    @Override
    public void execute() {
        ledSubsystem.setColor(LEDSubsystem.LedColors.ORANGE);
        if (DrivetrainSubsystem.movingAverageUltrasonic < 8) {
            ledSubsystem.setColor(LEDSubsystem.LedColors.RED_STROBE);
        }
        /*if (RobotMap.ledError) {
            statusLEDs.setColor(LEDSubsystem.LedColors.RED_STROBE);
        } else if(RobotMap.isInOverride){
            statusLEDs.setColor(LEDSubsystem.LedColors.WHITE_STROBE);
        } else if (inHabRange.get()) {
            statusLEDs.setColor(LEDSubsystem.LedColors.GOLD_STROBE);
        } else if (inStationRange.get()) {
            statusLEDs.setColor(LEDSubsystem.LedColors.GREEN);
        } else if (!Ball.ballIn) {
            statusLEDs.setColor(LEDSubsystem.LedColors.RAINBOW);
        } else {
            if (sIsBlueTeam.getB())
                statusLEDs.setColor(LEDSubsystem.LedColors.BLUE);
            else
                statusLEds.setColor(LEDSubsystem.LedColors.DARK_RED);
        }*/
    }

    @Override
    public void end(boolean interrupted) {
        ledSubsystem.setColor(LEDSubsystem.LedColors.RAINBOW);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}


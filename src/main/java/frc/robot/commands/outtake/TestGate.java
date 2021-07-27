//package frc.robot.commands.outtake;
//
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.ShooterSubsystem;
//
//import static frc.robot.Constants.PneumaticsConstants.gatePort;
//
//public class TestGate extends CommandBase {
//
//   // private ShooterSubsystem shooter;
//    private Solenoid solenoid = new Solenoid(gatePort);
//    boolean isEnabled = false;
//
//    public TestGate() {
//
//    }
//
//    @Override
//    public void initialize() {
//        solenoid.set(false);
//    }
//
//    // Called every time the scheduler runs while the command is scheduled.
//    @Override
//    public void execute() {
//        if(isEnabled){
//            solenoid.set(false);
//        }
//        else{
//            solenoid.set(true);
//        }
//        isEnabled = !isEnabled;
//    }
//
//    // Called once the command ends or is interrupted.
//    @Override
//    public void end(boolean interrupted) { }
//
//    // Returns true when the command should end.
//    @Override
//    public boolean isFinished() {
//        return true;
//    }
//}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.autonomous.components.AutoAngleTurn;
import frc.robot.commands.autonomous.routines.*;
import frc.robot.commands.climber.RunLeftTelescope;
import frc.robot.commands.climber.RunLeftWinch;
import frc.robot.commands.climber.RunRightTelescope;
import frc.robot.commands.climber.RunRightWinch;
import frc.robot.commands.drive.*;
import frc.robot.commands.intake.ChangeIntakeMode;
import frc.robot.commands.intake.IntakeBackward;
import frc.robot.commands.miscellaneous.Abort;
import frc.robot.commands.miscellaneous.LEDCommand;
import frc.robot.commands.outtake.DumperCommand;
import frc.robot.commands.outtake.InvertShooter;
import frc.robot.commands.outtake.ShootLowGoal;
import frc.robot.commands.vision.TurnToBall;
import frc.robot.lib.RobotType;
import frc.robot.lib.controllers.FightStick;
import frc.robot.subsystems.*;

import static frc.robot.lib.controllers.FightStick.*;

//import frc.robot.commands.climber.WinchTestUp;
//import frc.robot.commands.outtake.TestGate;

//import frc.robot.subsystems.AutonomousDrivetrainSubsystem;
//import frc.robot.subsystems.ColorWheelSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private static final RobotType ROBOT_TYPE = RobotType.KITBOT;
    public static JoystickButton xboxA;
    public static JoystickButton xboxB;
    public static JoystickButton xboxX;
    public static JoystickButton xboxY;
    public static JoystickButton xboxLB;
    public static JoystickButton xboxRB;
    public static JoystickButton xboxSquares;
    public static JoystickButton xboxBurger;
    public static Trigger xboxLS;
    public static XboxController.Axis xboxRS;
    // Define all OI devices here
    public static XboxController xboxController = new XboxController(OIConstants.xboxControllerPort);
    public static FightStick fightStick = new FightStick();
    public static Spark statusLEDs = new Spark(0);
    //LEDs
    public final LEDSubsystem ledSubsystem = new LEDSubsystem();
    // The robot's subsystems and commands are defined here...
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(ROBOT_TYPE);
    private final LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem("limelight-two");
    //private final AutonomousDrivetrainSubsystem autodrive = new AutonomousDrivetrainSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final ColorWheelSubsystem colorWheelSubsystem = new ColorWheelSubsystem();
    private final LeftTelescopeSubsystem leftTelescopeSubsystem = new LeftTelescopeSubsystem();
    private final RightTelescopeSubsystem rightTelescopeSubsystem = new RightTelescopeSubsystem();
    private final LeftWinchSubsystem leftWinchSubsystem = new LeftWinchSubsystem();
    private final RightWinchSubsystem rightWinchSubsystem = new RightWinchSubsystem();
    private final VideoControl videoSubsystem = new VideoControl();
    SendableChooser<SequentialCommandGroup> chooser = new SendableChooser<>();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        buttonSetup();
        configureButtonBindings();
        SmartDashboard.putData("AutoChooser", chooser);
        chooser.setDefaultOption("0: CrossLine", new AutoRoutine0(drivetrain));
        chooser.addOption("1: ShootPreloadsStraight", new AutoRoutine1(drivetrain, shooterSubsystem, intakeSubsystem));
        chooser.addOption("2: ShootPreloadsTurn", new AutoRoutine2(drivetrain, shooterSubsystem, intakeSubsystem));
        chooser.addOption("3: GrabTwoStraightShoot", new AutoRoutine3(drivetrain, shooterSubsystem, intakeSubsystem));
        chooser.addOption("4: GrabTwoTrenchShoot", new AutoRoutine4(drivetrain, shooterSubsystem, intakeSubsystem));
        chooser.addOption("5: DriveForwardTrench", new AutoRoutine5(drivetrain, intakeSubsystem));
        chooser.addOption("6: DriveForwardTrenchShootLowGoal", new AutoRoutine6(drivetrain, intakeSubsystem, shooterSubsystem, limeLightSubsystem));


        //  CommandScheduler.getInstance().registerSubsystem(colorWheelSubsystem, shooterSubsystem);
        //  We do not need to register subsystems, this is done automatically

        drivetrain.setDefaultCommand(new DriveArcade(drivetrain, xboxController));
        ledSubsystem.setDefaultCommand(new LEDCommand(ledSubsystem));
        //TODO: Figure out how to change command of drivetrain, create a button for switching
        //TODO: Implement arcade drive
    }

    public static void disableAll(RobotContainer robotContainer) {
        robotContainer.leftTelescopeSubsystem.leftTelescopeStop();
        robotContainer.rightTelescopeSubsystem.rightTelescopeStop();
        robotContainer.leftTelescopeSubsystem.leftTelescopeStop();
        robotContainer.leftWinchSubsystem.leftWinchStop();
        robotContainer.drivetrain.tankDrive(0, 0);
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */

    private void buttonSetup() {
        xboxA = new JoystickButton(xboxController, 1);
        xboxB = new JoystickButton(xboxController, 2);
        xboxX = new JoystickButton(xboxController, 3);
        xboxY = new JoystickButton(xboxController, 4);
        xboxLB = new JoystickButton(xboxController, 5);
        xboxRB = new JoystickButton(xboxController, 6);
        xboxSquares = new JoystickButton(xboxController, 7);
        xboxBurger = new JoystickButton(xboxController, 8);
        xboxLS = new Trigger();
//    xboxRS = new Ax();
    }

    private void configureButtonBindings() {


        //Xbox Controls
        xboxY.whenPressed(new FastTurnSpeed());
        xboxX.whenPressed(new SlowTurnSpeed());
        //xboxRS.whileActiveContinuous(new RunIntake(intakeSubsystem));
//    xboxLS.whenHeld(new FunctionalCommand(
//            intakeSubsystem::invert,
//            () -> {
//            },
//            interrupted -> intakeSubsystem.invert(),
//            () -> false,
//            intakeSubsystem));
        xboxRB.whenPressed(new SetIntakeForward());
        xboxLB.whenPressed(new SetShooterForward());
        xboxBurger.whenPressed(new TurnToBall(limeLightSubsystem, drivetrain));
        xboxSquares.whenPressed(new Abort(shooterSubsystem, drivetrain, intakeSubsystem, colorWheelSubsystem, leftTelescopeSubsystem, rightTelescopeSubsystem, leftWinchSubsystem, rightWinchSubsystem));
//    xboxA.whenPressed(new AutoRoutine2(drivetrain, shooterSubsystem, intakeSubsystem));
        xboxB.whenPressed(new AutoAngleTurn(drivetrain, 90));

        //xboxB.whenPressed(new AutoDriveForwardDistanceCustomTrapezoid(drivetrain, 1));
        //xboxA.whenPressed(new TestAutonomousRoutine(drivetrain, 90, 15, 3.5, 3));

        //FIGHT STICK CONTROLS

        fightStickA.whenPressed(new ChangeIntakeMode(intakeSubsystem, shooterSubsystem));
        fightStickB.whenPressed(new DumperCommand(shooterSubsystem));
        fightStickX.whenPressed(new ShootLowGoal(shooterSubsystem));
        fightStickY.whenHeld(new InvertShooter(shooterSubsystem));
//    fightStickLB.whenHeld(new RunLeftTelescope(leftClimberSubsystem));
//    fightStickRB.whenHeld(new RunRightTelescope(rightClimberSubsystem));
        fightStickLB.whenHeld(new RunLeftTelescope(leftTelescopeSubsystem));
        fightStickRB.whenHeld(new RunRightTelescope(rightTelescopeSubsystem));

        fightStickLT.whileActiveContinuous(new RunLeftWinch(leftWinchSubsystem));
        fightStickRT.whileActiveContinuous(new RunRightWinch(rightWinchSubsystem));

        // When held, this command changes the intake to backward (note: it does not change the status of the intake [on/off], just the direction)
        fightStickOption.whenPressed(new IntakeBackward(intakeSubsystem));


//    fightStickOption.whenHeld(new FunctionalCommand(
//            intakeSubsystem::invert,
//            () -> {
//            },
//            interrupted -> intakeSubsystem.invert(),
//            () -> false,
//            intakeSubsystem));

        // When held, this command changes the intake to backward, but doesn't change the speed/status
        fightStickShare.whenHeld(new FunctionalCommand(
                () -> {
                },
                () -> {
                },
                interrupted -> {
                    shooterSubsystem.invert();
                    shooterSubsystem.startShooter(1.0);
                },
                () -> false,
                intakeSubsystem));

    /*xboxA.whenPressed(new FunctionalCommand(
            shooterSubsystem::gateUp,
            () -> {},
            interrupted -> {},
            () -> true,
            shooterSubsystem));*/

        //xboxB.whenPressed(new GateCommand());
        //xboxX.whenPressed(new ChangeIntakeMode(intakeSubsystem));
        //xboxLB.whenPressed(new ShootLowGoal(shooterSubsystem));
        //xboxY.whenPressed(new RunColorWheel(colorWheelSubsystem));

        //XBOX CONTROLS
        //Change drive mode
        //xboxLB.whenPressed(new DriveTank(drivetrain, xboxController));
        //xboxRB.whenPressed(new DriveArcade(drivetrain, xboxController));


        //Autonomous Controls
        //xboxA.whenPressed(new FollowTrajectory(drivetrain).ExampleAutonomousCommand());
        //xboxA.whenPressed(new PathWeaver());
        //xboxA.whenPressed(new AutoDriveForwardDistance(drivetrain, 1.05));
        //xboxB.whenPressed(new AutoDriveForwardDistanceTrapezoid(drivetrain, 1.05));
        //xboxY.whenPressed(new AutoTurnAngle(drivetrain, 90));

        /**
         * ButtonDriveTest xbox controller Mapping
         * Uncomment as needed, make sure ROBOT_TYPE is correctly assigned!
         */
        //xboxA.whenPressed(new ButtonDriveTest(drivetrain, 0.0, 0.0));
        //xboxX.whenPressed(new ButtonDriveTest(drivetrain, 0.4, 0.4));
        //xboxB.whenPressed(new ButtonDriveTest(drivetrain, -0.4, -0.4));


        /**
         * GearBoxTest xbox controller Mapping
         * Uncomment as needed, make sure ROBOT_TYPE is correctly assigned!
         */
        //xboxY.whenPressed(new GearBoxTest(drivetrain, xboxController, 1.0, 1.0));
        //xboxA.whenPressed(new GearBoxTest(drivetrain, xboxController, -1.0, -1.0));
        //xboxX.whenPressed(new GearBoxTest(drivetrain, xboxController, 0.0, 0.0));

        /**
         * Test Buttons if you need to STOP, FORWARD OR REVERSE
         *
         * Comment out as needed, and change ROBOT_TYPE!
         */
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous

        //Uncomment this if you'd like to drive forward, forever
        //Make sure ROBOT_TYPE is set!

        //return new AutoDriveForwardForever(drivetrain, this);

        //Edit the second argument for the amount of seconds you'd like to drive
        //Make sure ROBOT_TYPE is set!

        //return new AutoDriveForwardTimer(drivetrain, 7.0);

        //Autonomous Command that doesnt work
        //return new FollowTrajectory(drivetrain).ExampleAutonomousCommand();

//        return new AutoRoutine1(drivetrain, shooterSubsystem, intakeSubsystem);
        return chooser.getSelected();
    }
}

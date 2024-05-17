// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final XboxController xbox = new XboxController(XBOX_PORT);
  
  private Drivetrain drivetrain = new Drivetrain();
  final DriveWithJoysticks driveCommand = new DriveWithJoysticks(drivetrain, xbox);
  SendableChooser<Command> auto_chooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    //Initializes the autonomous chooser
    auto_chooser = new SendableChooser<>();

    //Sets the options for the Auto_Chooser
    auto_chooser.setDefaultOption("Placeholder", m_autoCommand);
    auto_chooser.addOption("Placeholder2", driveCommand);
    auto_chooser.addOption("Placeholder3", m_autoCommand);

    //Sends choices to dashboard
    SmartDashboard.putData("Auto Choices: ", auto_chooser);
    
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return auto_chooser.getSelected();
  }
    //For subsystem default commands (driving, etc.)
    private void configureDefaultCommands(){

      //Drivetrain -> drive with xbox joysticks
      drivetrain.setDefaultCommand(driveCommand);
    }
}

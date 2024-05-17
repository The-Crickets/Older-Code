// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Constants.*;


public class DriveWithJoysticks extends Command {
  
 
  private Drivetrain drivetrain;
  private XboxController xboxJoysticks; //I don't think we need this because XboxController is a static class
  double angleCorrection = 0.0;

  /** Creates a new DriveWithJoysticks. */
  public DriveWithJoysticks(Drivetrain drive, XboxController xbox) {

    drivetrain = drive;
    xboxJoysticks = xbox;
   

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    //Drive using xbox joystick values

    drivetrain.driveMecanum(xboxJoysticks.getLeftX(), xboxJoysticks.getLeftY(), xboxJoysticks.getRightX(), USEGYRO);
     
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

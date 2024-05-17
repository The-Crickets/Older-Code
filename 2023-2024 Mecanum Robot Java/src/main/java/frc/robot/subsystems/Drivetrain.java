// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {
  
  //Define motor controllers
  private CANSparkMax frontLeftMotor;
  private CANSparkMax frontRightMotor;
  private CANSparkMax backLeftMotor;
  private CANSparkMax backRightMotor;
  private MecanumDrive mecanumDrive;

  //Define local variables
  private AHRS gyroscope;
  private Rotation2d gyroAngle;
  private double speedX, speedY, speedZ;


  /** Creates a new Drivetrain. */
  public Drivetrain() {

    

    // Initialize motor controllers
    frontLeftMotor = new CANSparkMax(FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(BACK_LEFT_MOTOR_ID, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(BACK_RIGHT_MOTOR_ID, MotorType.kBrushless);

    // Invert back motors
    backLeftMotor.setInverted(false);
    backRightMotor.setInverted(true);

    // Initialize and Calibrates gyroscope
    gyroscope = new AHRS(SerialPort.Port.kUSB);

    // Initialize mecanum drive
    mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
   
  }
  
  public void driveMecanum(double leftJoyX, double leftJoyY, double rightJoyX, boolean useGyro)
  {
    frontLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    frontRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    backLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    backRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

    //Set properties of drive
    mecanumDrive.setSafetyEnabled(false);	
    mecanumDrive.setMaxOutput(MAX_MOTOR_OUTPUT);
    
    //Get joystick values and scale
    speedX = -leftJoyY * DRIVE_SPEED;
    speedY = -leftJoyX * DRIVE_SPEED;
    speedZ = rightJoyX * DRIVE_SPEED;

    //Gets gyroscope angle
    gyroAngle = gyroscope.getRotation2d();

    //This condition only allows the robot to move if the gyroscope has finished calibrating.
    if (gyroscope.isCalibrating() == false) {
      
      //This condition tests if it should factor the gyroscope into driving.
      //This value can be changed in the Constants.java file.
      if(useGyro) {
        //If using the pi gyro board, mod the returned value by 360 to avoid a spinning robot
        //gyroAngle = Robot.gyroAngle.getDouble(0) % 360.0;
    
        mecanumDrive.driveCartesian(speedX, speedY, speedZ, gyroAngle);
    
      } else {

        mecanumDrive.driveCartesian(speedX, speedY, speedZ);

      }
    }

    //These place values to the dashboard
    SmartDashboard.putNumber("Gyro Angle", gyroscope.getAngle());
    SmartDashboard.putNumber("Gyro Yaw", gyroscope.getYaw());
    SmartDashboard.putNumber("Front Left Drive", frontLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Left Drive", backLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Front Right Drive", frontRightMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Right Drive", backRightMotor.getOutputCurrent());
    SmartDashboard.putBoolean("Gyro Calibrating", gyroscope.isCalibrating());
    SmartDashboard.putString("Current Drive Mode:", "Mecanum");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

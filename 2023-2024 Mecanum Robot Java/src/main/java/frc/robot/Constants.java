// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Motor Controller Constants - Drive
	public static final int BACK_LEFT_MOTOR_ID = 2;
	public static final int BACK_RIGHT_MOTOR_ID = 4;
	public static final int FRONT_LEFT_MOTOR_ID = 1;
	public static final int FRONT_RIGHT_MOTOR_ID = 3;

    //Speed multiplier for more accurate driving in mecanum
	public static final double MECANUM_TURN_MULTIPLIER = .8;

    //Motor speeds
	public static final double AUTO_DRIVE_SPEED = 1.0;
	public static double DRIVE_SPEED = 1.0;
	public static final double DRIVE_SPEED_FAST = 1.0;
	public static final double DRIVE_SPEED_SLOW = 0.5;

	//Gyro Config
	public static final boolean USEGYRO = true;

	//PID values for mecanum
	public static final double MAX_MOTOR_OUTPUT = 0.95;

	//Drive control port IDs
	public static final int XBOX_PORT = 0;
	public static final int LEFT_JOYSTICK = 1;
	public static final int RIGHT_JOYSTICK = 2;
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public class TankDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.Drivetrain.frontLeftID);
  private WPI_TalonFX frontRight = new WPI_TalonFX(Constants.Drivetrain.frontRightID);

  private WPI_TalonFX backLeft = new WPI_TalonFX(Constants.Drivetrain.backLeftID);
  private WPI_TalonFX backRight = new WPI_TalonFX(Constants.Drivetrain.backRightID);

  private MotorControllerGroup leftMotorControl = new MotorControllerGroup(backLeft, frontLeft);
  private MotorControllerGroup rightMotorControl = new MotorControllerGroup(backRight, frontRight);

  private final Gyro m_gyro = new ADXRS450_Gyro();

  private DifferentialDrive differentialDrive = new DifferentialDrive(leftMotorControl, rightMotorControl);

  public void Drivetrain() {
    rightMotorControl.setInverted(true);

    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);

  }

  public double getLeftDistance()
  {
    return (frontLeft.getSelectedSensorPosition() + backLeft.getSelectedSensorPosition())/2/Constants.Drivetrain.wheelConversionFactor;
  }

  public double getRightDistance()
  {
    return (frontRight.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition())/2/Constants.Drivetrain.wheelConversionFactor;
  }

  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

  public void stop() {
    differentialDrive.arcadeDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  // public void setDefaultCommand(DrivetrainCommnd drivetrainCommnd) {
  // }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive

import com.kauailabs.navx.frc.AHRS;

public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Drivebase() {}

  // Initialize motors
  CANSparkMax m_LeftMotorOne = new CANSparkMax(1, MotorType.kBrushed);
  CANSparkMax m_LeftMotorTwo = new CANSparkMax(2, MotorType.kBrushed);
  CANSparkMax m_LeftMotorThr = new CANSparkMax(3, MotorType.kBrushed);

  CANSparkMax m_RightMotorOne = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax m_RightMotorTwo = new CANSparkMax(5, MotorType.kBrushed);
  CANSparkMax m_RightMotorThr = new CANSparkMax(6, MotorType.kBrushed);

  // Sort Motors to run together
  MotorControllerGroup m_LeftMotors = new MotorControllerGroup(m_LeftMotorOne, m_LeftMotorTwo, m_LeftMotorThr);
  MotorControllerGroup m_RightMotors = new MotorControllerGroup(m_RightMotorOne, m_RightMotorTwo, m_RightMotorThr);

  DifferentialDrive m_DifferentialDrive = new DifferentialDrive(m_LeftMotors, m_RightMotors);

  // Initialize GYRO
  AHRS m_Gyro = new AHRS();

  // Initialize ArcadeDrive
  public void ArcadeDrive(double xSpeed, double yDirection){
    m_DifferentialDrive.arcadeDrive(xSpeed, yDirection);
  }

  public double getAngle(){
    return m_Gyro.getAngle();
  }

  public CommandBase moveForward() {
    return run(
        () -> {
          
          ArcadeDrive(1, 0);

        });
  }

  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

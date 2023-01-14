package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Import motors and arcade drive library
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// Import GYRO library
import com.kauailabs.navx.frc.AHRS;

public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Drivebase() {

    // Invert right motors because they are on the opposite side
    m_RightMotorOne.setInverted(true);
    m_RightMotorTwo.setInverted(true);
    m_RightMotorThr.setInverted(true);

    // Calibrate GYRO to reset the axis to zero
    m_Gyro.calibrate();
  }

  // Initialize motors
  // Motor one is the master; Motors two and three are slaves
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

  // Initialize GYRO and set to robot port
  AHRS m_Gyro = new AHRS(edu.wpi.first.wpilibj.SerialPort.Port.kMXP);

  // Initialize ArcadeDrive
  public void ArcadeDrive(double xSpeed, double yDirection){
    m_DifferentialDrive.arcadeDrive(xSpeed, yDirection);
  }

  public double getAngle(){
    return m_Gyro.getAngle();
  }

  public CommandBase move(double xSpeed, double yDirection) {
    return runOnce(
        () -> {
          
          // Move the robot based on controller input
          ArcadeDrive(xSpeed, yDirection);

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

    // Constantly get the angle of the robot
    SmartDashboard.putNumber("Gyro Angle", getAngle());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation

    
  }
}

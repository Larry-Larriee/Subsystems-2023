// DEFAULT IMPORTS ===============================================================================================================  

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// IMPORTS ======================================================================================================================= 

// Import Motors for drivebase
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 

// Import differential drive for arcade drive
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Drivebase() {

    // Invert Right Motors to all motors will spin forward
    m_rightMotorMaster.setInverted(true);
    m_rightMotorSlave1.setInverted(true);
    m_rightMotorSlave2.setInverted(true);
  }

  CANSparkMax m_leftMotorMaster = new CANSparkMax(0, MotorType.kBrushed);
  CANSparkMax m_leftMotorSlave1 = new CANSparkMax(1, MotorType.kBrushed);
  CANSparkMax m_leftMotorSlave2 = new CANSparkMax(2, MotorType.kBrushed);

  CANSparkMax m_rightMotorMaster = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax m_rightMotorSlave1 = new CANSparkMax(5, MotorType.kBrushed);
  CANSparkMax m_rightMotorSlave2 = new CANSparkMax(6, MotorType.kBrushed);

  // Group all Motors together
  MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_leftMotorMaster, m_leftMotorSlave1, m_leftMotorSlave2);
  MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_rightMotorMaster, m_rightMotorSlave1, m_rightMotorSlave2);

  DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // moveRovot is a method to make the robot move forward
  public CommandBase moveRobot(double xSpeed, double yDirection){

    return run(() -> {
      m_differentialDrive.arcadeDrive(xSpeed, yDirection);
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

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
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

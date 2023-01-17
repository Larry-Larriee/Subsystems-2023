// DEFAULT IMPORTS ================================================================================================================
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// IMPORTS =======================================================================================================================

// import SparkMax Motors and Encoders
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import PID controller
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import motor encoders
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder.Type; 

// import limit switch to stop elevator at top and bottom
import com.revrobotics.SparkMaxLimitSwitch; 
import com.revrobotics.CANSparkMax.SoftLimitDirection;
// MAINSETUP ====================================================================================================================

public class Elevator extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Elevator() {

    // Set the encoder position to 0 initially
    m_elevatorEncoder.setPosition(0);
  }

  // PID controller for elevator and limit switch
  PIDController m_PID = new PIDController(0.1, 0.0, 0.0);

  // elevator motor and its encoder
  CANSparkMax m_elevatorMotor = new CANSparkMax(0, MotorType.kBrushed);
  RelativeEncoder m_elevatorEncoder = m_elevatorMotor.getAlternateEncoder(Type.kQuadrature, 30);

  public CommandBase desiredElevatorHeight(double height) {
    return runOnce(
        () -> {

          // Use the limit switch to stop the elevator at the top 1000
          m_elevatorMotor.setSoftLimit(SoftLimitDirection.kForward, 1000);
          m_elevatorMotor.setSoftLimit(SoftLimitDirection.kReverse, 0);

          // Setting the motor speed based on PID calculations
          // PID will move the motor to the desired height
          m_elevatorMotor.set(m_PID.calculate(m_elevatorEncoder.getPosition(), height));
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

    // Get the height of the elevator
    SmartDashboard.putNumber("Elevator Encoder", m_elevatorEncoder.getPosition());

    // Check if the limit switch is triggered 
    // SmartDashboard.putBoolean("Limit Switch", m_elevatorMotor.getSoftLimit(SoftLimitDirection.kForward));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

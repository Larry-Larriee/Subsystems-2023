// DEFAULT IMPORTS ===============================================================================================================  

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

// IMPORTS ======================================================================================================================= 

// Import drivebase and elevator subsystems
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Elevator;
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivebase m_Drivebase = new Drivebase();
  private final Elevator m_Elevator = new Elevator();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Set commands to the XBOX controller for free movement
    m_Drivebase.setDefaultCommand(
      Commands.run(
        () -> m_Drivebase.moveRobot(m_driverController.getLeftY(), m_driverController.getRightX()), 
          m_Drivebase)
      );
  }

  private void configureBindings() {
    
    // Create a command that set the elevator to the max height up when the y button is pressed/toggled.
    m_driverController.y().whileTrue(m_Elevator.desiredElevatorHeight(1000));

    // Create a command that set the elevator at the middle(h = 500) when the x button is pressed/toggled.
    m_driverController.x().whileTrue(m_Elevator.desiredElevatorHeight(500));

    // Create a command that set the elevator at the minimum height when the b button is pressed/toggled.
    m_driverController.b().whileTrue(m_Elevator.desiredElevatorHeight(0));
  }

  public Command getAutonomousCommand() {
    
    // Move autonomously by plugging in the drivebase subsystem object as an argument
    // This makes the robot move forward automatically for 3 seconds
    return Autos.moveAuto(m_Drivebase);
  }
}

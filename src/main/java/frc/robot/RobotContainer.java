package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.controller;
import frc.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

// Import drivebase to robotcontainer to run command 
import frc.robot.subsystems.Drivebase;

public class RobotContainer {
  // Cretae a new drivebase
  private final Drivebase m_Drivebase = new Drivebase();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Set commands to the XBOX controller
    m_Drivebase.setDefaultCommand(
      //new (m_Drivebase.ArcadeDrive(m_driverController.getLeftY(), m_driverController.getRightX())));
      Commands.run(
        () -> m_Drivebase.ArcadeDrive(-m_driverController.getLeftY(), -m_driverController.getRightX()), m_Drivebase));
  }
  
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(
      m_Drivebase.move(1,0)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous

    return null; 
  }
}

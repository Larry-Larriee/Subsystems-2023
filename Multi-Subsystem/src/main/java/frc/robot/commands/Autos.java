// DEFAULT IMPORTS ===============================================================================================================  

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// IMPORTS ======================================================================================================================= 

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drivebase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */

  public static CommandBase moveAuto(Drivebase m_db) {
    return Commands.sequence(
      
      //subsystem.exampleMethodCommand(), new ExampleCommand(subsystem)
      m_db.moveRobot(0.5, 0.0),

      // Wait three seconds
      new WaitCommand(3),

      m_db.moveRobot(0.0, 0.0)
    
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}

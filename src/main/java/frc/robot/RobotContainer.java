// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer {
  
  private ClimberSubsystem climber;
  private IntakeSubsystem intake;
  private CommandXboxController opController;

  public RobotContainer() {
    climber = new ClimberSubsystem();
    intake = new IntakeSubsystem();
    opController = new CommandXboxController(1);
    configureBindings();
  }

  private void configureBindings() {
    //climber
    opController.povUp().onTrue(climber.raiseClimbers());
    opController.povDown().onTrue(climber.lowerClimbers());
    opController.povLeft().onTrue(climber.zeroClimbers());
    opController.povRight().onTrue(climber.stopClimbers());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

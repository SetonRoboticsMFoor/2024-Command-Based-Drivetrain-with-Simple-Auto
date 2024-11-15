// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSub;

public class AutoDistanceDriveCom extends Command {

  private final DriveTrainSub m_subsystem;
  private final double driveSpeed;
  private final double turnSpeed;
  private final double distance;

  public AutoDistanceDriveCom(DriveTrainSub subsystem, double driveSpeed, double turnSpeed, double distance) {
    m_subsystem = subsystem;
    this.driveSpeed = driveSpeed;
    this.turnSpeed = turnSpeed;
    this.distance = distance;
  }

  @Override
  public void initialize() {
    m_subsystem.setLeftEncoder(Constants.LEFT_ENCODER_SETPOINT);
    m_subsystem.setRightEncoder(Constants.RIGHT_ENCODER_SETPOINT);
  }

  @Override
  public void execute() {
    m_subsystem.arcadeDrive(driveSpeed, turnSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.arcadeDrive(Constants.STOP_SPEED, Constants.STOP_SPEED);
  }

  @Override
  public boolean isFinished() {
    if (distance < (m_subsystem.getLeftEncoder())) {
      return true;
    } else {
      return false;
    }
  }
}

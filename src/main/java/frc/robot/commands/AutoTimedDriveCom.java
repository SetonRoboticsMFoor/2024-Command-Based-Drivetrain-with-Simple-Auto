package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSub;

public class AutoTimedDriveCom extends Command {

  private final DriveTrainSub m_subsystem;
  private final double driveSpeed;
  private final double turnSpeed;
  private final double time;
  private Timer timer;

  public AutoTimedDriveCom(DriveTrainSub subsystem, double driveSpeed, double turnSpeed, double time) {
    m_subsystem = subsystem;
    this.driveSpeed = driveSpeed;
    this.turnSpeed = turnSpeed;
    this.time = time;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    m_subsystem.arcadeDrive(driveSpeed, turnSpeed);
    SmartDashboard.putNumber("Target Time", time);
    SmartDashboard.putNumber("Timer value", timer.get());
    SmartDashboard.putNumber("Drive Speed", driveSpeed);
    SmartDashboard.putNumber("Turn speed", turnSpeed);
    SmartDashboard.putString("Command Status", "Running");
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.arcadeDrive(Constants.STOP_SPEED, Constants.STOP_SPEED);
    SmartDashboard.putString("Command Status", "End of Command");
  }

  @Override
  public boolean isFinished() {
    if (time < timer.get()) {
      return true;

    } else {
      return false;
    }
  }
}

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSub;

public class TeleDriveCom extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrainSub m_subsystem;
  private final Supplier<Double> ySpeed;
  private final Supplier<Double> zSpeed;

  public TeleDriveCom(DriveTrainSub subsystem, Supplier<Double> ySpeed, Supplier<Double> zSpeed) {
    m_subsystem = subsystem;
    this.ySpeed = ySpeed;
    this.zSpeed = zSpeed;

    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double realTimeYSpeed = ySpeed.get();
    double realTimeZSpeed = zSpeed.get();
    m_subsystem.arcadeDrive(realTimeYSpeed, realTimeZSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.arcadeDriveStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
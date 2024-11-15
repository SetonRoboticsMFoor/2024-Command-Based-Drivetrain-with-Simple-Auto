package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoTimedDriveCom;
import frc.robot.commands.TeleDriveCom;
import frc.robot.subsystems.DriveTrainSub;

public class RobotContainer {

  private final DriveTrainSub m_DriveTrainSub;
  private Joystick m_Joystick;

  private final SendableChooser<String> auto_chooser;

  public static final String leftAutoText = "Left Auto";
  public static final String centerAutoText = "Center Auto";
  public static final String rightAutoText = "Right Auto";

  public RobotContainer() {
    m_DriveTrainSub = new DriveTrainSub();
    m_Joystick = new Joystick(Constants.JOYSTICK_CHANNEL);
    auto_chooser = new SendableChooser<>();
    auto_chooser.addOption(leftAutoText, leftAutoText);
    auto_chooser.addOption(centerAutoText, centerAutoText);
    auto_chooser.addOption(rightAutoText, rightAutoText);

    SmartDashboard.putData("Auto Choices", auto_chooser);

    m_DriveTrainSub.setDefaultCommand(new TeleDriveCom(
        m_DriveTrainSub,
        () -> m_Joystick.getZ() * Constants.Z_SPEED_MULTIPLIER,
        () -> m_Joystick.getY() * Constants.Y_SPEED_MULTIPLIER));

    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    switch (auto_chooser.getSelected()) {
      case "Left Auto":
        return new SequentialCommandGroup(
          new AutoTimedDriveCom(m_DriveTrainSub, .1, .1, 1),
          new AutoTimedDriveCom(m_DriveTrainSub, .2, .2, 2),
          new AutoTimedDriveCom(m_DriveTrainSub, .3, .3, 3),
          new AutoTimedDriveCom(m_DriveTrainSub, .4, .4, 4));

      case "Center Auto":
        return new AutoTimedDriveCom(m_DriveTrainSub, .5, .5, 5);

      case "Right Auto":
        return new AutoTimedDriveCom(m_DriveTrainSub, .75, .75, 7.5);

      default:
        return new AutoTimedDriveCom(m_DriveTrainSub, 0, 0, 0);
    }
  }
}

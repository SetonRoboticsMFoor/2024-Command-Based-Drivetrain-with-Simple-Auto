package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSub extends SubsystemBase {
  private CANSparkMax fLMotor;
  private CANSparkMax rLMotor;
  private CANSparkMax fRMotor;
  private CANSparkMax rRMotor;

  private RelativeEncoder leftDriveEncoder;
  private RelativeEncoder rightDriveEncoder;

  private DifferentialDrive driveTrain;



  public DriveTrainSub() {
    fLMotor = new CANSparkMax(Constants.FL_MOTOR_CHANNEL, MotorType.kBrushless);
    rLMotor = new CANSparkMax(Constants.RL_MOTOR_CHANNEL, MotorType.kBrushless);
    fRMotor = new CANSparkMax(Constants.FR_MOTOR_CHANNEL, MotorType.kBrushless);
    rRMotor = new CANSparkMax(Constants.RR_MOTOR_CHANNEL, MotorType.kBrushless);

    leftDriveEncoder = fLMotor.getEncoder();
    rightDriveEncoder = fRMotor.getEncoder();

    fLMotor.restoreFactoryDefaults();
    fRMotor.restoreFactoryDefaults();
    rLMotor.restoreFactoryDefaults();
    rRMotor.restoreFactoryDefaults();

    rLMotor.follow(fLMotor);
    rRMotor.follow(fRMotor);

    driveTrain = new DifferentialDrive(fLMotor, fRMotor);

    rightDriveEncoder.setPosition(0);
    leftDriveEncoder.setPosition(0);
  
  }

  public void periodic() {
    SmartDashboard.putNumber("Left Encoder Value", getLeftEncoder());
    SmartDashboard.putNumber("Right Encoder Value", getRightEncoder());
 
    
  }

  public void arcadeDrive(double xSpeed, double ySpeed) {
    driveTrain.arcadeDrive(xSpeed, ySpeed);
  }

  public void arcadeDriveStop() {
    driveTrain.arcadeDrive(Constants.STOP_SPEED, Constants.STOP_SPEED);
  }

  public double getLeftEncoder() {
    return leftDriveEncoder.getPosition();
  }

  public void setRightEncoder(double setpoint) {
    rightDriveEncoder.setPosition(setpoint);
  }

  public void setLeftEncoder(double setpoint) {
    leftDriveEncoder.setPosition(setpoint);
  }

  public double getRightEncoder() {
    return rightDriveEncoder.getPosition();
  }
}

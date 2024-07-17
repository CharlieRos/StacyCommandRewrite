package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private CANSparkMax leftMotor, rightMotor;
    private RelativeEncoder leftEncoder, rightEncoder;
    private SparkPIDController leftController, rightController;
    private DigitalInput leftBeam, rightBeam;
    private Rotation2d target;


    public ClimberSubsystem() {
        leftMotor = new CANSparkMax(ClimberConstants.kLeftMotorID, MotorType.kBrushless);
        rightMotor = new CANSparkMax(ClimberConstants.kRightMotorID, MotorType.kBrushless);
        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        leftController = leftMotor.getPIDController();
        leftController.setFeedbackDevice(leftEncoder);
        leftController.setP(ClimberConstants.kP);
        leftController.setI(ClimberConstants.kI);
        leftController.setD(ClimberConstants.kD);
        leftController.setFF(ClimberConstants.kFF);
        rightController = rightMotor.getPIDController();
        rightController.setFeedbackDevice(rightEncoder);
        rightController.setP(ClimberConstants.kP);
        rightController.setI(ClimberConstants.kI);
        rightController.setD(ClimberConstants.kD);
        rightController.setFF(ClimberConstants.kFF);

        leftBeam = new DigitalInput(ClimberConstants.kLeftBeamID);
        rightBeam = new DigitalInput(ClimberConstants.kRightBeamID);
        target = Rotation2d.fromRotations(0);
    }

    public void setTarget(Rotation2d target) {
        this.target = target;
        leftController.setReference(target.getRotations(), ControlType.kPosition);
        rightController.setReference(target.getRotations(), ControlType.kPosition);
    }

    public boolean climbersAtTarget() {
        if(Math.abs(target.getRotations() - leftEncoder.getPosition()) <= ClimberConstants.kTolerance.getRotations()
            && Math.abs(target.getRotations() - rightEncoder.getPosition()) <= ClimberConstants.kTolerance.getRotations()) {
                return true;
        }
        return false;
    }

    public Command stopClimbers() {
        return runOnce(() -> {
            leftController.setReference(0, ControlType.kVoltage);
            rightController.setReference(0, ControlType.kVoltage);
        });
    }

    public Command raiseClimbers() {
        return run(() -> setTarget(ClimberConstants.kTopPosition))
            .until(() -> climbersAtTarget());
    }

    public Command lowerClimbers() {
        return run(() -> setTarget(ClimberConstants.kBottomPosition))
            .until(() -> climbersAtTarget());
    }

    public Command zeroClimbers() {
        return run(() -> leftMotor.set(-.1)).until(() -> !leftBeam.get())
            .alongWith(run(() -> rightMotor.set(-.1))).until(() -> !rightBeam.get());
    }

}
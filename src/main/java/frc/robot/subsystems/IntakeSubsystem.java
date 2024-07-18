package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    private CANSparkMax topMotor, bottomMotor;
    private DigitalInput intakeBeam;

    public IntakeSubsystem() {
        topMotor = new CANSparkMax(IntakeConstants.kTopMotorID, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(IntakeConstants.kBottomMotorID, MotorType.kBrushless);
        bottomMotor.setInverted(true);

        intakeBeam = new DigitalInput(IntakeConstants.kIntakeBeamID);
    }

    private void setMotorSpeed(double percentage) {
        topMotor.set(percentage);
        bottomMotor.set(percentage);
    }

    public Command stopIntake() {
        return runOnce(() -> setMotorSpeed(0));
    }

    public Command startIntake() {
        return runOnce(() -> setMotorSpeed(.5));
    }

    public Command startOuttake() {
        return runOnce(() -> setMotorSpeed(-.5));
    }

}

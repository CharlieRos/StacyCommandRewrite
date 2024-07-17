package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;

public class Constants {
    public static final class ClimberConstants {
        public static final int kLeftMotorID = -1;
        public static final int kRightMotorID = -1;
        public static final int kLeftBeamID = -1;
        public static final int kRightBeamID = -1;
        
        public static final Rotation2d kTopPosition = Rotation2d.fromRotations(10.0);
        public static final Rotation2d kBottomPosition = Rotation2d.fromRotations(0.0);
        public static final Rotation2d kTolerance = Rotation2d.fromRotations(1.0);

        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kFF = 0;

    }
}

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
    private final CANSparkMax wristMotor = new CANSparkMax(Constants.Claw.claw_motor_id, CANSparkMax.MotorType.kBrushless);
    private final RelativeEncoder wristEncoder = wristMotor.getEncoder(); // TODO: check if absolute encoder??
    private PIDController wristController = new PIDController(1, 0,0 );

    private double desiredWristAngle = 0; // something to set wrist angle
    
    public WristSubsystem() {
        wristMotor.restoreFactoryDefaults();

        wristMotor.setInverted(true); // NOTE: dk if needed

        wristEncoder.setPositionConversionFactor(1.0 / 5.0); // TODO: check for value on this
        wristEncoder.setVelocityConversionFactor(1.0 / (60.0 * 5.0)); // TODO: check for value on this


        wristMotor.setSmartCurrentLimit(40); // TODO: check for optimal value on this
    }

    // angle is rotation in radians
    public void setWristMotor() {

        double voltage = wristController.calculate(wristEncoder.getPosition(), desiredWristAngle);
        wristMotor.set(voltage);
    }

    public double getWristMotorSpeed() {
        return wristMotor.get();
    }

    public double getWristAngle() {
        return (wristEncoder.getPosition() * 360); // TODO: find out how to get value for this
    }

    public void adjustWristAngle(double angle) {
        double newAngle = desiredWristAngle + angle;
        if (newAngle >= 90){ // TODO: find limit here
            desiredWristAngle = 90;
        } else if (newAngle <= -90) {
            desiredWristAngle = -90;
        } else {
            desiredWristAngle = newAngle;
        }
    }

    public double resetWristAngle() {
        return desiredWristAngle = 0;
    }

    public void stop() {
        wristMotor.stopMotor();
    }
}
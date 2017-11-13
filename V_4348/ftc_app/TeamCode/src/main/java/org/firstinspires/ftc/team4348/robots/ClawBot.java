package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.constants.HardwareName;

/**
 * Created by RoboKnights on 11/13/2017.
 */

public class ClawBot extends Bot
{
    public DcMotor armMotor;

    public Servo clawServo;
    final double CLAW_INIT_POS = 0.0;

    @Override
    public void init(HardwareMap hMap)
    {
        armMotor = hMap.dcMotor.get(HardwareName.ARM_MOTOR.name);
        clawServo = hMap.servo.get(HardwareName.CLAW_SERVO.name);
    }
}

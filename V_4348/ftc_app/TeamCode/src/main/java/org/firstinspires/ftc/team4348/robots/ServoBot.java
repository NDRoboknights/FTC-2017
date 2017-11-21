package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.constants.HardwareName;

/**
 * Created by Evyn on 11/20/2017.
 */

public class ServoBot extends Bot
{
    public Servo jewelServo;
    public final double JEWEL_INIT_POS = 0.5;

    @Override
    public void init(HardwareMap hMap)
    {
        jewelServo = hMap.servo.get(HardwareName.JEWEL_SERVO.name);
        jewelServo.setPosition(JEWEL_INIT_POS);
    }
}

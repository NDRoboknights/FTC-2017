package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team4348.constants.HardwareName;

/**
 * Created by evynm on 10/3/2017.
 */

public class TestBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    @Override
    public void init(HardwareMap hMap)
    {
        leftMotor = hMap.dcMotor.get(HardwareName.LEFT_MOTOR_ONE.name);
        rightMotor = hMap.dcMotor.get(HardwareName.RIGHT_MOTOR_ONE.name);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);
    }
}

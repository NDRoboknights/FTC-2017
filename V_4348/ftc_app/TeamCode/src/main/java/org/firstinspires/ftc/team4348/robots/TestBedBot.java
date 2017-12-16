package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class TestBedBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor middleMotor;
    public DcMotor rightMotor;

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        leftMotor = hMap.dcMotor.get(HardwareName.LEFT_MOTOR_ONE.name);
        rightMotor = hMap.dcMotor.get(HardwareName.RIGHT_MOTOR_ONE.name);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);
    }
}

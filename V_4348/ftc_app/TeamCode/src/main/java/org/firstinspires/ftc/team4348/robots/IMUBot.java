package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.team4348.controllers.ADAFruitIMU;


public class IMUBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public ADAFruitIMU imu;

    public final PIDCoefficients pidc = new PIDCoefficients(0.001, 0, 0);

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        //drive motors
        leftMotor = hMap.dcMotor.get(HardwareName.LEFT_MOTOR_ONE.name);
        rightMotor = hMap.dcMotor.get(HardwareName.RIGHT_MOTOR_ONE.name);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);

        this.MAX_SPEED = 0.8;

        //sensors
        imu = new ADAFruitIMU(hMap, HardwareName.ADAFRUIT_IMU.name);
    }
}

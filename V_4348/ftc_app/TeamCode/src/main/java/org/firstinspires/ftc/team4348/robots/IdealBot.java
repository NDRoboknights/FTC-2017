package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.constants.HardwareName;
import org.firstinspires.ftc.team4348.controllers.ADAFruitIMU;

/**
 * Created by evynm on 10/5/2017.
 */

public class IdealBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor middleMotor;

    public ADAFruitIMU imu;
    public ColorSensor cSensor;
    public IrSeekerSensor irSensor;

    public DcMotor armMotor;
    public Servo clawServo;
    final double CLAW_INIT_POS = 0.5;

    @Override
    public void init(HardwareMap hMap)
    {
        //drive motors
        leftMotor = hMap.dcMotor.get(HardwareName.LEFT_MOTOR_ONE.name);
        rightMotor = hMap.dcMotor.get(HardwareName.RIGHT_MOTOR_ONE.name);
        middleMotor = hMap.dcMotor.get(HardwareName.MIDDLE_MOTOR_ONE.name);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        middleMotor.setPower(0);

        //other motors
        armMotor = hMap.dcMotor.get(HardwareName.ARM_MOTOR.name);
        armMotor.setPower(0);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);
        otherMotors.add(middleMotor);

        //servos
        clawServo = hMap.servo.get(HardwareName.CLAW_SERVO.name);

        //sensors
        imu = new ADAFruitIMU(hMap, HardwareName.ADAFRUIT_IMU.name);
        cSensor = hMap.colorSensor.get(HardwareName.COLOR_SENSOR.name);
        irSensor = hMap.irSeekerSensor.get(HardwareName.IR_SENSOR.name);
    }
}

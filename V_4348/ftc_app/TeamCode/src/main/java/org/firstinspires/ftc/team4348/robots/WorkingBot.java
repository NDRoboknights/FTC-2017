package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.constants.HardwareName;
import org.firstinspires.ftc.team4348.controllers.ADAFruitIMU;
import org.firstinspires.ftc.team4348.utils.StatusChecker;
import org.firstinspires.ftc.team4348.utils.Utilities;

/**
 * Created by evynm on 10/5/2017.
 */

public class WorkingBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor middleMotor;

    public ADAFruitIMU imu;
    public NormalizedColorSensor cSensor1;
    public NormalizedColorSensor cSensor2;

    public Servo jewelServo;
    public final double JEWEL_INIT_POS = 0.5;

    public DcMotor intakeMotor1;
    public DcMotor intakeMotor2;

    public DcMotor upDownMotor1;
    public DcMotor upDownMotor2;

    public final PIDCoefficients PIDC = new PIDCoefficients(0.001, 0.001, 0.001);

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
        intakeMotor1 = hMap.dcMotor.get(HardwareName.INTAKE_MOTOR_ONE.name);
        intakeMotor2 = hMap.dcMotor.get(HardwareName.INTAKE_MOTOR_TWO.name);

        upDownMotor1 = hMap.dcMotor.get(HardwareName.UP_MOTOR_ONE.name);
        upDownMotor2 = hMap.dcMotor.get(HardwareName.UP_MOTOR_TWO.name);
        upDownMotor2.setDirection(DcMotor.Direction.REVERSE);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);
        otherMotors.add(middleMotor);
        otherMotors.add(intakeMotor1);
        otherMotors.add(intakeMotor2);
        otherMotors.add(upDownMotor1);
        otherMotors.add(upDownMotor2);

        //servos
        jewelServo = hMap.servo.get(HardwareName.JEWEL_SERVO.name);

        //sensors
        imu = new ADAFruitIMU(hMap, HardwareName.ADAFRUIT_IMU.name);
        cSensor1 = hMap.get(NormalizedColorSensor.class, HardwareName.COLOR_SENSOR1.name);
        cSensor2 = hMap.get(NormalizedColorSensor.class, HardwareName.COLOR_SENSOR2.name);
    }

    public void runIntakeMotors(double power)
    {
        intakeMotor1.setPower(power);
        intakeMotor2.setPower(power);
    }

    public void runUpMotors(double power)
    {
        upDownMotor1.setPower(power);
        upDownMotor2.setPower(power);
    }
}

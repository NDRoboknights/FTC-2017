package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team4348.controllers.ADAFruitIMU;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;


public class WorkingBot extends Bot
{
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor middleMotor;

    public ADAFruitIMU imu;
    public PIDController pidController;
    public PIDFunctions pidFunctions;

    public ColorSensor cSensor1;
    public ColorSensor cSensor2;

    public Servo jewelServo;
    public final double JEWEL_INIT_POS = 0.0;

    public DcMotor intakeMotors;

    public DcMotor upDownMotor1;

    public final double AUTO_DRIVE_SPEED = 0.7;
    public final double AUTO_MEDIUM_SPEED = 0.5;
    public double startingAngle;

    public final PIDCoefficients PIDC = new PIDCoefficients(0.001, 0.001, 0.001);

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        //drive motors
        leftMotor = hMap.dcMotor.get(HardwareName.LEFT_MOTOR_ONE.name);
        rightMotor = hMap.dcMotor.get(HardwareName.RIGHT_MOTOR_ONE.name);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        middleMotor = hMap.dcMotor.get(HardwareName.MIDDLE_MOTOR_ONE.name);

        //other motors
        intakeMotors = hMap.dcMotor.get(HardwareName.INTAKE_MOTOR_ONE.name);

        upDownMotor1 = hMap.dcMotor.get(HardwareName.UP_MOTOR_ONE.name);

        leftMotors.add(leftMotor);
        rightMotors.add(rightMotor);
//        otherMotors.add(middleMotor);
        otherMotors.add(intakeMotors);
        otherMotors.add(upDownMotor1);

        //servos
        jewelServo = hMap.servo.get(HardwareName.JEWEL_SERVO.name);
        jewelServo.setPosition(JEWEL_INIT_POS);

        //sensors
        imu = new ADAFruitIMU(hMap, HardwareName.ADAFRUIT_IMU.name);
        pidFunctions = new PIDFunctions(this, pidController = new PIDController(this.imu, PIDC));

        cSensor1 = hMap.colorSensor.get(HardwareName.COLOR_SENSOR1.name);
        cSensor1.setI2cAddress(I2cAddr.create8bit(0x4c));

//        cSensor2 = hMap.colorSensor.get(HardwareName.COLOR_SENSOR2.name);
//        cSensor2.setI2cAddress((I2cAddr.create8bit(0x4c)));

//        startingAngle = imu.getValue();

        cSensor1.enableLed(true);
    }

    public void runIntakeMotors(double power)
    {
        intakeMotors.setPower(power);
    }

    public void runUpMotors(double power)
    {
        upDownMotor1.setPower(power);
    }
}

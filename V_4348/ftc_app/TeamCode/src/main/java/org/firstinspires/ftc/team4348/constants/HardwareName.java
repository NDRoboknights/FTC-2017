package org.firstinspires.ftc.team4348.constants;

/**
 * Created by evynm on 10/5/2017.
 */

public enum HardwareName
{
    LEFT_MOTOR_ONE("lMotor_0"),
    LEFT_MOTOR_TWO("lMotor_1"),
    RIGHT_MOTOR_ONE("rMotor_0"),
    RIGHT_MOTOR_TWO("rMotor_1"),
    MIDDLE_MOTOR_ONE("mMotor_0"),

    INTAKE_MOTOR_ONE("iMotor_0"),
    INTAKE_MOTOR_TWO("iMotor_1"),
    UP_MOTOR_ONE("uMotor_0"),
    UP_MOTOR_TWO("uMotor_1"),

    ARM_MOTOR("aMotor"),
    CLAW_SERVO("cServo"),

    ADAFRUIT_IMU("imu"),
    COLOR_SENSOR("cSensor"),
    IR_SENSOR("irSensor");

    public String name;

    HardwareName(String name)
    {
        this.name = name;
    }
}
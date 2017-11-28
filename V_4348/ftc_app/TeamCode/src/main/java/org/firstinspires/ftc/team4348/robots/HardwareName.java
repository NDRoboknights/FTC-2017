package org.firstinspires.ftc.team4348.robots;


public enum HardwareName
{
    LEFT_MOTOR_ONE("lMotor0"),
    LEFT_MOTOR_TWO("lMotor1"),
    RIGHT_MOTOR_ONE("rMotor0"),
    RIGHT_MOTOR_TWO("rMotor1"),
    MIDDLE_MOTOR_ONE("mMotor0"),

    INTAKE_MOTOR_ONE("iMotor0"),
    INTAKE_MOTOR_TWO("iMotor1"),
    UP_MOTOR_ONE("uMotor0"),
    UP_MOTOR_TWO("uMotor1"),

    ARM_MOTOR("aMotor"),
    CLAW_SERVO("cServo"),
    JEWEL_SERVO("jServo"),

    ADAFRUIT_IMU("imu"),
    COLOR_SENSOR1("cSensor1"),
    COLOR_SENSOR2("cSensor2"),
    IR_SENSOR("irSensor");

    public String name;

    HardwareName(String name)
    {
        this.name = name;
    }
}
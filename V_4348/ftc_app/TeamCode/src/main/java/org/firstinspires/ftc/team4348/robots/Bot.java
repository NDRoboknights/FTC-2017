package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

public abstract class Bot
{
    public ArrayList<DcMotor> leftMotors = new ArrayList<>();
    public ArrayList<DcMotor> rightMotors = new ArrayList<>();
    public ArrayList<DcMotor> otherMotors = new ArrayList<>();
    public double MAX_SPEED = 1.0;
    public HardwareMap hardwareMap;

    public abstract void init(HardwareMap hMap);

    public void setDrivePowerT(double leftPower, double rightPower, long time)
    {
        setDrivePower(leftPower, rightPower);
        delay(time);
        setDrivePower(0,0);
    }

    public void setDrivePower(double lPower, double rPower)
    {
        for(DcMotor m : leftMotors) {
            m.setPower(lPower);
        }
        for(DcMotor m : rightMotors) {
            m.setPower(rPower);
        }
    }

    public void fullStop()
    {
        for(DcMotor m : leftMotors) {
            m.setPower(0);
        }
        for(DcMotor m : rightMotors) {
            m.setPower(0);
        }
        for(DcMotor m : otherMotors) {
            m.setPower(0);
        }
    }
}

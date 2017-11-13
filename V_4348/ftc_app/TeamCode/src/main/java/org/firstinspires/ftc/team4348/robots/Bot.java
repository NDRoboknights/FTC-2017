package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public abstract class Bot
{
    public ArrayList<DcMotor> leftMotors = new ArrayList<>();
    public ArrayList<DcMotor> rightMotors = new ArrayList<>();
    public ArrayList<DcMotor> otherMotors = new ArrayList<>();
    public double MAX_SPEED = 1.0;

    public abstract void init(HardwareMap hMap);
}

package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;


public class ColorBot extends Bot
{
    public ModernRoboticsI2cColorSensor cSensor;

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        cSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, HardwareName.COLOR_SENSOR1.name);
        cSensor.setI2cAddress(I2cAddr.create7bit(0x4c));
    }
}

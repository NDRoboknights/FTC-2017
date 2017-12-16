package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;


public class ColorBot extends Bot
{
    public ColorSensor cSensor1;
    public ColorSensor cSensor2;

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        cSensor1 = hMap.colorSensor.get(HardwareName.COLOR_SENSOR1.name);
        cSensor1.setI2cAddress(I2cAddr.create8bit(0x3c));

        cSensor2 = hMap.colorSensor.get(HardwareName.COLOR_SENSOR2.name);
        cSensor2.setI2cAddress((I2cAddr.create8bit(0x4c)));
    }
}

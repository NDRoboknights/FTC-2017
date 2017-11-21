package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team4348.constants.HardwareName;

/**
 * Created by evynm on 10/5/2017.
 */

public class ColorBot extends Bot
{
    public ColorSensor cSensor;

    @Override
    public void init(HardwareMap hMap)
    {
        this.hardwareMap = hMap;
        cSensor = hMap.colorSensor.get(HardwareName.COLOR_SENSOR1.name);
    }
}

package org.firstinspires.ftc.team4348.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

import org.firstinspires.ftc.team4348.constants.HardwareName;

/**
 * Created by evynm on 10/9/2017.
 */

public class IRBot extends Bot
{
    public IrSeekerSensor irSensor;

    @Override
    public void init(HardwareMap hMap)
    {
        irSensor = hMap.irSeekerSensor.get(HardwareName.IR_SENSOR.name);
    }
}

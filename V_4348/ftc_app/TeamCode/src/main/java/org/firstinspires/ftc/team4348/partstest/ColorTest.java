package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.ColorBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by RoboKnights on 11/20/2017.
 */

@TeleOp(name="SensorTest: Color",group="SensorTest")
public class ColorTest extends CustomTeleOp
{
    ColorBot bot = new ColorBot();

    @Override
    public void init() {
        bot.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Red: ", bot.cSensor.red());
        telemetry.addData("Blue: ", bot.cSensor.blue());
        telemetry.addData("Green: ", bot.cSensor.green());
        telemetry.update();
    }
}

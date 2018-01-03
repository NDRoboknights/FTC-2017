package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.ColorBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;


@TeleOp(name="SensorTest: Color",group="SensorTest")
public class ColorTest extends CustomTeleOp
{
    ColorBot bot = new ColorBot();

    @Override
    public void init() {
        bot.init(hardwareMap);
        bot.cSensor1.enableLed(true);
        //bot.cSensor2.enableLed(true);
    }

    @Override
    public void loop() {
        telemetry.addData("Red: ", bot.cSensor1.red());
        telemetry.addData("Blue: ", bot.cSensor1.blue());
        telemetry.addData("Green: ", bot.cSensor1.green());
        telemetry.update();
    }
}

package org.firstinspires.ftc.team4348.partstest;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team4348.robots.IMUBot;
import org.firstinspires.ftc.team4348.teleop.CustomTeleOp;

/**
 * Created by evynm on 10/4/2017.
 */

@TeleOp(name="SensorTest: IMU",group="SensorTest")
public class IMUTest extends CustomTeleOp
{
    IMUBot bot = new IMUBot();

    @Override
    public void init() {
        bot.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("IMU Angle:", bot.imu.getValue());
        telemetry.update();
    }
}

package org.firstinspires.ftc.team4348.autonomous.snippet;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

/**
 * Created by RoboKnights on 11/16/2017.
 */

@Autonomous(name="Blue,Snippet: Jewel",group="Blue,Snippet")
public class JewelAutoBlue extends CustomAutonomous
{
    WorkingBot bot = new WorkingBot();
    final double AUTO_SPEED = 0.75;
    
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
        setPidController(new PIDController(bot.imu, bot.PIDC.p, bot.PIDC.i, bot.PIDC.d));

        waitForStart();

        //ASSUMES:
        //(1) bot is facing toward  relic recovery on red, away for blue...
        //(2) color sensor is facing same way as robot...
        //(3) color sensor is on the end of the jewel servo...
        //(4) jewel servo is on left side of robot

        //drop jewel servo arm
        bot.jewelServo.setPosition(0.0);
        delay(50);

        //read color sensor
        NormalizedRGBA RGBA = bot.cSensor1.getNormalizedColors();

        Direction dir = null;
        //RGBA.red returns [0,1)
        if (RGBA.red >= 0.5) { //if above half threshold, choose that direction
            dir = Direction.RIGHT; //right for forward
        } else if (RGBA.blue >= 0.5) { //not an else statement to make sure we have a reading
            dir = Direction.LEFT; //left for backward
        }

        if (dir != null) { //make sure we had a reading
            straight(-dir.v * AUTO_SPEED, new TimeChecker(200));
            delay(50);
        }

        //retract arm
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
    }
}

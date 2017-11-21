package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.robots.WorkingBot;

import static org.firstinspires.ftc.team4348.utils.Utilities.delay;

/**
 * Created by RoboKnights on 11/19/2017.
 */

public class JewelBlue
{
    public static void run(WorkingBot bot)
    {
        //drop jewel servo arm
        bot.jewelServo.setPosition(0.0);

        //read color sensor and choose direction
        Direction dir = null;

        while(dir == null) { //make sure we had a reading
            //RGBA.red returns [0,255]
            //if above threshold, choose that direction
            if (bot.cSensor1.blue() >= CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.BACKWARD;
            }
            //not an else statement to make sure we have a reading
            else if (bot.cSensor1.red() >= CustomAutonomous.COLOR_THRESHOLD) {
                dir = Direction.FORWARD;
            }
        }

        bot.setDrivePowerT(dir.v * bot.AUTO_DRIVE_SPEED, dir.v * bot.AUTO_DRIVE_SPEED, 200);
        delay(50);

        //retract arm
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);
    }
}
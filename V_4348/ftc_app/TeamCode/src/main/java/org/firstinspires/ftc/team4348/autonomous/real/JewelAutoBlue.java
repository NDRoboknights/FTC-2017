package org.firstinspires.ftc.team4348.autonomous.real;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.robots.IdealBot;

/**
 * Created by RoboKnights on 11/16/2017.
 */

@Autonomous(name="Red,Snippet: Jewel",group="Red,Snippet")
public class JewelAutoBlue extends CustomAutonomous
{
    IdealBot bot = new IdealBot();
    final double AUTO_SPEED = 0.75;
    
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        bot.clawServo.setPosition(bot.CLAW_INIT_POS);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);

        waitForStart();

        //assumes: (1) bot is facing away from relic recovery on red, towards for blue...
        //(2) color sensor is facing same way as robot...
        //(3) color sensor is on the end of the jewel servo...
        //(4) jewel servo is on right side of robot

        bot.jewelServo.setPosition(0.0);
        delay(50);

        //read color sensor
        NormalizedRGBA RGBA = bot.cSensor.getNormalizedColors();
        Direction dir = null;

        //RGBA.red returns [0,1)
        if (RGBA.blue >= 0.5) { //if above half threshold, choose that direction
            dir = Direction.RIGHT; //right for forward
        } else if (RGBA.red >= 0.5) { //not an else statement to make sure we have a reading
            dir = Direction.LEFT; //left for backward
        }

        if (dir != null) { //make sure we had a reading
            setPowerT(-dir.v * AUTO_SPEED, -dir.v * AUTO_SPEED, 200);
        }

        //drive toward center to not hit anything
        bot.middleMotor.setPower(Direction.RIGHT.v * AUTO_SPEED); //reverse for red side
        delay(150);
        bot.middleMotor.setPower(0);
    }
}

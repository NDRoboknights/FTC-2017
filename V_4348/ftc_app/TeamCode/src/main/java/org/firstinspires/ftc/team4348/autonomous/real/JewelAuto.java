package org.firstinspires.ftc.team4348.autonomous.real;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.constants.Team;
import org.firstinspires.ftc.team4348.robots.IdealBot;

/**
 * Created by RoboKnights on 11/16/2017.
 */

@Autonomous(name="Red,Snippet: Jewel",group="Red,Snippet")
public class JewelAuto extends CustomAutonomous
{
    Team team = Team.RED;
    IdealBot bot = new IdealBot();
    final double AUTO_SPEED = 0.75;
    
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        bot.clawServo.setPosition(bot.CLAW_INIT_POS);
        bot.jewelServo.setPosition(bot.JEWEL_INIT_POS);

        waitForStart();

        //color sensor is on the end of the jewel servo

        bot.jewelServo.setPosition(0.0);
        delay(50);

        //read censor
        NormalizedRGBA RGBA = bot.cSensor.getNormalizedColors();
        Direction dir = null;

        //RGBA.red returns [0,1)
        if (RGBA.red >= 0.5) { //if above half threshold, choose that direction
            //assumes robot is facing toward the center
            //assumes color sensor is facing toward the right
            dir = Direction.RIGHT;
        } else if (RGBA.blue >= 0.5) { //not an else statement to make sure we have a reading
            dir = Direction.LEFT;
        }

        if (dir != null) { //make sure we had a reading
            bot.middleMotor.setPower(dir.v * AUTO_SPEED);
            delay(200);
            bot.middleMotor.setPower(0);
        }

        //drive forward to not hit anything
        setPowerT(AUTO_SPEED,AUTO_SPEED,150);
    }
}

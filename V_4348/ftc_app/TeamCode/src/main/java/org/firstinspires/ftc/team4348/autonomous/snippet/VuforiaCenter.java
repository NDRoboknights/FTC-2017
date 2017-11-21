package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.Vuforia;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;

/**
 * Created by RoboKnights on 11/17/2017.
 */

//The same for both red and blue per the field layout
public class VuforiaCenter
{
    static Vuforia vuforia;
    static final double ACC_ERROR = 2;

    public static void run(WorkingBot bot) throws InterruptedException {
        vuforia = new Vuforia(bot.hardwareMap);
        PIDFunctions pidFunc = new PIDFunctions(bot, new PIDController(bot.imu, bot.PIDC));

        //track the RelicVuMarks
        VuforiaTrackables relicTrackables = vuforia.localizer.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

        //Assume:
        //(1) bot is facing away from relic recovery on red, towards for blue...
        //(2) phone is mounted to the right of the robot

        //drive forward until in line with VuMark
        PIDFunctions.PIDStraightThread sThread = new PIDFunctions.PIDStraightThread(pidFunc, bot.AUTO_MEDIUM_SPEED);
        sThread.thread.start();

        //drive forward until pose is determinable (in range of VuMark)
        OpenGLMatrix pose;
        do {
            pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
        }while(pose == null);

        //drive forward until rotationalX of VuMark is 0
        double rX;
        do {
            Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

            // Extract the rotational components of the target relative to the robot
            rX = rot.firstAngle;
        }while(Math.abs(rX) > 0 + ACC_ERROR);

        sThread.setRunning(false);
        sThread.thread.join();
        bot.setDrivePower(0, 0);

        //finally, get the VuMark
        RelicRecoveryVuMark mark = RelicRecoveryVuMark.from(relicTemplate);
    }
}
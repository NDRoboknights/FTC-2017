package org.firstinspires.ftc.team4348.autonomous.snippet;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.team4348.controllers.PID.PIDController;
import org.firstinspires.ftc.team4348.controllers.Vuforia;
import org.firstinspires.ftc.team4348.robots.WorkingBot;
import org.firstinspires.ftc.team4348.controllers.PID.PIDFunctions;
import org.firstinspires.ftc.team4348.utils.TimeChecker;

//The same for both red and blue per the field layout
public class IDVuMark
{
    static Vuforia vuforia;

    public static RelicRecoveryVuMark run(WorkingBot bot, long timeout)
    {
        vuforia = new Vuforia(bot.hardwareMap);
        PIDFunctions pidFunc = new PIDFunctions(bot, new PIDController(bot.imu, bot.PIDC));

        //track the RelicVuMarks
        VuforiaTrackables relicTrackables = vuforia.localizer.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

        //Assume:
        //(1) bot is facing away from relic recovery on blue, towards for red...
        //(2) phone is mounted to the left of the robot
        RelicRecoveryVuMark vumark = null;

        TimeChecker tChecker = new TimeChecker(timeout);
        while(vumark == null && tChecker.checkStatus())
        {
            vumark = RelicRecoveryVuMark.from(relicTemplate);
        }

        return vumark;
    }
}
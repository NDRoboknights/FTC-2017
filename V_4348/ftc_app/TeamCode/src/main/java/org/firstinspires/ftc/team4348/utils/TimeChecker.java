package org.firstinspires.ftc.team4348.utils;

/**
 * Created by RoboKnights on 11/20/2017.
 */

public class TimeChecker extends StatusChecker
{
    long endTime;

    public TimeChecker(long time)
    {
        this.endTime = System.currentTimeMillis() + time;
    }

    @Override
    public boolean checkStatus() {
        return System.currentTimeMillis() < endTime;
    }
}
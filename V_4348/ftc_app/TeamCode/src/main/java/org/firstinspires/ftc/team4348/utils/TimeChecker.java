package org.firstinspires.ftc.team4348.utils;


public class TimeChecker extends StatusChecker
{
    long endTime;

    /**
     * @param time the time in milliseconds to complete the loop
     */
    public TimeChecker(long time)
    {
        this.endTime = System.currentTimeMillis() + time;
    }

    @Override
    public boolean checkStatus() {
        return System.currentTimeMillis() < endTime;
    }
}
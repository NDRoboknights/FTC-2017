package org.firstinspires.ftc.team4348.utils;

/**
 * Created by RoboKnights on 11/18/2017.
 */

public class Utilities
{
    public static void delay(StatusChecker sChecker)
    {
        Object monitor = new Object();
        while(sChecker.checkStatus()) {
            try {
                monitor.wait(2);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void delay(long time)
    {
        Object monitor = new Object();
        try {
            monitor.wait(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double[] scalePower(double... doubles)
    {
        //find max
        double max = Math.abs(doubles[0]);
        for(Double d : doubles)
        {
            if(Math.abs(d) > max) {
                max = d;
            }
        }

        if(max < 1.0) return doubles;

        //scale everything to max
        for(int x=0; x<doubles.length; x++)
        {
            doubles[x] /= max;
        }

        return doubles;
    }
}
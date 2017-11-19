package org.firstinspires.ftc.team4348.utils;

/**
 * Created by RoboKnights on 11/18/2017.
 */

public class Utilities
{
    static final Object monitor = new Object();

    public static void delay(long time)
    {
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
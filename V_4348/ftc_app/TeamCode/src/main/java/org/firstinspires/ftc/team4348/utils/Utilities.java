package org.firstinspires.ftc.team4348.utils;

/**
 * Useful functions including <code>delay(...)</code> and <code>scalePower(...)</code>
 */
public class Utilities
{
    /**
     * Waits untils <code>sChecker.checkStatus()</code> returns false.
     * @param sChecker Status Checker
     */
    public static void delay(StatusChecker sChecker)
    {
        Object monitor = new Object();
        while(sChecker.checkStatus()) {
            try {
                monitor.wait(1);
            } catch (InterruptedException ignored) {

            }
        }
    }

    /**
     * Calls <code>wait(time)</code> on an initialized Object
     * @param time Time to wait
     */
    public static void delay(long time)
    {
        Object monitor = new Object();
        try {
            monitor.wait(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * If any power is over MAX (1.0), will divide through by the max power. Else, returns original
     * powers.
     * @param doubles All powers to scale with each other
     * @return An array of the new powers corresponding in order with original powers
     */
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

        if(max <= 1.0) return doubles;

        //scale everything to max
        for(int x=0; x<doubles.length; x++)
        {
            doubles[x] /= max;
        }

        return doubles;
    }
}
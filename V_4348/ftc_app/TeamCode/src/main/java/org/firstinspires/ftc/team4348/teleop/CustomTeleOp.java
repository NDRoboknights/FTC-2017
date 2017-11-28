package org.firstinspires.ftc.team4348.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


/**
 * Most TeleOps made extends this TeleOp which includes TeleOp constants like <code>JOYSTICK_THRESHOLD</code>
 * and functions like <code>scaleInput(...)</code>
 */
public abstract class CustomTeleOp extends OpMode
{
    protected static final double JOYSTICK_THRESHOLD = 0.125;

    /**
     * @param dVal The value to scale.
     * @return Returns the nearest multiple of 0.05 but not over 1.00
     */
    public double scaleInput(double dVal)
    {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }
}

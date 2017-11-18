package org.firstinspires.ftc.team4348.autonomous.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team4348.autonomous.CustomAutonomous;
import org.firstinspires.ftc.team4348.constants.Direction;
import org.firstinspires.ftc.team4348.controllers.PIDController;
import org.firstinspires.ftc.team4348.robots.IMUBot;

/**
 * Created by evynm on 10/4/2017.
 */

@Autonomous(name="Calibration: PIDCalibration",group="Calibration")
public class PIDCalibration extends CustomAutonomous
{
    IMUBot bot = new IMUBot();
    PIDController pidController;
    final double THRESHOLD = 0.5;
    final double DELTA_VAL = 0.001;

    @Override
    public void runOpMode() throws InterruptedException
    {
        setBot(bot);
        bot.init(hardwareMap);

        double p = 0.001;
        double i = 0.000;
        double d = 0.000;
        pidController = new PIDController(bot.imu, 0.001, 0, 0);
        CycleChecker cChecker = new CycleChecker(pidController, pidController.D_EXTRACYCLES);

        waitForStart();

        boolean prevA = false;
        //DO STUFF
        while(opModeIsActive())
        {
            double leftStick = -gamepad1.left_stick_y;
            boolean a = gamepad1.a, x = gamepad1.x, b = gamepad1.b, y = gamepad1.y;

            if(a && !prevA) {
                turn(Direction.LEFT, 90, cChecker);
            }

            if(x && Math.abs(leftStick) > THRESHOLD)
            {
                if(leftStick < 0) {
                    p -= DELTA_VAL;
                }
                else {
                    p += DELTA_VAL;
                }
            }

            if(b && Math.abs(leftStick) > THRESHOLD)
            {
                if(leftStick < 0) {
                    d -= DELTA_VAL;
                }
                else {
                    d += DELTA_VAL;
                }
            }

            if(y && Math.abs(leftStick) > THRESHOLD)
            {
                if(leftStick < 0) {
                    i -= DELTA_VAL;
                }
                else {
                    i += DELTA_VAL;
                }
            }

            pidController.setPidc(p, i, d);
            prevA = a;

            telemetry.addData("IMU: ", bot.imu.getValue());
            telemetry.addData("P: ", p);
            telemetry.addData("I: ", i);
            telemetry.addData("D: ", d);

            telemetry.update();
        }
    }
}

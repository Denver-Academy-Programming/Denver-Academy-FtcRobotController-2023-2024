package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Prototype V1 AutoDrive", group="PrototypeV1")
public class PrototypeV1AutoDrive extends LinearOpMode {
    /* Decalare OpMode members.*/
    private DcMotor motorFront = null;
    private DcMotor motorBack = null;
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private ElapsedTime runtime = new ElapsedTime();

    float rightLeftPower;
    float forwardBackPower;
    float turningPower = 0.6f;

    //Need to add a wait for start variable!!!
    //
    //
    //

    @Override
    public void runOpMode() {

        //Initialize and setup the modules that are linked to the control hub.
        //Setting the motors up so that the robot can function
        //Setting the left motor to reverse due to its position
        //relative to the right motors placement so that it can move forward.
        try {
            //All four motors front, back, left, and right.
            //Grab them all from the hardwareMap and set their
            //direction so that the driving works correctly.
            motorFront = hardwareMap.get(DcMotor.class, "motorFront");
            motorBack = hardwareMap.get(DcMotor.class, "motorBack");
            motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
            motorRight = hardwareMap.get(DcMotor.class, "motorRight");

            motorFront.setDirection(DcMotorSimple.Direction.REVERSE);
            motorBack.setDirection(DcMotorSimple.Direction.FORWARD);
            motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
            motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }   catch(IllegalArgumentException e) {
            throw new RuntimeException(
                    "A error occurred in the initialization of the script  \n" +
                    "Please check you have the right motor control scheme set on the \n" +
                    "Driver Hub so it knows which modules are connected. \n" +
                    "Full traceback: \n" + e

            );
        }

        telemetry.addData("Status", "Hot Stand by");
        telemetry.addData("Awaiting User Action", "Waiting for user to begin");
        telemetry.update();

        //Waiting for program to begin and user to start the application.
        waitForStart();

        //Step 1: Drive forward for 2 seconds
        motorLeft.setPower(forwardBackPower);
        motorRight.setPower(forwardBackPower);
        //Resetting the runtime because it begins right away after initalization.
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            telemetry.addData("Move 1 Time", runtime.seconds());
        }

    }
}

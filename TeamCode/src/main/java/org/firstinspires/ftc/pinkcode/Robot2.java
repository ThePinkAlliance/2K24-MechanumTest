package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Robot2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor rightMotor, leftMotor, flintLockwoodMotor;
        Servo gusServo, fringServo;

        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        flintLockwoodMotor = hardwareMap.dcMotor.get("flintLockwoodMotor");
        gusServo = hardwareMap.get(Servo.class,"gus");
        fringServo = hardwareMap.get(Servo.class, "fring");

        flintLockwoodMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flintLockwoodMotor.setTargetPosition(0);
        flintLockwoodMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = -gamepad1.left_stick_x; // Counteract imperfect strafing
            double clawUp=gamepad1.right_stick_y; // Lower power;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double leftPower=((y-x) * -1)/2;
            double rightPower=((y+x) * -1)/2;
            rightMotor.setPower(rightPower);
            leftMotor.setPower(leftPower);

            if(clawUp>0) {
                flintLockwoodMotor.setPower(clawUp*0.1);
            } else if(clawUp<0){
                flintLockwoodMotor.setPower(clawUp*0.7);
            } else {
                flintLockwoodMotor.setPower(0.0);
            }

            if(gamepad1.a){
                gusServo.setPosition(45);
                fringServo.setPosition(0);
            } else if(gamepad1.b){
                gusServo.setPosition(0);
                fringServo.setPosition(45);
            }

            if(gamepad1.y){
                flintLockwoodMotor.setTargetPosition(-200);
            }

            flintLockwoodMotor.setPower(1);

            telemetry.addData("armPosition",flintLockwoodMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}

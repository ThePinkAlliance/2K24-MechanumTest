package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class Teleop extends OpMode {
    DcMotor frontLeft, frontRight, backLeft, backRight;

    DcMotorEx gusMotor;
    @Override
    public void init() {
        gusMotor = hardwareMap.get(DcMotorEx.class, "gus");

        frontRight = hardwareMap.get(DcMotor.class, "frontR");
        frontLeft = hardwareMap.get(DcMotor.class, "frontL");

        backLeft = hardwareMap.get(DcMotor.class, "backL");
        backRight = hardwareMap.get(DcMotor.class, "backR");
    }

    @Override
    public void loop() {
        float x = -gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float rot = gamepad1.right_stick_x;

        double r = Math.hypot(x, y);
        double robotAngle = Math.atan2(y, x) - Math.PI / 4;
        double rightX = rot;

        // Equations below is motor speed for each wheel
        double v1 = r * Math.cos(robotAngle) - rightX;
        double v2 = r * Math.sin(robotAngle) + rightX;
        double v3 = r * Math.sin(robotAngle) - rightX;
        double v4 = r * Math.cos(robotAngle) + rightX;

        // If not turning give each wheel full power
        if (x == 0) {
            v1 += v1 / 3;
            v2 += v2 / 3;
            v3 += v3 / 3;
            v4 += v4 / 3;
        }

        this.frontLeft.setPower(v1);
        this.frontRight.setPower(-v2);
        this.backLeft.setPower(v3);
        this.backRight.setPower(-v4);

        if (gusMotor.getCurrentPosition() >= 30) {
            gusMotor.setPower(-0.1);
        } else if (gusMotor.getCurrentPosition() <= 26) {
            gusMotor.setPower(0.1);
        } else{
            gusMotor.setPower(0.0);
        }

        telemetry.addData("Ticks", gusMotor.getCurrentPosition());
        telemetry.update();
    }
}

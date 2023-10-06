package org.firstinspires.ftc.pinkcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ArmBot extends OpMode {

    DcMotorEx right, left;
    Servo arm, pin;

    @Override
    public void init() {
        right = hardwareMap.get(DcMotorEx.class, "right");

        left = hardwareMap.get(DcMotorEx.class, "left");
        arm = hardwareMap.get(Servo.class, "arm");
        pin = hardwareMap.get(Servo.class, "pin");
    }

    @Override
    public void loop() {

    }
}

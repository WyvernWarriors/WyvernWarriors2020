package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Frog Teleop", group="Iterative Opmode")
public class GretaElizabethTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //declaring drive motors

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    //declaring mechanism motors

    private Servo clawServo = null;
    private DcMotor armMotor = null;
    
    double speedMod = 1;
    
    @Override
    public void init() {
        //initializing drive motors

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
    
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    
        //initializing mechanism motors
        
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        servoMotor = hardwareMap.get(Servo.class, "clawServo");

        armMotor.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setZeroPowerBehavior(BRAKE);
        
    }

    @Override
    public void loop() {
    //drive code
    
    if(gamepad1.x) {
        speedMod = 1;
    } else if (gamepad1.y) {
        speedMod = .5;
    }

    if(gamepad2.x) {
        clawServo.setPosition(0);
    } 
    if(gamepad2.y) {
        clawServo.setPosition(1);
    }

    
    double leftPower = deadband(gamepad1.left_stick_y) + deadband(gamepad1.right_stick_x);
    double rightPower = deadband(gamepad1.left_stick_y) - deadband(gamepad1.right_stick_x);
    double motorLimit = .8;
    
    frontLeft.setPower(speedMod*leftPower*motorLimit);
    backLeft.setPower(speedMod*leftPower*motorLimit);
    frontRight.setPower(speedMod*rightPower*motorLimit);
    backRight.setPower(speedMod*rightPower*motorLimit);

    armMotor.setPower(deadband(gamepad2.left_stick_y*.8));
    

    //mechanisms controls
    /* current controls :  
        gamepad a runs intake (backwards)
        gamepad left trigger will run shooter motor*
        gamepad right trigger will run belt motor*
            *both triggers are pressure sensitive so there is some range of speeds you can go at !! 
    */
    
    /*
    shooterMotor.setPower(deadband(gamepad1.left_trigger));
    beltMotor.setPower(deadband(gamepad1.right_trigger));
    */

    }

    /**
     * This method takes in input, the number you're getting from joysticks,
     * and a range you want to make dead on the joysticks. If the joystick 
     * input is in the range, the method will return 0. Otherwise, it will
     * return the current value of the joystick. 
     */
    public double deadband(double input) { 
        double range = .02;
        if(input >= 0 && input <= range) { //if input is positive and smaller than range
            return 0;
        } else if(input < 0 && input >= -range) { // or if input is negative and greater than negative range
            return 0;
        } else { //if netiher of these, just return the initial value
            return input;
        }
    }
}
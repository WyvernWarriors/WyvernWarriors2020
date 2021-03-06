package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Cool Teleop", group="Iterative Opmode")
public class basicDrive extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //declaring drive motors

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    //declaring mechanism motors

    private DcMotor intakeMotor = null;
    private DcMotor beltMotor = null;
    private DcMotor shooterMotor = null;
    
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
        
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        beltMotor = hardwareMap.get(DcMotor.class, "beltMotor");
        shooterMotor = hardwareMap.get(DcMotor.class, "shooterMotor");
    
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        beltMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setDirection(DcMotor.Direction.REVERSE);
        
    }

    @Override
    public void loop() {
    //drive code
    
    if(gamepad1.x) {
        speedMod = 1;
    } else if (gamepad1.y) {
        speedMod = .5;
    }

    double leftPower = deadband(gamepad1.left_stick_y) + deadband(gamepad1.right_stick_x);
    double rightPower = deadband(gamepad1.left_stick_y) - deadband(gamepad1.right_stick_x);
    double motorLimit = .8;

    frontLeft.setPower(speedMod*leftPower*motorLimit);
    backLeft.setPower(speedMod*leftPower*motorLimit);
    frontRight.setPower(speedMod*rightPower*motorLimit);
    backRight.setPower(speedMod*rightPower*motorLimit);

    //mechanisms controls
    /* current controls :  
        gamepad a runs intake (backwards)
        gamepad left trigger will run shooter motor*
        gamepad right trigger will run belt motor*
            *both triggers are pressure sensitive so there is some range of speeds you can go at !! 
    */
    
    if(gamepad2.a) {
        intakeMotor.setPower(-1);
    } else if(gamepad2.b) {
        intakeMotor.setPower(1);
    } else {
        intakeMotor.setPower(0);
    }
    /*
    shooterMotor.setPower(deadband(gamepad1.left_trigger));
    beltMotor.setPower(deadband(gamepad1.right_trigger));
    */
    if(gamepad2.left_bumper) {
        shooterMotor.setPower(1);
    } else {
        shooterMotor.setPower(0);
    }
    if(gamepad2.right_bumper) {
        beltMotor.setPower(1);
    } else {
        beltMotor.setPower(0);
    }
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
import jdk.jfr.internal.dcmd.DCmdCheck;

@Autonomous(name="basic auto : onion", group="Linear Opmode")

public class BasicAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    //declaring mechanism motors

    private DcMotor intakeMotor = null;
    private DcMotor beltMotor = null;
    private DcMotor shooterMotor = null;

    @Override 
    public void runOpMode() {

        //initialization stuff

        robot.init(hardwareMap);

        telemetry.addData("Onion auto running !!");    //logging
        telemetry.update();

        // reset encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                
        // set motors to run forward for 5000 encoder counts.
        frontLeft.setTargetPosition(5000);
        backLeft.setTargetPosition(5000);
        frontRight.setTargetPosition(5000);
        backRight.setTargetPosition(5000);
        
        // set mode to run forward the set number of ticks
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //wait for driver to press play button
        waitForStart();

        telemetry.addData("Running Auto");
        telemetry.addData("encoder-back-left : ", backLeft.getCurrentPosition());
        telemetry.addData("encoder-front-right", frontRight.getCurrentPosition());
        telemetry.update();

        // 
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);



    }
}

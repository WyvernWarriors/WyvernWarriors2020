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

        robot.init(hardwareMap);

        telemetry.addData("Onion auto running !!");    //
        telemetry.update();

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


    }
}

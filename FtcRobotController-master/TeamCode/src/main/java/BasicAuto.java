
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

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
        runtime.reset();
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        //robot.init(hardwareMap);

        telemetry.addLine("Onion auto running !!");    //logging
        telemetry.update();
        
        //wait for driver to press play button
        waitForStart();
        
        while(opModeIsActive() && runtime.seconds() < 7) {
            // 
            backLeft.setPower(.4);
            backRight.setPower(.4);
            frontLeft.setPower(.4);
            frontRight.setPower(.4);
        }
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);

        
        telemetry.addLine("Running Auto");
        telemetry.update();


    }
}

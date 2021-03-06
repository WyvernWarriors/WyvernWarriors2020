
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="basic auto : shallot", group="Linear Opmode")

public class ShallotAuto extends LinearOpMode {
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
        
               frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
               frontRight = hardwareMap.get(DcMotor.class, "frontRight");
               backLeft = hardwareMap.get(DcMotor.class, "backLeft");
               backRight = hardwareMap.get(DcMotor.class, "backRight");
               
               frontLeft.setDirection(DcMotor.Direction.FORWARD);
               frontRight.setDirection(DcMotor.Direction.REVERSE);
               backLeft.setDirection(DcMotor.Direction.FORWARD);
               backRight.setDirection(DcMotor.Direction.REVERSE);
       
                  // configure encoders
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                
                telemetry.addData("Path0",  "Starting at %7d :%7d",
                                frontLeft.getCurrentPosition(),
                                backRight.getCurrentPosition());
                telemetry.update();
                
                // set motors to run forward for 5000 encoder counts.
                frontLeft.setTargetPosition(6000); //1120 for neverest 40 should 
                backLeft.setTargetPosition(6000); // go 1 full rotation
                frontRight.setTargetPosition(6000);
                backRight.setTargetPosition(6000);
                

                
               telemetry.addLine("Onion auto initialized !!");    //logging
               telemetry.update();

                //wait for driver to press play button
                waitForStart();

                // set mode to run forward the set number of ticks
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                backLeft.setPower(1);
                backRight.setPower(1);
                frontLeft.setPower(1);
                frontRight.setPower(1);

                while(opModeIsActive() && backLeft.isBusy()) {
                    telemetry.addData("target position", frontLeft.getTargetPosition());
                    telemetry.addData("encoder-fwd-left", frontLeft.getCurrentPosition());
                    telemetry.addData("encoder-back-right", backRight.getCurrentPosition());
                    telemetry.update();
                }

                /*
                while(opModeIsActive()) {
                    telemetry.addData("Path0",  "Currently at %7d :%7d",
                    frontLeft.getCurrentPosition(),
                    backRight.getCurrentPosition());
                    telemetry.update();
                    if(frontLeft.getTargetPosition <= 5000) {
                        // 
                        backLeft.setPower(1);
                        backRight.setPower(1);
                        frontLeft.setPower(1);
                        frontRight.setPower(1);
                    } else {
                        backLeft.setPower(0);
                        backRight.setPower(0);
                        frontLeft.setPower(0);
                        frontRight.setPower(0);
                    }
                }
                */
                telemetry.addLine("Auto Done !");
                telemetry.update();
    }
    }
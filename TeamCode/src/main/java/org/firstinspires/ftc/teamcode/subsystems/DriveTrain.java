package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @version 1.0
 * @author Connor_Hofenbitzer
 * Main drive train class for an x drive
 */
public class DriveTrain {

    //syncronized singleton
    private static DriveTrain driveTrain = null;
    public static DriveTrain getNewDriveInstance(HardwareMap hwMap) {
        if(driveTrain == null) {
            driveTrain = new DriveTrain(hwMap);
        }
        return driveTrain;
    }

    //outputs
    private double[][] motorOutputs;

    private boolean isDone;
    private double forwardSetpoint;
    private double strafeSetpoint;

    //front motors
    private DcMotor frontLeft;
    private DcMotor frontRight;

    //back motors
    private DcMotor backLeft;
    private DcMotor backRight;

    //possible states the drive train could be in
    private systemStates possState;
    private enum systemStates  {
        SYSTEM_OFF , DRIVE, AUTO
    };

    public DriveTrain(HardwareMap hwMap) {

        //sets up the enum state case
        possState = systemStates.SYSTEM_OFF;

        //drive motor outputs
        isDone = false;
        motorOutputs = new double[2][2];
        forwardSetpoint = 0;
        strafeSetpoint = 0;

        //front drive motors
        frontLeft = hwMap.get(DcMotor.class, "front_LeftWheel");
        frontRight = hwMap.get(DcMotor.class, "front_RightWheel");

        //back drive motors
        backLeft = hwMap.get(DcMotor.class, "back_LeftWheel");
        backRight = hwMap.get(DcMotor.class, "back_RightWheel");

    }

    public void update() {
        switch (possState) {
            case SYSTEM_OFF:
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                isDone = false;
                updateInnerDrive(0,0,0);
                break;
            case DRIVE:
                break;
            case AUTO:
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                double scale = (1.0/360.0 * (8.89 * 3.14159));
                if(strafeSetpoint != 0) {
                    frontLeft.setTargetPosition((int) (strafeSetpoint*scale) );
                    frontRight.setTargetPosition((int) (strafeSetpoint*scale));
                    backLeft.setTargetPosition((int) -(strafeSetpoint*scale));
                    backRight.setTargetPosition((int) (strafeSetpoint*scale));

                } else {
                    frontLeft.setTargetPosition((int)   (forwardSetpoint*scale) );
                    frontRight.setTargetPosition((int) -(forwardSetpoint*scale));
                    backLeft.setTargetPosition((int)    (forwardSetpoint*scale));
                    backRight.setTargetPosition((int)  -(forwardSetpoint*scale));
                }
                isDone = !frontLeft.isBusy();
                break;
            default:
                System.out.println("Yo, you messed up enumeration within drivetrain dude");
        }

        if(possState != systemStates.AUTO) {
            //sets the power for the front motors
            frontLeft.setPower(motorOutputs[0][0]);
            frontRight.setPower(-motorOutputs[0][1]);

            //sets the power for the back motors
            backLeft.setPower(motorOutputs[1][0]);
            backRight.setPower(-motorOutputs[1][1]);
        }
    }

    /**
     * Updates the x drive base
     * @param strafe : robots lateral movement
     * @param drive  : robots forwards and backwards movement
     * @param turn   : rotational value
     */
    public void updateInnerDrive(double strafe, double drive , double turn) {

        //enables motor control

        motorOutputs[0][0] =  strafe + drive + turn;
        motorOutputs[0][1] = -strafe + drive - turn;
        motorOutputs[1][0] = -strafe + drive + turn;
        motorOutputs[1][1] =  strafe + drive - turn;
    }

    public void updateDrive(double strafe, double drive , double turn) {

        //enables motor control
        possState = systemStates.DRIVE;

        motorOutputs[0][0] =  strafe + drive + turn;
        motorOutputs[0][1] = -strafe + drive - turn;
        motorOutputs[1][0] = -strafe + drive + turn;
        motorOutputs[1][1] =  strafe + drive - turn;
    }

    public boolean isDone() {
        return isDone;
    }
    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
        this.update();
    }

    /**
     *
     * @param forwards : CM
     */
    public void setAutoForwards(double forwards) {
        this.strafeSetpoint = 0;
        this.forwardSetpoint = forwards;
    }

    /**
     *
     * @param strafe : CM
     */
    public void setAutoStrafe(double strafe) {
        this.forwardSetpoint = 0;
        this.strafeSetpoint = strafe;
    }


}
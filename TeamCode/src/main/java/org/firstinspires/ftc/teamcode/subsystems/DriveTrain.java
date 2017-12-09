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

    //front motors
    private DcMotor frontLeft;
    private DcMotor frontRight;

    //back motors
    private DcMotor backLeft;
    private DcMotor backRight;

    //possible states the drive train could be in
    private systemStates possState;
    private enum systemStates  {
        SYSTEM_OFF , DRIVE
    };

    public DriveTrain(HardwareMap hwMap) {

        //sets up the enum state case
        possState = systemStates.SYSTEM_OFF;

        //drive motor outputs
        motorOutputs = new double[2][2];

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
                updateDrive(0,0,0);
                break;
            case DRIVE:
                break;
            default:
                System.out.println("Yo, you messed up enumeration within drivetrain dude");
        }

        //sets the power for the front motors
        frontLeft.setPower(motorOutputs[0][0]);
        frontRight.setPower(-motorOutputs[0][1]);

        //sets the power for the back motors
        backLeft.setPower(motorOutputs[1][0]);
        backRight.setPower(-motorOutputs[1][1]);
    }

    /**
     * Updates the x drive base
     * @param strafe : robots lateral movement
     * @param drive  : robots forwards and backwards movement
     * @param turn   : rotational value
     */
    public void updateDrive(double strafe, double drive , double turn) {

        //enables motor control
        possState = systemStates.DRIVE;

        motorOutputs[0][0] =  strafe + drive +turn;
        motorOutputs[0][1] = -strafe + drive - turn;
        motorOutputs[1][0] = -strafe + drive + turn;
        motorOutputs[1][1] =  strafe + drive - turn;
    }

    public void setStop() {
        possState = systemStates.SYSTEM_OFF;
        this.update();
    }
}
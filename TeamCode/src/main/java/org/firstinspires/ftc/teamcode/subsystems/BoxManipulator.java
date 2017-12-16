package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * @version 1.1
 * @author  chinmaysavanur
 * Intake and elevator combination
 */
public class BoxManipulator {

    //singleton
    private static BoxManipulator boxManipulator = null;

    public static BoxManipulator getNewBoxManipulatorInstance(HardwareMap hwMap) {
        if (boxManipulator == null) {
            boxManipulator = new BoxManipulator(hwMap);
        }
        return boxManipulator;
    }

    //intake motors
    private DcMotor leftIntake;
    private DcMotor rightIntake;
    private Servo rotationalServo;

//    //belt motors
//    private DcMotor topBelt;
//    private DcMotor bottomBelt;

    public double intakeSpeed;
    public double beltSpeed;
    public double rotationalPos;

    public BoxManipulator(HardwareMap hwMap) {

        //intake motors
        leftIntake = hwMap.get(DcMotor.class, "left_Intake");
        rightIntake = hwMap.get(DcMotor.class, "right_Intake");
        rotationalServo = hwMap.get(Servo.class, "rotational_Servo");
//
//        //belt motors
//        topBelt = hwMap.get(DcMotor.class, "top_Belt");
//        bottomBelt = hwMap.get(DcMotor.class, "bottom_Belt");
    }

    /**
     * updates the system
     */
    public void update() {

        //sets the power for the front motors
        leftIntake.setPower(intakeSpeed);
        rightIntake.setPower(intakeSpeed);
        rotationalServo.setPosition(rotationalPos);

//        //sets the power for the back motors
//        topBelt.setPower(beltSpeed);
//        bottomBelt.setPower(beltSpeed);
    }

    /**
     * Shuts down the entire system
     */
    public void setStop() {
        setIntakeSpeed(0);
        setBeltSpeed(0);
        update();
    }

    /**
     * changes intake speed to input from game-pad
     * @param spd : speed of intake
     */
    public void setIntakeSpeed(double spd) {
        intakeSpeed = spd;
        update();
    }

    /**
     * changes belt speed to input from game-pad
     * @param spd : speed of belt
     */
    public void setBeltSpeed(double spd) {
        beltSpeed = spd;
        update();
    }

    /**
     * change position to input from game-pad
     * @param pos : position of rotational servo
     */
    public void setRotationalPos(double pos) {
        rotationalPos = pos;
        update();
    }
}

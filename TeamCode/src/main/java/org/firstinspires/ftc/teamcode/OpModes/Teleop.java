package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;
import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
//import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;

@TeleOp(name="Nemesis Prime 2018 Teleop", group="Ares Teleop")
public class Teleop extends OpMode
{
    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;
    //private RelicManipulator relicManipulator;

    @Override
    public void init() {

        //init all systems
        driveTrain = DriveTrain.getNewDriveInstance(hardwareMap);
        boxManipulator = BoxManipulator.getNewBoxManipulatorInstance(hardwareMap);
        //relicManipulator = RelicManipulator.getNewRelicManipulatorInstance(hardwareMap);

        //set system stops
        driveTrain.setStop();
        boxManipulator.setStop();
        //relicManipulator.setStop();

    }

    @Override
    public void loop() {
        //updates the drive train
        driveTrain.updateDrive(gamepad1.left_stick_x , gamepad1.left_stick_y , gamepad1.right_stick_y);
        driveTrain.update();

        //updates the intake and belt speeds
        if(gamepad1.left_trigger > 0.25 != gamepad1.left_bumper) {
            if(gamepad1.left_bumper) {
                boxManipulator.setIntakeSpeed(-1);
            } else {
                boxManipulator.setIntakeSpeed(1);
            }
        } else {
            boxManipulator.setIntakeSpeed(0);
        }

        if(gamepad1.right_trigger > 0.25 != gamepad1.right_bumper) {
            if(gamepad1.right_bumper) {
                boxManipulator.setBeltSpeed(-1);
            } else {
                boxManipulator.setBeltSpeed(1);
            }
        } else {
            boxManipulator.setBeltSpeed(0);
        }

        if(gamepad1.a) {
            boxManipulator.setRotationalPos(1);
        }
        if(gamepad1.b) {
            boxManipulator.setRotationalPos(0);
        }

    }
}

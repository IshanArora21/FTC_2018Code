package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

/**
 * Created by Chinmay on 12/9/2017.
 */

@Autonomous(name="Nemesis Prime Blue Left Auto", group="Ares Auto")
public class AutoBlueLeft extends LinearOpMode {

    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;
    private RelicManipulator relicManipulator;
    private Arm arm;

    @Override
    public void runOpMode() throws InterruptedException {
        //init all systems
        driveTrain = DriveTrain.getNewDriveInstance(hardwareMap);
        boxManipulator = BoxManipulator.getNewBoxManipulatorInstance(hardwareMap);
        relicManipulator = RelicManipulator.getNewRelicManipulatorInstance(hardwareMap);
        arm = Arm.getNewArmInstance(hardwareMap);

        //set system stops
        driveTrain.setStop();
        boxManipulator.setStop();
        relicManipulator.setStop();
        arm.setStop();

        arm.setArmExtended();
        arm.update();
        if(arm.seesRed()) { //turn right/left accordingly
            driveTrain.updateDrive(0,0,0.5);
            driveTrain.update();
            sleep(150);
            driveTrain.updateDrive(0,0,-0.5);
            driveTrain.update();
            sleep(150);
            driveTrain.setStop();
        } else {
            driveTrain.updateDrive(0,0,-0.5);
            driveTrain.update();
            sleep(150);
            driveTrain.updateDrive(0,0,0.5);
            driveTrain.update();
            sleep(150);
            driveTrain.setStop();
        }
        arm.setArmRetracted();
        arm.update();

        driveTrain.updateDrive(0,0,1); //turn towards safe zone (right)
        driveTrain.update();
        sleep(1000);
        driveTrain.updateDrive(0,1,0); //drive for five seconds
        driveTrain.update();
        sleep(5000); //forward until in zone (sleep for 5k milliseconds?)
    }

}
package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.BoxManipulator;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.RelicManipulator;

import java.sql.Time;

/**
 * Created by Connor_Hofenbitzer on 12/6/17.
 */

@Autonomous(name="Nemesis Prime Example Auto", group="Ares Auto")
public class ExampleAuto extends LinearOpMode {

    private DriveTrain driveTrain;
    private BoxManipulator boxManipulator;
    private RelicManipulator relicManipulator;



    @Override
    public void runOpMode() throws InterruptedException {
        //init all systems
        driveTrain = DriveTrain.getNewDriveInstance(hardwareMap);
        boxManipulator = BoxManipulator.getNewBoxManipulatorInstance(hardwareMap);
        relicManipulator = RelicManipulator.getNewRelicManipulatorInstance(hardwareMap);

        //set system stops
        driveTrain.setStop();
        boxManipulator.setStop();
        relicManipulator.setStop();

        driveTrain.setAutoStrafe(100);
        while (!driveTrain.isDone()) {
            driveTrain.update();
        }
        driveTrain.setStop();
    }

}
package com.barrybecker4;


import com.barrybecker4.common.WallAvoider;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

import java.awt.Color;

/**
 * Robot tries to stay near the center (away from walls) while spinning and firing.
 *
 * @author Barry Becker
 */
public class CentricityBot extends AdvancedRobot {


	public void run() {

		setBodyColor(Color.orange);
		setGunColor(Color.orange);
		setRadarColor(Color.yellow);
		setScanColor(Color.orange);
        WallAvoider avoider = new WallAvoider(this);

		while (true) {

            if (Math.random() < 0.9)
		        setTurnRight(2);
            else
		        setTurnLeft(1);
			setAhead(5);
            avoider.avoidWalls();
            execute();
		}
	}

	/**
	 * onScannedRobot: Fire hard!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(determineFirepower(e.getDistance()));
	}


    private double determineFirepower(double distance) {
        double fp =  Math.min(3.0, 500.0 / (distance + 80.0));
        System.out.println("dist="+ distance + " firePower=" + fp);
        return fp;
    }

	/**
	 * onHitRobot:  If it's our fault, we'll stop turning and moving,
	 * so we need to turn again to keep spinning.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(5);
		}
	}

}

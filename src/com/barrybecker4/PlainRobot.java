package com.barrybecker4;


import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Barry Becker
 */
public class PlainRobot extends AdvancedRobot {


	public void run() {

		setBulletColor(Color.yellow);
		setScanColor(new Color(100, 200, 100, 230));
		setColors(Color.yellow, new Color(140, 110, 30), new Color(80, 90, 220)); // body,gun,radar

        setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(false);

		// Robot main loop
		while (true) {
			setAhead(10);
			setTurnRight(Math.random() * 100 - 50);
			setTurnGunLeft(Math.random() * 100 - 50);
			//turnGunRight(100);
			//turnRadarRight(20);
            scan();
		}
	}

	/**
	 * What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

        this.setFire(1.0);
	}

    /**
     * Called when one of this robots bullets hits another robot.
     */
    public void onBulletHit(final BulletHitEvent e) {

    }

    /**
	 * What to do when this robot hit by a bullet
	 */
    public void onHitByBullet(final HitByBulletEvent e) {
    }

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		setTurnLeft(Math.random() * 180);
		setAhead(100);
	}


    public void onPaint(Graphics2D g) {
    }


    public static void main(String[] args) {
        Robot s = new PlainRobot();
    }
}

package com.barrybecker4;


import com.barrybecker4.common.BulletShield;
import com.barrybecker4.common.DebugScanner;
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
public class SearcherRobot extends AdvancedRobot {

    private BulletShield shield;
    private DebugScanner scanner;

	public void run() {

		setBulletColor(Color.yellow);
		setScanColor(new Color(100, 200, 100, 230));
		setColors(new Color(10, 70, 40), new Color(120, 100, 30), new Color(80, 90, 200)); // body,gun,radar

        setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

        shield = new BulletShield(this);
        scanner = new DebugScanner();

		// Robot main loop
		while (true) {
			//setAhead(1);
			//setTurnRight(20);
			//setTurnGunRight(20);
			//turnGunRight(10);
			setTurnRadarRight(6);
            scan();
		}
	}

	/**
	 * What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

        shield.bulletDefense(e);
        if (Math.random() <0.3)
            this.setFire(1.0);

        scanner.calcLastScanned(this, e);
	}

    /**
     * Called when one of this robots bullets hits another robot.
     */
    public void onBulletHit(final BulletHitEvent e) {
        shield.bulletHit(e.getBullet());
    }

    /**
	 * What to do when this robot hit by a bullet
	 */
    public void onHitByBullet(final HitByBulletEvent e) {
        shield.hitByBullet(e);
    }

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		setTurnLeft(20);
		setBack(20);
	}


    public void onPaint(Graphics2D g) {
        scanner.paint(this, g);
    }


    public static void main(String[] args) {
        Robot s = new SearcherRobot();
    }
}

package com.barrybecker4;

import robocode.*;
import java.awt.Color;

/**
 * API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html
 * @author Barry Becker
 */
public class FirstBot extends Robot
{
    private Color BODY_COLOR = new Color(20, 150, 10);
    private Color GUN_COLOR = new Color(10, 200, 150);
    private Color RADAR_COLOR = new Color(220, 255, 30);
	/** default behavior */
	public void run() {

        // initialization
		setColors(BODY_COLOR, GUN_COLOR, RADAR_COLOR);

		// Robot main loop
		while (true) {
			ahead(50);
			turnGunRight(180);
            turnLeft(Math.random() * 180);
			back(50);
			turnGunRight(180);
		}
	}

	/** What to do when you see another robot */
	public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance()  < 20)
            fire(3);
        else if (e.getDistance() < 90)
		    fire(2);
        else
            fire(1);
	}

	/** What to do when you're hit by a bullet */
	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}

	/** What to do when you hit a wall */
	public void onHitWall(HitWallEvent e) {
        if (Math.random() < 0.5)
		    back(20);
        else
            ahead(20);
	}
}

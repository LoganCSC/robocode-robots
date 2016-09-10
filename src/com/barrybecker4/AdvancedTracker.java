package com.barrybecker4;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import java.awt.Color;

import static robocode.util.Utils.normalRelativeAngleDegrees;


/**
 * Locks onto a robot, moves close, fires when close.
 */
public class AdvancedTracker extends AdvancedRobot {

    /**  Keeps track of how long we've been searching for our target  */
	private int count = 0;

    /**  How much to turn our gun when searching */
	private double gunTurnAmt;

    /** Name of the robot we're currently tracking */
	private String trackedRobot;

	/**
	 * run:  Tracker's main run function
	 */
	public void run() {
		setBodyColor(new Color(128, 128, 50));
		setGunColor(new Color(50, 50, 20));
		setRadarColor(new Color(200, 200, 70));
		setScanColor(Color.white);
		setBulletColor(new Color(255, 200, 10));

		trackedRobot = null; // Initialize to not tracking anyone
		setAdjustGunForRobotTurn(true); // Keep the gun still when we turn
		gunTurnAmt = 10; // Initialize gunTurn to 10

		// Loop forever
		while (true) {
			// turn the Gun (looks for enemy)
			setTurnGunRight(gunTurnAmt);
			// Keep track of how long we've been looking
			count++;
			// If we've haven't seen our target for 2 turns, look left
			if (count > 2) {
				gunTurnAmt = -10;
			}
			// If we still haven't seen our target for 5 turns, look right
			if (count > 5) {
				gunTurnAmt = 10;
			}
			// If we *still* haven't seen our target after 10 turns, find another target
			if (count > 11) {
				trackedRobot = null;
			}
            execute();
		}
	}

	/**
	 * track a robot that is scanned
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

		// If we have a target, and this isn't it, return immediately
		// so we can get more ScannedRobotEvents.
		if (trackedRobot != null && !e.getName().equals(trackedRobot)) {
			return;
		}

		// If we don't have a target, well, now we do!
		if (trackedRobot == null) {
			trackedRobot = e.getName();
			out.println("Tracking " + trackedRobot);
		}
		// This is our target.  Reset count (see the run method)
		count = 0;
		// If our target is too far away, turn and move toward it.
		if (e.getDistance() > 150) {
			gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

			setTurnGunRight(gunTurnAmt); // Try changing these to setTurnGunRight,
			setTurnRight(e.getBearing()); // and see how much Tracker improves...
			// (you'll have to make Tracker an AdvancedRobot)
			ahead(e.getDistance() - 140);
			return;
		}

		// Our target is close.
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();
	}

	/**
	 * Set him as our new target
	 */
	public void onHitRobot(HitRobotEvent e) {
		// Only print if he's not already our target.
		if (trackedRobot != null && !trackedRobot.equals(e.getName())) {
			out.println("Tracking " + e.getName() + " due to collision");
		}
		// Set the target
		trackedRobot = e.getName();
		// Back up a bit.
		// Note:  We won't get scan events while we're doing this!
		// An AdvancedRobot might use setBack(); execute();
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);
		back(50);
	}

	/**
	 *  Do a victory dance
	 */
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}
}

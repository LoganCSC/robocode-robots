/*******************************************************************************
 * Copyright (c) 2001-2013 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *******************************************************************************/
package com.barrybecker4;


import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.Color;

/**
 * SpinBot - a sample robot by Mathew Nelson.
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class SpinBot2 extends AdvancedRobot {

	public void run() {
		setBodyColor(Color.orange);
		setGunColor(Color.blue);
		setRadarColor(Color.black);
		setScanColor(Color.orange);

		while (true) {
			// turn right... a lot.
            if (Math.random() < 0.872)
			    setTurnRight(101);
            else
                setTurnLeft(50);
			setMaxVelocity(10);
			setAhead(50);
			execute();
            while (getDistanceRemaining() > 0 && getTurnRemaining() > 0)
                execute();
		}
	}

	/** Fire hard! */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}

	/**
	 * If it's our fault, we'll stop turning and moving, so we need to turn again to keep spinning.
	 */
	public void onHitRobot(HitRobotEvent e) {
		/*
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(1);
		}
		if (e.isMyFault()) {
			turnRight(5);
		}*/
	}

	/** What to do when you hit a wall */
	public void onHitWall(HitWallEvent e) {
		turnLeft(160);
		ahead(100);
	}
}

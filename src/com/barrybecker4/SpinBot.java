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
import robocode.ScannedRobotEvent;

import java.awt.Color;
/**
 * Best so far.
 * SpinBot - a sample robot by Mathew Nelson.
 * <p/>
 * Moves in a randome circle, firing hard when an enemy is detected.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 * @author Barry Becker (contributor)
 */
public class SpinBot extends AdvancedRobot {

	public void run() {
		setBodyColor(Color.orange);
		setGunColor(Color.green);
		setScanColor(Color.orange);

		while (true) {
			setTurnRight(100);
			setMaxVelocity(10);
			setAhead(110);
			execute();
            while (getDistanceRemaining() > 0 && getTurnRemaining() > 0)
                execute();
		}
	}

	/** Fire hard!*/
	public void onScannedRobot(ScannedRobotEvent e) {
		setFire(determineFirepower(e.getDistance()));
	}

	/**
	 * If it's our fault, we'll stop turning and moving, so we need to turn again to keep spinning.
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			setFire(3);
		}
		if (e.isMyFault()) {
			setTurnRight(10);
		}
	}


    private double determineFirepower(double distance) {
        return Math.min(3.0, 600.0 / (distance + 80.0));
    }
}

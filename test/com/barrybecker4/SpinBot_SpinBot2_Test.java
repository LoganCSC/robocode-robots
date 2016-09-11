package com.barrybecker4;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.testing.RobotTestBed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(JUnit4.class)
public class SpinBot_SpinBot2_Test extends RobotTestBed {

  private ResultReporter reporter = new ResultReporter();

  /**
   * The names of the robots that want battling is specified.
   * @return The names of the robots we want battling.
   */
  @Override
  public String getRobotNames() {
    return "com.barrybecker4.SpinBot2*, com.barrybecker4.SpinBot*";
  }

  /**
   * Pick the amount of rounds that we want our robots to battle for.
   *
   * @return Amount of rounds we want to battle for.
   */
  @Override
  public int getNumRounds() {
    return 10;
  }

  /**
	 * Keep track of the number of errors.  Print them if desired.
	 *
	 * @param event The BattleErrorEvent.
	 */
	public void onBattleError(BattleErrorEvent event) {
        System.out.println("ERROR: " + event.getError());
		super.onBattleError(event);
	}

  /**
   * Tests to see if our robot won all rounds.
   * @param event Holds information about the battle has been completed.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    reporter.showReport(battleResults);

    BattleResults results = battleResults[1];
    String robotName = results.getTeamLeaderName();
    assertEquals("Check that results[1] is SpinBot", "com.barrybecker4.SpinBot*", robotName);

    System.out.println("r firsts = " + results.getFirsts() + " rounds = " + getNumRounds());
    assertTrue("Check SpinBot winner. Num firsts = " + results.getFirsts(), results.getFirsts() == 3);
  }
}
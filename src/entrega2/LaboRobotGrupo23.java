package entrega2;

import robocode.JuniorRobot;

public class LaboRobotGrupo23 extends JuniorRobot {

	private LaboRobotStrategyInterface strategy;
	public LaboRobotStrategy robotStrategy = new LaboRobotStrategy(this);

	@Override
	public void run() {
		strategy = robotStrategy.changeStrategy(); // Set the initial strategy
		setColors(red, black, black);
		while (true) {
			strategy.runStrategy();
			strategy = robotStrategy.changeStrategy();
		}
	}

	// when the radar detects another robot.
	// When this event occurs the scannedDistance, scannedAngle, scannedBearing, and
	// scannedEnergy
	// field values are automatically updated.
	@Override
	public void onScannedRobot() {
		strategy.onScannedRobot();
	}

	// onHitByBullet: What to do when you're hit by a bullet
	@Override
	public void onHitByBullet() {
		strategy.onHitByBullet();
	}

	// onHitWall: What to do when you hit a wall
	@Override
	public void onHitWall() {
		strategy.onHitWall();
	}
}

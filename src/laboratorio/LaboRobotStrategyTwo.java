package laboratorio;

public class LaboRobotStrategyTwo implements LaboRobotStrategy {

	private LaboRobot robot;
	private boolean isFirstTime = true;

	public LaboRobotStrategyTwo(LaboRobot robot) {
		this.robot = robot;
	}

	@Override
	public void runStrategy() {
		this.firstMove();
		robot.ahead(300);
	}

	@Override
	public void onScannedRobot() {
		int distance = robot.scannedDistance;
		robot.out.println("distance: " + distance);
		if ((distance != -1) && (distance < 150)) {
			robot.turnTo(robot.heading + 90);
		}
	}

	@Override
	public void onHitByBullet() {
		int angle = robot.hitByBulletBearing;
		robot.out.println("angle: " + angle);
		if (angle == 0) {
			robot.fire(3);
		}
	}

	@Override
	public void onHitWall() {
		robot.turnTo(robot.heading + 90);
	}

	private void firstMove() {
		if (isFirstTime) {
			robot.turnTo(90);
			robot.out.println("LaboRobotStrategyTwo");
			isFirstTime = false;
		}
	}

}

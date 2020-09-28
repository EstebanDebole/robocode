package entrega2;

public class LaboRobotStrategy {

	private LaboRobotGrupo23 robot;
	private boolean directionForward = true;
	private LaboRobotStrategyInterface instance;
	private int instanceNumber = 0;

	public LaboRobotStrategy(LaboRobotGrupo23 robot) {
		this.robot = robot;
	}

	public LaboRobotStrategyInterface getStrategyOne() {
		if ((instance == null) || (instanceNumber != 1)) {
			instanceNumber = 1;
			instance = new LaboRobotStrategyOne();
		}
		return instance;
	}

	public LaboRobotStrategyInterface getStrategyTwo() {
		if ((instance == null) || (instanceNumber != 2)) {
			instanceNumber = 2;
			instance = new LaboRobotStrategyTwo();
		}
		return instance;
	}

	public LaboRobotStrategyInterface changeStrategy() {
		if (instance == null)
			instance = getStrategyTwo();
		else if (robot.others == 1)
			instance = getStrategyOne();
		return instance;
	}

	private class LaboRobotStrategyOne implements LaboRobotStrategyInterface {

		private boolean isFirstTime = true;

		@Override
		public void runStrategy() {
			this.firstMove();
			this.erraticMove();
			// 360° mapping trigger the event onScannedRobot()
			robot.turnGunLeft(360);
		}

		@Override
		public void onScannedRobot() {
			int distance = robot.scannedDistance;
			// robot.out.println("distance: "+distance);
			int angle = robot.scannedAngle;
			// robot.out.println("angle: "+angle);
			int velocity = robot.scannedVelocity;
			// robot.out.println("velocity: "+velocity);
			// robot.out.println("heading: "+robot.scannedHeading);

			if (distance > 350) {
				this.attackMove(angle, distance);
			} else if (robot.gunReady) {
				this.attack(angle, distance);
			}
		}

		@Override
		public void onHitByBullet() {
			int angle = robot.hitByBulletAngle;
			robot.turnTo(angle + 90);
			robot.turnGunTo(angle);
		}

		@Override
		public void onHitWall() {
			robot.turnTo(robot.heading + 90);
		}

		private void attack(int angle, int distance) {
			robot.turnGunTo(angle);
			if (distance < 200)
				robot.fire(3);
			else if (distance < 250)
				robot.fire(2);
			else
				robot.fire(1);
		}

		private void attackMove(int angle, int distance) {
			robot.turnTo(angle);
			if (distance > 200)
				robot.ahead(300);
			else
				robot.ahead(150);
		}

		private void erraticMove() {
			if (directionForward)
				robot.ahead(75);
			else
				robot.back(75);
			directionForward = !directionForward;
		}

		private void firstMove() {
			if (isFirstTime) {
				robot.turnTo(180);
				robot.out.println("LaboRobotStrategyOne");
				isFirstTime = false;
			}
		}

	}

	private class LaboRobotStrategyTwo implements LaboRobotStrategyInterface {

		private boolean isFirstTime = true;

		@Override
		public void runStrategy() {
			this.firstMove();
			robot.ahead(300);
		}

		@Override
		public void onScannedRobot() {
			int distance = robot.scannedDistance;
			robot.out.println("distance: " + distance);
			if ((distance != -1) && (distance < 150))
				robot.turnTo(robot.heading + 90);
		}

		@Override
		public void onHitByBullet() {
			int angle = robot.hitByBulletBearing;
			robot.out.println("angle: " + angle);
			if (angle == 0)
				robot.fire(3);
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
}

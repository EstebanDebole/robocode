package laboratorio;

public class LaboRobotStrategyOne implements LaboRobotStrategy {
	
	public LaboRobot robot;
	
	public LaboRobotStrategyOne(LaboRobot robot) {
		this.robot = robot;
	}

	@Override
	public void onScannedRobot() {
		
		if (robot.scannedDistance < 50 && robot.energy > 50) {
			int angle = robot.scannedAngle;
			System.out.println(angle);
			robot.turnGunTo(angle - robot.gunHeading);
			robot.fire(1);
		} // otherwise, fire 1.
		else {
			robot.ahead(8);
		}
		
	}

	@Override
	public void onHitByBullet() {
		robot.ahead(100);
		
	}

	@Override
	public void onHitWall() {
		robot.back(100);
	}

}

package laboratorio;

public class LaboRobotStrategyOne implements LaboRobotStrategy {
	
	public LaboRobot robot;
	public boolean direction = true;
	public boolean first = true;
	
	public LaboRobotStrategyOne(LaboRobot robot) {
		this.robot = robot;
	}
	
	@Override
	public void runStrategy() {
		this.hello();
		this.erraticMove();	
		//360° mapping trigger the event onScannedRobot()
		robot.turnGunLeft(360);
	}

	
	@Override
	public void onScannedRobot() {
		int distance = robot.scannedDistance;
		//robot.out.println("distance: "+distance);
		int angle = robot.scannedAngle;
		//robot.out.println("angle: "+angle);
		int velocity = robot.scannedVelocity;
		//robot.out.println("velocity: "+velocity);
		//robot.out.println("heading: "+robot.scannedHeading); 
		
		if (distance > 350) {
			this.attackMove(angle,distance);
		} else if ((robot.gunReady) && (velocity==0)) {
			this.attack(angle,distance);
		}
	}

	@Override
	public void onHitByBullet() {
		int angle = robot.hitByBulletAngle;
		robot.turnTo(angle+90);
		robot.turnGunTo(angle);		
	}

	@Override
	public void onHitWall() {
		robot.turnTo(robot.heading+90);
	}
	
	
	private void attack(int angle, int distance) {
		robot.turnGunTo(angle);	
		if (distance < 200) {
			robot.fire(3);
		} else if (distance < 250) {
			robot.fire(2);
		}else {
			robot.fire(1);
		}
	}
	
	private void attackMove(int angle, int distance) {
		robot.turnTo(angle);
		if (distance > 200) {
			robot.ahead(300);
		}else {
			robot.ahead(150);
		}
	}
	
	private void erraticMove() {
		if (direction) {
			robot.ahead(75);
		} else {
			robot.back(75);
		}
		direction = !direction;
	}
	
	private void hello(){
		if (first) {
			robot.turnTo(180);
			robot.out.println("LaboRobotStrategyOne");
			first = false;
		}
	}

}

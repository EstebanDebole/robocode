package laboratorio;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

public class LaboRobot extends JuniorRobot {

	private LaboRobotStrategy strategy; 
	
	@Override	
	public void run() {
		strategy = new LaboRobotStrategyTwo(this); //Set the initial strategy
		boolean strategyChange = false;
		setColors(red,black,black);
		while (true) {
			strategy.runStrategy();
			if ((!strategyChange) && (this.others == 1)) {
				strategy = new LaboRobotStrategyOne(this);
				strategyChange = true;
			}
		}
	}

	//when the radar detects another robot. 
	//When this event occurs the scannedDistance, scannedAngle, scannedBearing, and scannedEnergy 
	//field values are automatically updated.
	@Override
	public void onScannedRobot() {
		strategy.onScannedRobot();
	}

	//onHitByBullet: What to do when you're hit by a bullet
	@Override
	public void onHitByBullet() {
		strategy.onHitByBullet();
	}
	
	//onHitWall: What to do when you hit a wall
	@Override
	public void onHitWall() {
		strategy.onHitWall();
	}
}
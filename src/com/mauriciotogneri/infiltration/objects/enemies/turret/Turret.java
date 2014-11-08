package com.mauriciotogneri.infiltration.objects.enemies.turret;

import java.util.ArrayList;
import java.util.List;
import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Alarm.OnAlarmRing;
import com.misty.kernel.Process;

public class Turret extends Process
{
	private final float speed;
	private final List<TurretBeam> beams = new ArrayList<TurretBeam>();
	
	public Turret(float x, float y, int frequency, float speed, int orientationHorizontal)
	{
		super(true, true);
		
		this.x = x;
		this.y = y;
		this.z = 3;
		this.orientationHorizontal = orientationHorizontal;
		
		this.speed = speed;
		
		setImage(Resources.Images.Enemies.Turret.BASE);
		
		setAlarm(new OnAlarmRing()
		{
			@Override
			public boolean onAlarmRing()
			{
				shot();
				
				return true;
			}
		}, frequency);
	}
	
	private void shot()
	{
		TurretBeam beam = new TurretBeam(this.x, this.y, 0, this.speed, 1, 0);
		beam.start();
		
		this.beams.add(beam);
	}
}
package com.mauriciotogneri.infiltration.objects.enemies.turret;

import java.util.ArrayList;
import java.util.List;
import com.mauriciotogneri.infiltration.objects.Building;
import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Alarm.OnAlarmRing;
import com.misty.kernel.Process;

public class Turret extends Process
{
	private final Building building;
	private final float speed;
	
	private final Object lock = new Object();
	private final List<TurretBeam> beams = new ArrayList<TurretBeam>();
	
	public Turret(float x, float y, int frequency, float speed, int orientationHorizontal, Building building)
	{
		super(true, true);
		
		this.x = x;
		this.y = y;
		this.z = 3;
		this.orientationHorizontal = orientationHorizontal;
		
		this.speed = speed;
		
		this.building = building;
		
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
	
	public void checkCollision(TurretBeam beam)
	{
		if (this.building.collide(beam))
		{
			beam.finish();
			
			synchronized (this.lock)
			{
				this.beams.remove(beam);
			}
		}
	}
	
	public void reset()
	{
		synchronized (this.lock)
		{
			for (TurretBeam beam : this.beams)
			{
				beam.finish();
			}
			
			this.beams.clear();
		}
	}
	
	private void shot()
	{
		TurretBeam beam = new TurretBeam(this, this.x, this.y, 0, this.speed, this.orientationHorizontal, 0);
		beam.start();
		
		playSound(Resources.Audio.Sound.Enemies.TURRET_BEAM);
		
		synchronized (this.lock)
		{
			this.beams.add(beam);
		}
	}
}
package com.mauriciotogneri.infiltration.objects.enemies.laser;

import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Alarm.OnAlarmRing;
import com.misty.kernel.Process;

public class LaserBeam extends Process
{
	private final boolean initialVisible;
	private final int timeActive;
	private final int timeInactive;
	
	public LaserBeam(float x, float y, int timeActive, int timeInactive, boolean visible)
	{
		super(true, true);
		
		this.x = x;
		this.y = y;
		this.z = 2;
		
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.initialVisible = visible;
		
		setImage(Resources.Images.Enemies.Laser.BEAM);
		
		reset();
	}
	
	private void configureAlarm()
	{
		removeAlarms();
		
		if (this.visible)
		{
			setAlarm(new OnAlarmRing()
			{
				@Override
				public boolean onAlarmRing()
				{
					alarmRing();
					
					return false;
				}
			}, this.timeActive);
		}
		else
		{
			setAlarm(new OnAlarmRing()
			{
				@Override
				public boolean onAlarmRing()
				{
					alarmRing();
					
					return false;
				}
			}, this.timeInactive);
		}
	}
	
	private void alarmRing()
	{
		this.visible = !this.visible;
		
		configureAlarm();
	}
	
	public void reset()
	{
		this.visible = this.initialVisible;
		
		configureAlarm();
	}
}
package com.mauriciotogneri.infiltration.objects.enemies.laser;

import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class LaserBase extends Process
{
	public LaserBase(float x, float y, float angle)
	{
		super(false, false);
		
		this.x = x;
		this.y = y;
		this.z = 3;
		this.angle = angle;
		
		setImage(Resources.Images.Enemies.Laser.BASE);
	}
}
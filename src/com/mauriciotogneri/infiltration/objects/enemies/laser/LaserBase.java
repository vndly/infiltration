package com.mauriciotogneri.infiltration.objects.enemies.laser;

import com.mauriciotogneri.infiltration.objects.levels.Level;
import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class LaserBase extends Process
{
	public LaserBase(int x, int y, float angle)
	{
		super(false, false);
		
		this.x = Level.BLOCK_SIZE * x;
		this.y = Level.BLOCK_SIZE * y;
		this.z = 3;
		this.angle = angle;
		
		setImage(Resources.Images.Enemies.Laser.BASE);
	}
}
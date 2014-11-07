package com.mauriciotogneri.infiltration.objects.enemies.laser;

import java.util.ArrayList;
import java.util.List;
import com.mauriciotogneri.infiltration.objects.Level;

public class Laser
{
	private final LaserBase baseBottom;
	private final LaserBase baseTop;
	private final List<LaserBeam> beams = new ArrayList<LaserBeam>();
	
	public Laser(float x, float y, int height, int timeActive, int timeInactive, boolean enable)
	{
		this.baseBottom = new LaserBase(x, y, 0);
		this.baseBottom.start();
		
		this.baseTop = new LaserBase(x, y + (Level.BLOCK_SIZE * (height - 1)), 180);
		this.baseTop.start();
		
		for (int i = 0; i < height; i++)
		{
			LaserBeam beam = new LaserBeam(x, y + (Level.BLOCK_SIZE * i), timeActive, timeInactive, enable);
			beam.start();
			this.beams.add(beam);
		}
	}
}
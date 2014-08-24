package com.mauriciotogneri.wakeupmax;

import com.mauriciotogneri.wakeupmax.objects.Max;
import com.mauriciotogneri.wakeupmax.objects.World;
import com.misty.graphics.ScreenResolution;
import com.misty.kernel.Misty;

public class Main extends Misty
{
	public static int RESOLUTION_X = 17 * World.BLOCK_SIZE;
	public static int RESOLUTION_Y = 10 * World.BLOCK_SIZE;

	@Override
	public void start()
	{
		World world = new World();
		world.create(this);

		Max max = new Max(world);
		max.start();
	}

	@Override
	public ScreenResolution getResolution()
	{
		return new ScreenResolution(Main.RESOLUTION_X, Main.RESOLUTION_Y);
	}
}
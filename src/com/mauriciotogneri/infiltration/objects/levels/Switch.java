package com.mauriciotogneri.infiltration.objects.levels;

import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class Switch extends Process
{
	public Switch(int x, int y)
	{
		super(false, true);
		
		this.x = Level.BLOCK_SIZE * x;
		this.y = Level.BLOCK_SIZE * y;
		this.z = 3;
		
		setImage(Resources.Images.Levels.Switch.SWITCH_OFF);
	}
	
	public void activate()
	{
		setImage(Resources.Images.Levels.Switch.SWITCH_ON);
		playSound(Resources.Audio.Sound.Levels.SWITCH_ON);
	}
}
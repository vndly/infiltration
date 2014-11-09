package com.mauriciotogneri.infiltration.objects.levels;

import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.graphics.Animation;
import com.misty.kernel.Process;

public class Door extends Process
{
	private boolean opened = false;
	private final Animation animationOpening;
	
	public Door(int x, int y)
	{
		super(true, false);
		
		this.x = Level.BLOCK_SIZE * x;
		this.y = Level.BLOCK_SIZE * y;
		this.z = 3;
		
		this.animationOpening = new Animation(false, 0.1f, Resources.Images.Levels.Door.DOOR_OPENING_1, Resources.Images.Levels.Door.DOOR_OPENING_2, Resources.Images.Levels.Door.DOOR_OPENING_3);
		
		setImage(Resources.Images.Levels.Door.DOOR_CLOSED);
	}
	
	public void open()
	{
		this.opened = true;
		
		playSound(Resources.Audio.Sound.Levels.DOOR_OPENING);
	}
	
	@Override
	public void update(float delta)
	{
		if (this.opened)
		{
			setImage(this.animationOpening.getSprite(delta));
		}
	}
}
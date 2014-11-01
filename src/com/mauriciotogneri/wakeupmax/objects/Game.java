package com.mauriciotogneri.wakeupmax.objects;

import com.misty.kernel.Process;

public class Game extends Process
{
	private final Level currentLevel;
	
	public Game()
	{
		super(true, false);

		this.currentLevel = new Level();
	}

	@Override
	public void update(float delta)
	{
		this.currentLevel.update(delta);
	}
}
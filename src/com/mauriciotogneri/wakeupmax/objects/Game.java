package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.controls.Controls;
import com.mauriciotogneri.wakeupmax.controls.Input;
import com.mauriciotogneri.wakeupmax.utils.Resources;
import com.misty.kernel.Process;

public class Game extends Process
{
	private final Level currentLevel;
	private final Controls controls;
	
	public Game()
	{
		super(true, false);
		
		this.controls = new Controls();
		this.currentLevel = new Level();
		
		playMusic(Resources.Music.MUSIC);
	}
	
	@Override
	public void update(float delta)
	{
		Input input = this.controls.getInput();
		this.currentLevel.update(delta, input);
	}
}
package com.mauriciotogneri.infiltration.objects;

import com.mauriciotogneri.infiltration.controls.Controls;
import com.mauriciotogneri.infiltration.controls.Input;
import com.mauriciotogneri.infiltration.objects.levels.Level;
import com.mauriciotogneri.infiltration.utils.Resources;
import com.misty.kernel.Process;

public class Game extends Process
{
	private final Level currentLevel;
	private final Controls controls;
	
	public Game()
	{
		super(true, false);
		
		this.controls = new Controls(getResolutionX());
		this.currentLevel = new Level();
		
		playMusic(Resources.Audio.Music.MUSIC);
	}
	
	@Override
	public void update(float delta)
	{
		Input input = this.controls.getInput();
		this.currentLevel.update(delta, input);
	}
}
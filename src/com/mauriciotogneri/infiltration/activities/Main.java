package com.mauriciotogneri.infiltration.activities;

import com.mauriciotogneri.infiltration.objects.Game;
import com.mauriciotogneri.infiltration.objects.levels.Level;
import com.misty.graphics.ScreenResolution;
import com.misty.kernel.Misty;

public class Main extends Misty
{
	// public static final int RESOLUTION_ = 17 * Level.BLOCK_SIZE;
	
	private static int RESOLUTION_Y = 10 * Level.BLOCK_SIZE;
	
	private Game game;
	
	@Override
	public void start()
	{
		this.game = new Game();
		this.game.start();
	}
	
	@Override
	public ScreenResolution getResolution()
	{
		return ScreenResolution.fromVertical(Main.RESOLUTION_Y);
	}
}
package com.mauriciotogneri.infiltration.activities;

import com.mauriciotogneri.infiltration.objects.Game;
import com.mauriciotogneri.infiltration.objects.Level;
import com.misty.graphics.ScreenResolution;
import com.misty.graphics.textures.TextureManager;
import com.misty.kernel.Misty;

public class Main extends Misty
{
	public static final int RESOLUTION_X = 17 * Level.BLOCK_SIZE;
	
	// public static int RESOLUTION_Y = 10 * Building.BLOCK_SIZE;
	
	private Game game;
	
	@Override
	public void start()
	{
		TextureManager.loadTexture("images/enemies/turret/turret_beam.png");
		
		this.game = new Game();
		this.game.start();
	}
	
	@Override
	public ScreenResolution getResolution()
	{
		return ScreenResolution.fromHorizontal(Main.RESOLUTION_X);
	}
}
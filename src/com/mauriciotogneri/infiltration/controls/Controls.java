package com.mauriciotogneri.infiltration.controls;

import com.mauriciotogneri.infiltration.utils.Resources;

public class Controls
{
	private final Button buttonLeft;
	private final Button buttonRight;
	private final Button buttonUp;
	
	private final Input input = new Input();
	
	public Controls(int resolutionX)
	{
		this.buttonLeft = new Button(10, 10, 90, Resources.Images.Controls.ARROW);
		this.buttonLeft.start();
		
		this.buttonRight = new Button(this.buttonLeft.x + Button.SIZE + 30, 10, -90, Resources.Images.Controls.ARROW);
		this.buttonRight.start();
		
		this.buttonUp = new Button(resolutionX - Button.SIZE - 10, 10, 0, Resources.Images.Controls.ARROW);
		this.buttonUp.start();
	}
	
	public Input getInput()
	{
		this.input.left = this.buttonLeft.isPressed();
		this.input.right = this.buttonRight.isPressed();
		this.input.up = this.buttonUp.isPressed();
		
		return this.input;
	}
}
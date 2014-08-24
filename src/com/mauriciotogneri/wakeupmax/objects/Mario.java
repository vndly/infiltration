package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.Main;
import com.mauriciotogneri.wakeupmax.Resources;
import com.misty.graphics.Animation;
import com.misty.kernel.Process;

public class Mario extends Process
{
	private boolean jumping = false;
	
	private float accelerationX = 0;
	private float accelerationY = 0;
	
	private float speedX = 0;
	private float speedY = 0;
	
	private final World world;
	
	private final Button buttonLeft;
	private final Button buttonRight;
	private final Button buttonUp;
	
	private final Sky sky;
	private final Mountains mountains;
	
	private final Animation animationRight;
	private final Animation animationLeft;
	
	private boolean facingRight = true;
	
	private static final int MAX_SPEED_Y = 10;
	private static final int MAX_ACCELERATION_Y_DOWN = -300;
	private static final int GRAVITY = -1100;
	
	public Mario(World world)
	{
		super(true, true);
		
		this.world = world;
		
		this.x = 0;
		this.y = (World.BLOCK_SIZE * 3);
		this.z = 2;
		
		setImage(Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_1);
		
		this.animationRight = new Animation(0.08f, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_1, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_2, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_3, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_4);
		this.animationLeft = new Animation(0.08f, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_1, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_2, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_3, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_4);
		
		this.buttonLeft = new Button(5, 5, Resources.Images.Controls.ARROW_LEFT);
		this.buttonLeft.start();
		
		this.buttonRight = new Button(Button.SIZE + 15, 5, Resources.Images.Controls.ARROW_RIGHT);
		this.buttonRight.start();
		
		this.buttonUp = new Button(Main.RESOLUTION_X - Button.SIZE - 5, 5, Resources.Images.Controls.ARROW_UP);
		this.buttonUp.start();
		
		this.sky = new Sky();
		this.sky.start();
		
		this.mountains = new Mountains();
		this.mountains.start();
		
		setJumping(false);
		updateButtons();
		playMusic(Resources.Music.MUSIC);
	}
	
	@Override
	public void update(float delta)
	{
		processInput(delta);
		updatePosition(delta);
		updateCamera();
		updateButtons();

		this.sky.update(this.camera);
		this.mountains.update(this.camera);
	}
	
	private void updatePosition(float delta)
	{
		float newX = updateX(delta);
		float newY = updateY(delta);
		
		if (newX != this.x)
		{
			this.x = newX;
			
			if (this.speedX < 0)
			{
				this.world.checkLeft(this);
			}
			
			if (this.speedX > 0)
			{
				this.world.checkRight(this);
			}
		}
		
		if (newY != this.y)
		{
			this.y = newY;
			
			if (this.speedY < 0)
			{
				this.world.checkBottom(this);
			}
			
			if (this.speedY > 0)
			{
				this.world.checkUp(this);
			}
		}
	}
	
	private float updateX(float delta)
	{
		float result = this.x;
		
		if (this.accelerationX > 0)
		{
			this.accelerationX -= 20;
		}
		else if (this.accelerationX < 0)
		{
			this.accelerationX += 20;
		}
		
		this.speedX = this.accelerationX * delta;
		result = this.x + this.speedX;
		
		if (result < 0)
		{
			result = 0;
		}
		
		int limitX = 1024 - this.width;
		
		if (result > limitX)
		{
			result = limitX;
		}
		
		return result;
	}
	
	private float updateY(float delta)
	{
		float result = this.y;
		
		applyGravity(delta);
		
		this.speedY = this.accelerationY * delta;
		
		if (Math.abs(this.speedY) > Mario.MAX_SPEED_Y)
		{
			this.speedY = Mario.MAX_SPEED_Y * Math.signum(this.speedY);
		}
		
		result = this.y + this.speedY;
		
		return result;
	}
	
	private void applyGravity(float delta)
	{
		if (this.jumping)
		{
			this.accelerationY += Mario.GRAVITY * delta;
		}
		
		if (this.accelerationY < Mario.MAX_ACCELERATION_Y_DOWN)
		{
			this.accelerationY = Mario.MAX_ACCELERATION_Y_DOWN;
		}
	}
	
	private void updateCamera()
	{
		this.camera.x = (this.x - (Main.RESOLUTION_X / 2)) + this.width;
		this.camera.y = 0; // this.y - (World.BLOCK_SIZE * 3);
		
		if (this.camera.x < 0)
		{
			this.camera.x = 0;
		}
		
		int limitX = 1024 - Main.RESOLUTION_X;
		
		if (this.camera.x > limitX)
		{
			this.camera.x = limitX;
		}
	}
	
	private void updateButtons()
	{
		this.buttonLeft.x = this.camera.x + this.buttonLeft.initialX;
		this.buttonLeft.y = this.camera.y + this.buttonLeft.initialY;
		
		this.buttonRight.x = this.camera.x + this.buttonRight.initialX;
		this.buttonRight.y = this.camera.y + this.buttonRight.initialY;
		
		this.buttonUp.x = this.camera.x + this.buttonUp.initialX;
		this.buttonUp.y = this.camera.y + this.buttonUp.initialY;
	}
	
	private void processInput(float delta)
	{
		if (isPressed(this.buttonLeft.initialX, this.buttonLeft.initialX + Button.SIZE, this.buttonLeft.initialY, this.buttonLeft.initialY + Button.SIZE))
		{
			this.accelerationX = -160;
			this.facingRight = false;
			
			if (!this.jumping)
			{
				setImage(this.animationLeft.getSprite(delta));
			}
			else
			{
				setImage(Resources.Images.Sprites.CHARACTER_JUMPING_LEFT);
			}
		}
		
		if (isPressed(this.buttonRight.initialX, this.buttonRight.initialX + Button.SIZE, this.buttonRight.initialY, this.buttonRight.initialY + Button.SIZE))
		{
			this.accelerationX = 160;
			this.facingRight = true;
			
			if (!this.jumping)
			{
				setImage(this.animationRight.getSprite(delta));
			}
			else
			{
				setImage(Resources.Images.Sprites.CHARACTER_JUMPING_RIGHT);
			}
		}
		
		if (isPressed(this.buttonUp.initialX, this.buttonUp.initialX + Button.SIZE, this.buttonUp.initialY, this.buttonUp.initialY + Button.SIZE) && (!this.jumping))
		{
			setJumping(true);
			
			if (this.facingRight)
			{
				setImage(Resources.Images.Sprites.CHARACTER_JUMPING_RIGHT);
			}
			else
			{
				setImage(Resources.Images.Sprites.CHARACTER_JUMPING_LEFT);
			}
		}
		
		if ((this.accelerationX == 0.0) && (!this.jumping))
		{
			if (this.facingRight)
			{
				setImage(Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_1);
			}
			else
			{
				setImage(Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_1);
			}
		}
	}
	
	public void setJumping(boolean value)
	{
		this.jumping = value;
		
		if (this.jumping)
		{
			playSound(Resources.Sound.JUMP);
			this.accelerationY = 410;
		}
		else
		{
			this.accelerationY = Mario.MAX_ACCELERATION_Y_DOWN;
		}
	}
}
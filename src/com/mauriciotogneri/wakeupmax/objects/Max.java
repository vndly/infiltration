package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.utils.Resources;
import com.misty.graphics.Animation;
import com.misty.kernel.Process;

public class Max extends Process
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
	
	private final Background background;
	
	private final Animation animationRight;
	private final Animation animationLeft;
	
	private boolean facingRight = true;
	
	private static final int MAX_SPEED_Y = 10;
	private static final int MAX_ACCELERATION_Y_DOWN = -400;
	private static final int GRAVITY = -2200;

	private static final int JUMP_FORCE = 590;
	
	private static final int HORIZONTAL_SPEED = 200;
	private static final int HORIZONTAL_FRICTION = 20;
	
	public Max(World world)
	{
		super(true, true);
		
		this.world = world;
		
		this.x = 2 * World.BLOCK_SIZE;
		this.y = 4 * World.BLOCK_SIZE;
		this.z = 2;
		
		setImage(Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_1);
		
		this.animationRight = new Animation(0.08f, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_1, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_2, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_3, Resources.Images.Sprites.CHARACTER_RUNNING_RIGHT_4);
		this.animationLeft = new Animation(0.08f, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_1, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_2, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_3, Resources.Images.Sprites.CHARACTER_RUNNING_LEFT_4);
		
		this.buttonLeft = new Button(10, 10, Resources.Images.Controls.ARROW_LEFT);
		this.buttonLeft.start();
		
		this.buttonRight = new Button(Button.SIZE + 40, 10, Resources.Images.Controls.ARROW_RIGHT);
		this.buttonRight.start();
		
		this.buttonUp = new Button(getResolutionX() - Button.SIZE - 15, 10, Resources.Images.Controls.ARROW_UP);
		this.buttonUp.start();
		
		this.background = new Background(0, 2, 1, 2, 2, "WALL");
		this.background.start();
		
		setJumping(false);
		playMusic(Resources.Music.MUSIC);
	}
	
	@Override
	public void update(float delta)
	{
		processInput(delta);
		updatePosition(delta);
		updateCamera();

		this.background.update(this.camera);
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
			this.accelerationX -= Max.HORIZONTAL_FRICTION;
		}
		else if (this.accelerationX < 0)
		{
			this.accelerationX += Max.HORIZONTAL_FRICTION;
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

		if (Math.abs(this.speedY) > Max.MAX_SPEED_Y)
		{
			this.speedY = Max.MAX_SPEED_Y * Math.signum(this.speedY);
		}
		
		result = this.y + this.speedY;
		
		return result;
	}
	
	private void applyGravity(float delta)
	{
		this.accelerationY += Max.GRAVITY * delta;
		
		if (this.accelerationY < Max.MAX_ACCELERATION_Y_DOWN)
		{
			this.accelerationY = Max.MAX_ACCELERATION_Y_DOWN;
		}
	}
	
	private void updateCamera()
	{
		this.camera.x = (this.x - (getResolutionX() / 2)) + this.width;
		this.camera.y = (this.y - (getResolutionY() / 2)) + this.height;
		
		if (this.camera.x < 0)
		{
			this.camera.x = 0;
		}
		
		int limitX = 960 - getResolutionX();
		
		if (this.camera.x > limitX)
		{
			this.camera.x = limitX;
		}
	}
	
	private void processInput(float delta)
	{
		if (isPressed(this.buttonLeft.x, this.buttonLeft.x + Button.SIZE, this.buttonLeft.y, this.buttonLeft.y + Button.SIZE))
		{
			this.accelerationX = -Max.HORIZONTAL_SPEED;
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
		
		if (isPressed(this.buttonRight.x, this.buttonRight.x + Button.SIZE, this.buttonRight.y, this.buttonRight.y + Button.SIZE))
		{
			this.accelerationX = Max.HORIZONTAL_SPEED;
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
		
		if (isPressed(this.buttonUp.x, this.buttonUp.x + Button.SIZE, this.buttonUp.y, this.buttonUp.y + Button.SIZE) && (!this.jumping))
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
			this.accelerationY = Max.JUMP_FORCE;
		}
		else
		{
			this.accelerationY = 0;
		}
	}
}
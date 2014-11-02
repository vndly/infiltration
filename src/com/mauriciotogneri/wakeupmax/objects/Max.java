package com.mauriciotogneri.wakeupmax.objects;

import com.mauriciotogneri.wakeupmax.controls.Input;
import com.mauriciotogneri.wakeupmax.utils.Resources;
import com.misty.graphics.Animation;
import com.misty.kernel.Process;
import com.misty.math.Vector;

public class Max extends Process
{
	private final Building building;
	private final Animation animationRunning;
	private final Vector position = new Vector();
	private final Vector acceleration = new Vector();
	private final Vector velocity = new Vector();
	private State state = State.IDLE;
	private boolean jumpingPressed = false;
	
	private static final float FRICTION = 0.6f;
	private static final int MAX_JUMP_SPEED = Level.BLOCK_SIZE * 18;
	private static final int MAX_FALL_SPEED = -Level.BLOCK_SIZE * 10;
	private static final int GRAVITY = 5 * Max.MAX_FALL_SPEED;
	private static final int MAX_RUNNING_SPEED = Level.BLOCK_SIZE * 6;
	
	private enum State
	{
		IDLE, RUNNING, JUMPING
	}
	
	public Max(Building building)
	{
		super(false, true);
		
		this.building = building;
		
		this.x = Level.BLOCK_SIZE * 2; // 3
		this.y = Level.BLOCK_SIZE * 3; // 10
		this.z = 2;
		
		this.position.set(this.x, this.y);
		
		setImage(Resources.Images.Sprites.CHARACTER_IDLE);
		
		this.animationRunning = new Animation(0.05f, Resources.Images.Sprites.CHARACTER_RUNNING_1, Resources.Images.Sprites.CHARACTER_RUNNING_2, Resources.Images.Sprites.CHARACTER_RUNNING_3, Resources.Images.Sprites.CHARACTER_RUNNING_4);
	}
	
	public void update(float delta, Input input)
	{
		processInput(input);
		
		updatePosition(delta);
		
		checkPosition();
		
		setSprite(delta);
		
		updateCamera();
	}
	
	private void setSprite(float delta)
	{
		if (this.state == State.IDLE)
		{
			setImage(Resources.Images.Sprites.CHARACTER_IDLE);
			this.animationRunning.reset();
		}
		else if (this.state == State.RUNNING)
		{
			setImage(this.animationRunning.getSprite(delta));
		}
		else if (this.state == State.JUMPING)
		{
			setImage(Resources.Images.Sprites.CHARACTER_JUMPING);
			this.animationRunning.reset();
		}
	}
	
	private void updatePosition(float delta)
	{
		this.acceleration.y = Max.GRAVITY;
		this.acceleration.mul(delta);
		this.velocity.add(this.acceleration);
		
		if (this.acceleration.x > 0)
		{
			this.velocity.x = Max.MAX_RUNNING_SPEED;
		}
		else if (this.acceleration.x < 0)
		{
			this.velocity.x = -Max.MAX_RUNNING_SPEED;
		}
		else if (this.acceleration.x == 0)
		{
			this.velocity.x *= Max.FRICTION;
			
			// TODO: ADJUST LIMIT
			if (Math.abs(this.velocity.x) < 1)
			{
				this.velocity.x = 0;
			}
		}
		
		if (this.velocity.y < Max.MAX_FALL_SPEED)
		{
			this.velocity.y = Max.MAX_FALL_SPEED;
		}
		
		this.position.add(this.velocity.copy().mul(delta));
	}
	
	public void touchGround()
	{
		if (this.state == State.JUMPING)
		{
			this.state = State.IDLE;
		}
	}
	
	public void touchCeiling()
	{
		this.velocity.y = 0;
	}
	
	private void checkPosition()
	{
		float newX = this.position.x;
		float newY = this.position.y;
		
		if (newX != this.x)
		{
			this.x = newX;
			
			if (this.velocity.x < 0)
			{
				this.building.checkLeft(this);
			}
			
			if (this.velocity.x > 0)
			{
				this.building.checkRight(this);
			}
		}
		
		if (newY != this.y)
		{
			this.y = newY;
			
			if (this.velocity.y < 0)
			{
				this.building.checkBottom(this);
			}
			
			if (this.velocity.y > 0)
			{
				this.building.checkTop(this);
			}
		}
		
		this.position.x = this.x;
		this.position.y = this.y;
	}
	
	private void processInput(Input input)
	{
		if (input.up)
		{
			if ((!this.jumpingPressed) && (this.state != State.JUMPING))
			{
				playSound(Resources.Audio.Sound.JUMP);
				
				this.jumpingPressed = true;
				this.state = State.JUMPING;
				this.velocity.y = Max.MAX_JUMP_SPEED;
			}
		}
		else
		{
			this.jumpingPressed = false;
		}
		
		if (input.left)
		{
			this.orientationHorizontal = -1;
			
			if (this.state == State.IDLE)
			{
				this.state = State.RUNNING;
			}
			
			this.acceleration.x = -1;
		}
		else if (input.right)
		{
			this.orientationHorizontal = 1;
			
			if (this.state == State.IDLE)
			{
				this.state = State.RUNNING;
			}
			
			this.acceleration.x = 1;
		}
		else
		{
			if (this.state == State.RUNNING)
			{
				this.state = State.IDLE;
			}
			
			this.acceleration.x = 0;
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
		
		int limitX = (21 * Level.BLOCK_SIZE) - this.camera.width;
		
		if (this.camera.x > limitX)
		{
			this.camera.x = limitX;
		}
		
		if (this.camera.y < 0)
		{
			this.camera.y = 0;
		}
	}
}
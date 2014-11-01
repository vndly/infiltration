package com.mauriciotogneri.wakeupmax.utils;

public class Resources
{
	public static class Images
	{
		private static final String ROOT_IMAGES = "images/";
		
		public static class Sprites
		{
			private static final String ROOT = Images.ROOT_IMAGES + "sprites/";
			
			public static final String CHARACTER_IDLE = Sprites.ROOT + "idle.png";
			public static final String CHARACTER_JUMPING = Sprites.ROOT + "jumping.png";
			public static final String CHARACTER_RUNNING_1 = Sprites.ROOT + "running_1.png";
			public static final String CHARACTER_RUNNING_2 = Sprites.ROOT + "running_2.png";
			public static final String CHARACTER_RUNNING_3 = Sprites.ROOT + "running_3.png";
			public static final String CHARACTER_RUNNING_4 = Sprites.ROOT + "running_4.png";
		}
		
		public static class Backgrounds
		{
			private static final String ROOT = Images.ROOT_IMAGES + "backgrounds/";
			
			public static final String WALL = Backgrounds.ROOT + "wall.png";
		}
		
		public static class Blocks
		{
			private static final String ROOT = Images.ROOT_IMAGES + "blocks/";
			
			public static final String STONE = Blocks.ROOT + "stone.png";
		}
		
		public static class Controls
		{
			private static final String ROOT = Images.ROOT_IMAGES + "controls/";
			
			public static final String ARROW_LEFT = Controls.ROOT + "arrow_left.png";
			public static final String ARROW_RIGHT = Controls.ROOT + "arrow_right.png";
			public static final String ARROW_UP = Controls.ROOT + "arrow_up.png";
		}
	}
	
	public static class Music
	{
		private static final String ROOT = "audio/music/";
		
		public static final String MUSIC = Music.ROOT + "music.ogg";
	}
	
	public static class Sound
	{
		private static final String ROOT = "audio/sounds/";
		
		public static final String JUMP = Sound.ROOT + "jump.ogg";
	}
}
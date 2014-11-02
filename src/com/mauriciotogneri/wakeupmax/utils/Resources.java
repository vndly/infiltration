package com.mauriciotogneri.wakeupmax.utils;

public class Resources
{
	public static class Images
	{
		private static final String ROOT_IMAGES = "images/";
		
		public static class Protagonist
		{
			private static final String ROOT = Images.ROOT_IMAGES + "sprites/";
			
			public static final String CHARACTER_IDLE = Protagonist.ROOT + "idle.png";
			public static final String CHARACTER_JUMPING = Protagonist.ROOT + "jumping.png";
			public static final String CHARACTER_RUNNING_1 = Protagonist.ROOT + "running_1.png";
			public static final String CHARACTER_RUNNING_2 = Protagonist.ROOT + "running_2.png";
			public static final String CHARACTER_RUNNING_3 = Protagonist.ROOT + "running_3.png";
			public static final String CHARACTER_RUNNING_4 = Protagonist.ROOT + "running_4.png";
		}
		
		public static class Levels
		{
			private static final String ROOT = Images.ROOT_IMAGES + "levels/";
			
			public static final String LEVEL_1 = Levels.ROOT + "level_1.png";
		}
		
		public static class Controls
		{
			private static final String ROOT = Images.ROOT_IMAGES + "controls/";
			
			public static final String ARROW = Controls.ROOT + "arrow.png";
		}
	}
	
	public static class Levels
	{
		private static final String ROOT = "levels/";
		
		public static final String LEVEL_1 = Levels.ROOT + "level_1.txt";
	}
	
	public static class Audio
	{
		private static final String ROOT_AUDIO = "audio/";
		
		public static class Music
		{
			private static final String ROOT = Audio.ROOT_AUDIO + "music/";
			
			public static final String MUSIC = Music.ROOT + "music.ogg";
		}
		
		public static class Sound
		{
			private static final String ROOT = Audio.ROOT_AUDIO + "sounds/";
			
			public static final String JUMP = Sound.ROOT + "jump.ogg";
		}
	}
}
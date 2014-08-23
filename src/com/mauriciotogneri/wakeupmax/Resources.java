package com.mauriciotogneri.wakeupmax;

public class Resources
{
	public static class Images
	{
		private static final String ROOT_IMAGES = "images/";

		public static class Sprites
		{
			private static final String ROOT = Images.ROOT_IMAGES + "sprites/";

			public static final String MARIO_JUMPING_LEFT = Sprites.ROOT + "mario_jumping_left.png";
			public static final String MARIO_JUMPING_RIGHT = Sprites.ROOT + "mario_jumping_right.png";
			public static final String MARIO_RUNNING_LEFT_1 = Sprites.ROOT + "mario_running_left_1.png";
			public static final String MARIO_RUNNING_LEFT_2 = Sprites.ROOT + "mario_running_left_2.png";
			public static final String MARIO_RUNNING_LEFT_3 = Sprites.ROOT + "mario_running_left_3.png";
			public static final String MARIO_RUNNING_RIGHT_1 = Sprites.ROOT + "mario_running_right_1.png";
			public static final String MARIO_RUNNING_RIGHT_2 = Sprites.ROOT + "mario_running_right_2.png";
			public static final String MARIO_RUNNING_RIGHT_3 = Sprites.ROOT + "mario_running_right_3.png";
		}
		
		public static class Levels
		{
			private static final String ROOT = Images.ROOT_IMAGES + "levels/";

			public static final String GROUND_1 = Levels.ROOT + "ground_1.png";
			public static final String GROUND_2 = Levels.ROOT + "ground_2.png";
			public static final String MOUNTAINS = Levels.ROOT + "mountains.png";
			public static final String SKY = Levels.ROOT + "sky.png";
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
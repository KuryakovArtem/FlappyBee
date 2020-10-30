package info.fandroid.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.fandroid.game.FlappyBee;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBee.WIDTH;
		config.height = FlappyBee.HEIGHT;
		config.title = FlappyBee.TITLE;
		new LwjglApplication(new FlappyBee(), config);
	}
}

package engine.menu.button;

import engine.level.Level;
import engine.menu.LevelSelectMenu;
import engine.menu.Menu;
import engine.menu.OptionsMenu;
import engine.menu.click.ClickBehavior;
import engine.menu.click.ExitProgram;
import engine.menu.click.PlayLevel;
import engine.menu.click.SwitchMenu;

public class ButtonFactory {
	
	public static Button createButton(String text) {
		return new Button(text);
	}
	
	public static Button createButton(String text, ClickBehavior click) {
		Button button = new Button(text);
		button.setClickBehavior(click);
		return button;
	}
	
	public static Button createNavigationButton(String text, Menu prev, Menu next) {
		return createButton(text, new SwitchMenu(prev, next));
	}
	
	public static Button createExitButton() {
		return createButton("Exit", new ExitProgram());
	}
	
	public static Button createLevelSelectButton(Menu prev, LevelSelectMenu level) {
		return createNavigationButton("Level Select", prev, level);
	}
	
	public static Button createOptionsButton(Menu prev, OptionsMenu options) {
		return createNavigationButton("Options", prev, options);
	}
	
	public static Button createBackButton(Menu current, Menu prev) {
		return createNavigationButton("Back", current, prev);
	}
	
	public static Button createPlayLevelButton(Level level, Menu levelSelect) {
		return createButton(level.getName(), new PlayLevel(level, levelSelect));
		
	}

}

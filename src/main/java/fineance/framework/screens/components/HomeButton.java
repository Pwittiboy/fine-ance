package fineance.framework.screens.components;

import fineance.framework.ScreensController;
import fineance.framework.ScreensFramework;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class HomeButton {
	
	public static void init(Label label, ScreensController myController) {
		label.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				myController.setScreen(ScreensFramework.HOME_SCREEN_ID);
			}
			
		});
	}

}

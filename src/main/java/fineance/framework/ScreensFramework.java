package fineance.framework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScreensFramework extends Application {

	// Database credentials
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	
	// Screen IDs
	public static String LOGIN_SCREEN_ID = "loginScreenId";
	public static String HOME_SCREEN_ID = "homeScreenId";
	public static String ACCOUNTS_SCREEN_ID = "accountsScreenId";
	public static String IMPORTING_SCREEN_ID = "importingScreenId";
	public static String REPORTING_SCREEN_ID = "reportingScreenId";

	// Screen file names
	public static String LOGIN_SCREEN_FILE = "Login.fxml";
	public static String HOME_SCREEN_FILE = "Home.fxml";
	public static String ACCOUNTS_SCREEN_FILE = "Accounts.fxml";
	public static String IMPORTING_SCREEN_FILE = "Importing.fxml";
	public static String REPORTING_SCREEN_FILE = "Reporting.fxml";
	
	private void loadScreens(ScreensController mainContainer) {
		mainContainer.loadScreen(ScreensFramework.LOGIN_SCREEN_ID, ScreensFramework.LOGIN_SCREEN_FILE);
		mainContainer.loadScreen(ScreensFramework.HOME_SCREEN_ID, ScreensFramework.HOME_SCREEN_FILE);
		mainContainer.loadScreen(ScreensFramework.ACCOUNTS_SCREEN_ID, ScreensFramework.ACCOUNTS_SCREEN_FILE);
		mainContainer.loadScreen(ScreensFramework.IMPORTING_SCREEN_ID, ScreensFramework.IMPORTING_SCREEN_FILE);
		mainContainer.loadScreen(ScreensFramework.REPORTING_SCREEN_ID, ScreensFramework.REPORTING_SCREEN_FILE);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("FineAnce");
		primaryStage.getIcons().add(new Image("/img/Dark_Horse_Squadron.png"));
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		ScreensController mainContainer = new ScreensController();
		loadScreens(mainContainer);
		
		mainContainer.setScreen(ScreensFramework.LOGIN_SCREEN_ID);
		
		Group root = new Group();
		root.setId("root");
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root, 1000, 680);
//		scene.getStylesheets().add("/application.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		try {
			databaseCredentials(args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		launch(args);
	}

	private static void databaseCredentials(String[] args) throws Exception {
		
		if (args.length < 4) throw new Exception("Missing args for username and password");
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("dbusername")) DB_USERNAME = args[i+1];
			if (args[i].equals("dbpassword")) DB_PASSWORD = args[i+1];
		}
	}

}

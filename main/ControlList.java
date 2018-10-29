package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * Add and remove Elements on a List, sort it
 * has a expandable Menu, created with adding a child-Pane and removing it by button
 * (tricky feature)
 * 
 * Mark: once you've changed the Insets-Property, it is changed for all Buttons,
 * so you have to hand over each Button its "normal" Insets, as follows:
 * button1.setPadding(new Insets(4));
 * 
 * Buttons by Mark James: http://www.famfamfam.com/lab/icons/silk/
 * 
 * @author Frederik Weitz
 *
 */
public class ControlList extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Layout Container
		HBox root = new HBox();
		
		// ListView
		ListView<String> animalList = new ListView<String>();
		ObservableList<String> obsAnimalList = FXCollections.observableArrayList();
		animalList.setItems(obsAnimalList);
		animalList.setMinWidth(200);
		
		// PopOutPane
		HBox popOutButtonWindow = new HBox();
		Button popOutButton = new Button();
		popOutButton.setGraphic(new ImageView(new Image("/listImages/play_green.png")));
		popOutButton.setPadding(Insets.EMPTY);
		popOutButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// ********** ButtonPane ***********
		// Main Container for Buttons
		GridPane buttonPane = createGridPane(obsAnimalList);
		
		// fill List
		obsAnimalList.addAll("tiger", "bear", "ant", "lion");
		
		// Listeners and Bindings
		popOutButton.setOnAction(new EventHandler<ActionEvent>() {
			
			boolean isOn = false;
			
			@Override
			public void handle(ActionEvent event) {
				if(isOn) {
					popOutButtonWindow.getChildren().remove(buttonPane);
					primaryStage.sizeToScene();
					popOutButton.setGraphic(new ImageView(new Image("/listImages/play_green.png")));
					isOn = false;
				} else {
					popOutButtonWindow.getChildren().add(buttonPane);
					primaryStage.sizeToScene();
					popOutButton.setGraphic(new ImageView(new Image("/listImages/reverse_green.png")));
					isOn = true;
				}
			}
		});
		

		// add Elements to Parents
		root.getChildren().addAll(animalList, popOutButtonWindow);
		popOutButtonWindow.getChildren().addAll(popOutButton);
		
		
		// Scene and Stage
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch();
	}
	
	
	/**
	 * creates GridPane for poppable Window-Element
	 * @param obsList
	 * @return GridPane
	 */
	private GridPane createGridPane(ObservableList<String> obsList) {
		// Layout Container
		GridPane buttonPane = new GridPane();
		buttonPane.setHgap(2);
		buttonPane.setVgap(2);
		buttonPane.setPadding(new Insets(2));
		
		// add Element to List
		TextField addElementText = new TextField();
		Button addElementButton = new Button("add");
		addElementButton.setPadding(new Insets(4));
		addElementButton.setMaxSize(400, 32);
		buttonPane.add(addElementText, 0, 0);
		buttonPane.add(addElementButton, 1, 0);
		GridPane.setFillWidth(addElementButton, true);
				
		// remove Element from List
		TextField removeElementText = new TextField();
		Button removeElementButton = new Button("remove");
		removeElementButton.setPadding(new Insets(4));
		buttonPane.add(removeElementText, 0, 1);
		buttonPane.add(removeElementButton, 1, 1);
				
		// sorting Button
		Button sortElementsButton = new Button("Sort all Elements");
		buttonPane.add(sortElementsButton, 0, 2);
		sortElementsButton.setPadding(new Insets(4));
		sortElementsButton.setMaxSize(2000, 32);
		GridPane.setColumnSpan(sortElementsButton, 2);
		GridPane.setFillWidth(sortElementsButton, true);
		
		// add Listeners
		addElementButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String tempS = addElementText.getText();
				if (tempS!="" && !obsList.contains(tempS)) {
					obsList.add(tempS);
					addElementText.clear();
				}
			}
		});
		
		removeElementButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String tempS = removeElementText.getText();
				if (tempS!="") {
					obsList.remove(tempS);
					addElementText.clear();
				}	
			}
		});
		
		sortElementsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				obsList.sort((a,b) -> a.compareTo(b));	
			}
		});
		
		return buttonPane;
	}

}

package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Standalone:
 * Moving and Coloring a Square on a Pane with Scrollbars
 * and a timeline two circles moving
 * 
 * @author frede
 *
 */
public class ScrollImage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Layout Container
		VBox root = new VBox();
		VBox scrolls = new VBox();
		Pane showPane = new Pane();
		
		root.getChildren().addAll(scrolls, showPane);
		
		
		// ScrollBars
		Label moveHorizonalLabel = new Label("Move Horizontal");
		ScrollBar moveHorizontal = new ScrollBar();
		Label moveVerticalLabel = new Label("Move Vertical");
		ScrollBar moveVertical = new ScrollBar(); 
		Label colorChangeLabel = new Label("Change Color");
		ScrollBar changeColor = new ScrollBar();
				
		scrolls.getChildren().addAll(moveHorizonalLabel, moveHorizontal,
				moveVerticalLabel, moveVertical,
				colorChangeLabel, changeColor);
		
		// Defining showPane
		showPane.setPrefHeight(440);
		showPane.setPrefWidth(440);
		
		Rectangle moveIt = new Rectangle();
		moveIt.setWidth(40);
		moveIt.setHeight(40);
		moveIt.setFill(new Color(0,0,1,1));
		showPane.getChildren().add(moveIt);
		
		Circle circle1 = new Circle();
		circle1.setCenterX(200);
		circle1.setCenterY(150);
		circle1.setRadius(20);
		circle1.setFill(Color.TAN);
		circle1.setStrokeWidth(2);
		circle1.setStroke(Color.CORNFLOWERBLUE);
		showPane.getChildren().add(circle1);
		
		Circle circle2 = new Circle();
		circle2.setCenterX(160);
		circle2.setCenterY(150);
		circle2.setRadius(15);
		circle2.setFill(Color.FIREBRICK);
		showPane.getChildren().add(circle2);
		
		// Property and Listener Management
		moveIt.translateXProperty().bind(moveHorizontal.valueProperty().multiply(4));
		moveIt.translateYProperty().bind(moveVertical.valueProperty().multiply(3));
		
		// Change Color
		changeColor.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				moveIt.setFill(new Color(0,newValue.doubleValue()/changeColor.getMax(),1,1));
			}
		});
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(20);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new KeyValue(circle1.translateXProperty(), 240)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new KeyValue(circle1.radiusProperty(), 200)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), new KeyValue(circle2.translateXProperty(), 160)));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000), new KeyValue(circle2.radiusProperty(), 3)));
		timeline.play();
		
		// Scene and Stage
		Scene scene = new Scene(root,440,93+340);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}

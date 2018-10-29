package shapetest;

import cloverbutton.CloverButton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class ShapeTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Layout Container
		Pane root = new Pane();
		
		// Scene
		Scene scene = new Scene(root,400,400);

		// Shape and Button?
		
		Button testButton = new Button();
		testButton.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		testButton.setPrefSize(50,50);
		
		Path buttonPath = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(40);
		moveTo.setY(40);
		
		ArcTo arc1 = new ArcTo();
		arc1.setX(140);
		arc1.setY(40);
		arc1.setRadiusX(40);
		arc1.setRadiusY(40);
		arc1.setSweepFlag(true);
		
		ArcTo arc2 = new ArcTo();
		arc2.setX(90);
		arc2.setY(110);
		arc2.setRadiusX(40);
		arc2.setRadiusY(40);
		arc2.setSweepFlag(true);
		
		ArcTo arc3 = new ArcTo();
		arc3.setX(40);
		arc3.setY(40);
		arc3.setRadiusX(40);
		arc3.setRadiusY(40);
		arc3.setSweepFlag(true);
		
		buttonPath.getElements().addAll(moveTo, arc1, arc2, arc3);
		buttonPath.setFill(Color.AQUAMARINE);
		buttonPath.setStroke(Color.TRANSPARENT);
		
		// EventHandlers for testButton
		testButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button)event.getSource()).setBackground(new Background(
						new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		
		testButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button)event.getSource()).setBackground(new Background(
						new BackgroundFill(Color.CHARTREUSE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		
		testButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button)event.getSource()).setBackground(new Background(
						new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		});
		
		
		// EventHandler for buttonPath
		buttonPath.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Path)event.getSource()).setFill(Color.RED);
			}
		});
		
		buttonPath.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Path)event.getSource()).setFill(Color.CHARTREUSE);
			}
		});
		
		buttonPath.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Path)event.getSource()).setFill(Color.BLUE);
			}
		});
		
		// zweiter Pfad
		Path p1 = firstPath();
		p1.setTranslateX(100);
		p1.setTranslateY(100);
		p1.setFill(Color.CADETBLUE);
		
		CloverButton cbut = new CloverButton();
		cbut.setButtonColor(Color.RED);
		cbut.setTranslateX(150);
		cbut.setTranslateY(150);
		
		cbut.setActArc(15);
		cbut.setMaxArc(16.5);
		cbut.setMinArc(15);
		cbut.setStepSize(0.25);
		cbut.setStepDuration(0.1);
		cbut.setHoverColor(Color.CORNFLOWERBLUE);
		cbut.setClickedColor(Color.DARKORCHID);
		cbut.start();
		
		//testButton.setShape(buttonPath);
		root.getChildren().addAll(buttonPath, p1, cbut.getPath());
		
		// Stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Path firstPath() {
		Path p = new Path();
		
		MoveTo moveTo = new MoveTo();
		moveTo.setX(0);
		moveTo.setY(0);
		
		ArcTo arc1 = new ArcTo();
		arc1.setX(20);
		arc1.setY(0);
		arc1.setRadiusX(10);
		arc1.setRadiusY(10);
		arc1.setSweepFlag(true);
		
		ArcTo arc2 = new ArcTo();
		arc2.setX(20);
		arc2.setY(20);
		arc2.setRadiusX(10);
		arc2.setRadiusY(10);
		arc2.setSweepFlag(true);
		
		ArcTo arc3 = new ArcTo();
		arc3.setX(0);
		arc3.setY(20);
		arc3.setRadiusX(10);
		arc3.setRadiusY(10);
		arc3.setSweepFlag(true);
		
		ArcTo arc4 = new ArcTo();
		arc4.setX(0);
		arc4.setY(0);
		arc4.setRadiusX(10);
		arc4.setRadiusY(10);
		arc4.setSweepFlag(true);
		
		p.getElements().addAll(moveTo, arc1, arc2, arc3, arc4);
		
		return p;
	}
	

}

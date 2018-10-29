package cloverbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class CloverButton {

	// graphic elements
	private Path path;
	private MoveTo moveTo;
	private ArcTo arc1;
	private ArcTo arc2;
	private ArcTo arc3;
	private ArcTo arc4;
	
	// transition conditions
	private double actArc = 10;
	private double minArc = 10;
	private double maxArc = 10;
	
	private double stepSize = 1;
	
	private double stepDuration = 0.1;
	private boolean stepDirection = true;
	
	Timeline timeline;
	
	// MouseOnColors
	
	private Color buttonColor = Color.LIGHTSKYBLUE;
	private Color hoverColor = null;
	private Color clickedColor = null;
	
	/**
	 * Constructor for the "Shape"-Button
	 */
	public CloverButton() {
		constructPath();
	}
	
	
	// instatiate Timeline
	private void initTimeline() {
		timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(stepDuration),
				ev -> {
					if (stepDirection) {
						increasePath();
						if (arc1.getRadiusX() > maxArc) {
							stepDirection = false;
						}
					} else
						decreasePath();
						if (arc1.getRadiusX() < minArc) {
							stepDirection = true;
						}
				}));
		timeline.setCycleCount(Animation.INDEFINITE);
	}
	
	
	/**
	 * start Timeline
	 */
	public void start() {
		initTimeline();
		timeline.play();
	}
	
	/** 
	 * pause Timeline
	 */
	public void pause() {
		timeline.pause();
	}
	
	/**
	 * getOnMouseEntered
	 */
	public void setOnMouseEntered(EventHandler<MouseEvent> newHandler) {
		path.addEventHandler(MouseEvent.MOUSE_ENTERED, newHandler);
	}
	
	/**
	 * getOnMouseExited
	 */
	public void setOnMouseExited(EventHandler<MouseEvent> newHandler) {
		path.addEventHandler(MouseEvent.MOUSE_EXITED, newHandler);
	}
	
	/**
	 * getOnMousePressed
	 */
	public void setOnMousePressed(EventHandler<MouseEvent> newHandler) {
		path.addEventHandler(MouseEvent.MOUSE_PRESSED, newHandler);
	}
	
	/**
	 * increases the size of the Button by stepSize
	 */
	private void increasePath() {
		moveTo.setX(moveTo.getX()-stepSize);
		moveTo.setY(moveTo.getY()-stepSize);
		
		arc1.setX(arc1.getX()+stepSize);
		arc1.setY(arc1.getY()-stepSize);
		arc1.setRadiusX(arc1.getRadiusX()+stepSize);
		arc1.setRadiusY(arc1.getRadiusY()+stepSize);
		
		arc2.setX(arc2.getX()+stepSize);
		arc2.setY(arc2.getY()+stepSize);
		arc2.setRadiusX(arc2.getRadiusX()+stepSize);
		arc2.setRadiusY(arc2.getRadiusY()+stepSize);
		
		arc3.setX(arc3.getX()-stepSize);
		arc3.setY(arc3.getY()+stepSize);
		arc3.setRadiusX(arc3.getRadiusX()+stepSize);
		arc3.setRadiusY(arc3.getRadiusY()+stepSize);
		
		arc4.setX(arc4.getX()-stepSize);
		arc4.setY(arc4.getY()-stepSize);
		arc4.setRadiusX(arc4.getRadiusX()+stepSize);
		arc4.setRadiusY(arc4.getRadiusY()+stepSize);
	}
	
	/**
	 * decreases the size of the button by stepSize
	 */
	private void decreasePath() {
		moveTo.setX(moveTo.getX()+stepSize);
		moveTo.setY(moveTo.getY()+stepSize);
		
		arc1.setX(arc1.getX()-stepSize);
		arc1.setY(arc1.getY()+stepSize);
		arc1.setRadiusX(arc1.getRadiusX()-stepSize);
		arc1.setRadiusY(arc1.getRadiusY()-stepSize);
		
		arc2.setX(arc2.getX()-stepSize);
		arc2.setY(arc2.getY()-stepSize);
		arc2.setRadiusX(arc2.getRadiusX()-stepSize);
		arc2.setRadiusY(arc2.getRadiusY()-stepSize);
		
		arc3.setX(arc3.getX()+stepSize);
		arc3.setY(arc3.getY()-stepSize);
		arc3.setRadiusX(arc3.getRadiusX()-stepSize);
		arc3.setRadiusY(arc3.getRadiusY()-stepSize);
		
		arc4.setX(arc4.getX()+stepSize);
		arc4.setY(arc4.getY()+stepSize);
		arc4.setRadiusX(arc4.getRadiusX()-stepSize);
		arc4.setRadiusY(arc4.getRadiusY()-stepSize);
	}
	
	/**
	 * constructs the path of the Button
	 */
	private void constructPath() {
		if (path == null) {
			path = new Path();
		}
		
		moveTo = new MoveTo();
		moveTo.setX(0);
		moveTo.setY(0);
		
		arc1 = new ArcTo();
		arc1.setX(2*actArc);
		arc1.setY(0);
		arc1.setRadiusX(actArc);
		arc1.setRadiusY(10);
		arc1.setSweepFlag(true);
		
		arc2 = new ArcTo();
		arc2.setX(2*actArc);
		arc2.setY(20);
		arc2.setRadiusX(actArc);
		arc2.setRadiusY(actArc);
		arc2.setSweepFlag(true);
		
		arc3 = new ArcTo();
		arc3.setX(0);
		arc3.setY(2*actArc);
		arc3.setRadiusX(actArc);
		arc3.setRadiusY(actArc);
		arc3.setSweepFlag(true);
		
		arc4 = new ArcTo();
		arc4.setX(0);
		arc4.setY(0);
		arc4.setRadiusX(actArc);
		arc4.setRadiusY(actArc);
		arc4.setSweepFlag(true);
		
		path.getElements().addAll(moveTo, arc1, arc2, arc3, arc4);
	}
	
	// Getter for the path
	public Path getPath() {
		return path;
	}

	// Getters and Setters
	public double getActArc() {
		return actArc;
	}

	public void setActArc(double actArc) {
		if (actArc < 5) {
			actArc = 5;
		} else if (actArc > 30) {
			actArc = 30;
		}
		this.actArc = actArc;
	}
	
	public double getMinArc() {
		return minArc;
	}

	public void setMinArc(double minArc) {
		if (minArc < 5) {
			minArc = 5;
		} else if (minArc >= actArc) {
			minArc = actArc;
		}
		this.minArc = minArc;
	}

	public double getMaxArc() {
		return maxArc;
	}

	public void setMaxArc(double maxArc) {
		if (maxArc > 60) {
			maxArc = 60;
		} else if (maxArc <= actArc) {
			maxArc = actArc;
		}
		this.maxArc = maxArc;
	}

	public double getStepSize() {
		return stepSize;
	}

	public void setStepSize(double stepSize) {
		if (stepSize > 2) {
			stepSize = 2;
		}
		this.stepSize = stepSize;
	}

	public double getStepDuration() {
		return stepDuration;
	}

	public void setStepDuration(double stepDuration) {
		this.stepDuration = stepDuration;
	}

	public boolean isStepDirection() {
		return stepDirection;
	}

	public void setStepDirection(boolean stepDirection) {
		this.stepDirection = stepDirection;
	}

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		path.setFill(buttonColor);
		this.buttonColor = buttonColor;
	}

	public Color getHoverColor() {
		return hoverColor;
	}

	public void setHoverColor(Color hoverColor) {
		if (hoverColor != null) {
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					path.setFill(hoverColor);	
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					path.setFill(buttonColor);
				}
			});
		}
		this.hoverColor = hoverColor;
	}

	public Color getClickedColor() {
		return clickedColor;
	}

	public void setClickedColor(Color clickedColor) {
		if (clickedColor != null) {
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					path.setFill(clickedColor);
				}
			});
		}
		this.clickedColor = clickedColor;
	}
	
	public double getTranslateX() {
		return path.getTranslateX();
	}
	
	public void setTranslateX(double x) {
		path.setTranslateX(x);
	}
	
	public double getTranslateY() {
		return path.getTranslateY();
	}
	
	public void setTranslateY(double y) {
		path.setTranslateY(y);
	}
	
}

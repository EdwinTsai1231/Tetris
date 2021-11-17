import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {
	
	public static void animationCombo() {
		ImageView imageView = new ImageView (getComboImage(Controller.combo));
		imageView.setX(0);
		imageView.setY(30);
		KeyValue kv1 = new KeyValue(imageView.translateYProperty(),0);
		KeyValue kv1OP = new KeyValue(imageView.opacityProperty(),0);
		KeyValue kv1SX = new KeyValue(imageView.scaleXProperty(),0);
		KeyValue kv1SY = new KeyValue(imageView.scaleYProperty(),0);
		KeyFrame kf1 = new KeyFrame(Duration.seconds(0),kv1,kv1OP , kv1SX ,kv1SY);
		KeyValue kv2 = new KeyValue(imageView.translateYProperty(),50);
		KeyValue kv2OP = new KeyValue(imageView.opacityProperty(),1);
		KeyValue kv2SX = new KeyValue(imageView.scaleXProperty(),1);
		KeyValue kv2SY = new KeyValue(imageView.scaleYProperty(),1);
		KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5),kv2,kv2OP , kv2SX ,kv2SY);
		KeyValue kvstop= new KeyValue(imageView.scaleYProperty(),2);
		KeyFrame kf3 = new KeyFrame(Duration.seconds(1),kvstop);
		KeyValue kv4OP = new KeyValue(imageView.opacityProperty(),0);
		KeyFrame kf4 = new KeyFrame(Duration.seconds(2.5),kv4OP);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(kf1 , kf2 , kf3 , kf4);
		timeline.play();
		Main.pane.getChildren().add(imageView);
	}
	
	public static Image getComboImage(int combo) {
		switch(combo) {
		case 2 :
			return new Image("picture/combo2.png");
		case 3 :
			return new Image("picture/combo3.png");
		case 4 :
			return new Image("picture/combo4.png");
		case 5 :
			return new Image("picture/combo5.png");
		case 6 :
			return new Image("picture/combo6.png");
		case 7 :
			return new Image("picture/combo7.png");
		case 8 :
			return new Image("picture/combo8.png");
		case 9 :
			return new Image("picture/combo9.png");
		case 10 :
			return new Image("picture/combo10.png");
		default :
			return new Image("picture/combo10+.png");
		}
	}
	
	public static void animationTetris() {
		Image image = new Image("picture/tetris.png");
		ImageView imageView = new ImageView(image);
		imageView.setX(-30);
		imageView.setY(30);
		KeyValue kv1 = new KeyValue(imageView.rotateProperty(),0);
		KeyValue kv1OP = new KeyValue(imageView.opacityProperty(),0);
		KeyValue kv1SX = new KeyValue(imageView.scaleXProperty(),0);
		KeyValue kv1SY = new KeyValue(imageView.scaleYProperty(),0);
		KeyFrame kf1 = new KeyFrame(Duration.seconds(0),kv1,kv1OP , kv1SX ,kv1SY);
		KeyValue kv2 = new KeyValue(imageView.rotateProperty(),360);
		KeyValue kv2OP = new KeyValue(imageView.opacityProperty(),1);
		KeyValue kv2SX = new KeyValue(imageView.scaleXProperty(),1);
		KeyValue kv2SY = new KeyValue(imageView.scaleYProperty(),1);
		KeyFrame kf2 = new KeyFrame(Duration.seconds(1),kv2,kv2OP , kv2SX ,kv2SY);
		KeyValue kvstop= new KeyValue(imageView.scaleYProperty(),2);
		KeyFrame kf3 = new KeyFrame(Duration.seconds(2),kvstop);
		KeyValue kv4OP = new KeyValue(imageView.opacityProperty(),0);
		KeyFrame kf4 = new KeyFrame(Duration.seconds(3),kv4OP);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(kf1 , kf2 , kf3 , kf4);
		timeline.play();
		Main.pane.getChildren().add(imageView);
	}

	public static void animationLevel() {
		ImageView imageView = new ImageView (getLevel(Main.taskNo));
		imageView.setX(120);
		imageView.setY(50);
		KeyValue kv1OP = new KeyValue(imageView.opacityProperty(),0);
		KeyValue kv1SX = new KeyValue(imageView.scaleXProperty(),0);
		KeyValue kv1SY = new KeyValue(imageView.scaleYProperty(),0);
		KeyFrame kf1 = new KeyFrame(Duration.seconds(0),kv1OP , kv1SX ,kv1SY);
		KeyValue kv2OP = new KeyValue(imageView.opacityProperty(),1);
		KeyValue kv2SX = new KeyValue(imageView.scaleXProperty(),0.5);
		KeyValue kv2SY = new KeyValue(imageView.scaleYProperty(),0.5);
		KeyFrame kf2 = new KeyFrame(Duration.seconds(1.5),kv2OP , kv2SX ,kv2SY);
		KeyValue kvstop= new KeyValue(imageView.scaleYProperty(),0.5);
		KeyFrame kf3 = new KeyFrame(Duration.seconds(3),kvstop);
		KeyValue kv4OP = new KeyValue(imageView.opacityProperty(),0);
		KeyFrame kf4 = new KeyFrame(Duration.seconds(3.5),kv4OP);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(kf1 , kf2 ,kf3 , kf4);
		timeline.play();
		Main.pane.getChildren().add(imageView);
	}
	
	private static Image getLevel(int level) {
		switch(level) {
		case 5 :
			return new Image("picture/level5.png");
		case 10 :
			return new Image("picture/level10.png");
		
		}
		return null;
	}
}

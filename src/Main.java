
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.* ;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	//Variables
	public static final int MOVE = 25;
	public static final int SIZE = 25;
	public static final int XMAX = 12*SIZE;
	public static final int YMAX = 25*SIZE;
	public static final int[][] FIELD = new int[XMAX/SIZE][YMAX/SIZE];
	public static Pane pane = new Pane();
	public static Form object; // contain current tetrimino
	static boolean start = true; // control ON and OFF
	public static int score = 0;
	public static int linesNo = 0;
	private static int period = 300 ;
	public static Form nextObject;// contain next tetrimino
	public static Form thirdObject ;
	public static Form fourthObject ;
	public static Form fifthObject;
	public static Form tempObject = null;
	private static int over = 0 ;
	public static int taskNo = 1;
	private static int threshold = 5;
	private static Scene scene= new Scene(pane,XMAX+150,YMAX);
	TimerTask task2 ;
	TimerTask task ;

	public static void main(String[] args) { launch(args); }

	@Override
	public void start(Stage stage) throws Exception {

		for(int a[]:FIELD)
			Arrays.fill(a,0); // 初始化 把FIELD裡面的值都設為0
		
		//介面
		Line lineRight = new Line(XMAX, 0, XMAX, YMAX);
		lineRight.setStroke(Color.WHITE);
		Line lineLeft  = new Line(0, 0, 0, YMAX);
		lineLeft.setStroke(Color.WHITE);
		Text scoreText = new Text("Score:");
		scoreText.setX(XMAX + 5);
		scoreText.setY(30);
		scoreText.setFill(Color.WHITE);
		scoreText.setStyle("-fx-font: 20 arial;");
		Text nextText = new Text ("Next:") ;
		nextText.setX(XMAX+5);
		nextText.setY(90); 
		nextText.setStyle("-fx-font: 20 arial;");
		nextText.setFill(Color.DARKGRAY);
		Text lineText = new Text("Lines:");
		lineText.setX(XMAX + 5);
		lineText.setY(60);
		lineText.setFill(Color.GREEN);
		lineText.setStyle("-fx-font: 20 arial;");
		Text hint = new Text("press C to switch");
		hint .setX(XMAX+20);
		hint.setY(490);
		hint.setFill(Color.WHITE);
		hint.setStyle("-fx-font: 15 arial;");
		Text level = new Text("Level:");
		level.setX(XMAX-60);
		level.setY(25);
		level.setFill(Color.YELLOW);
		level.setStyle("-fx-font: 15 arial;");
		pane.getChildren().addAll(lineRight , lineLeft,scoreText,lineText , nextText,hint , level);
		
		//生成第一組
		Form firstObject = Controller.makeTetri();
		pane.getChildren().addAll(firstObject.a , firstObject.b , firstObject.c , firstObject.d);
		moveByKeyPress(firstObject);
		object = firstObject ;
		nextObject = Controller.makeTetri();
		thirdObject = Controller.makeTetri();
		fourthObject = Controller.makeTetri();
		fifthObject = Controller.makeTetri();
		
		//投影
		Controller.projectForm =new Form(Controller.Pa,Controller.Pb,Controller.Pc,Controller.Pd);
		Controller.Projection(object);
		
		//預覽下一個
		ImageView iv2 = new ImageView(getImage(nextObject));
		iv2.setX(XMAX+50);
		iv2.setY(120);
		ImageView iv3 = new ImageView(getImage(thirdObject));
		iv3.setX(XMAX+50);
		iv3.setY(210);
		ImageView iv4 = new ImageView(getImage(fourthObject));
		iv4.setX(XMAX+50);
		iv4.setY(300);
		ImageView iv5 = new ImageView(getImage(fifthObject));
		iv5.setX(XMAX+50);
		iv5.setY(390);
		Image image = new Image("picture/unknown.png" , 65 , 65, true, false);
		ImageView ivtemp = new ImageView(image) ; 
		ivtemp.setX(XMAX+50);
		ivtemp.setY(510);
		pane.getChildren().addAll(iv2 , iv3 , iv4 , iv5 , ivtemp);
		
		//scene and stage stuff
		scene.setFill(Color.BLACK);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		//控制掉落
		Timer[] falling = new Timer[10] ;
		for (int i = 0 ; i<falling.length ; i++) 
			falling[i]=new Timer();
		
		TimerTask[] task = new TimerTask[10];
		
		for( int spawnTask=0 ; spawnTask <task.length ; spawnTask++) {
			//System.out.println(spawnTask);
			task[spawnTask] = new TimerTask(){ // 執行thread部分
				@Override
				public void run() {
					Platform.runLater(new Runnable() { //JavaFX thread
						@Override
						public void run() {
							if(checkOver())
								over++;
							else
								over = 0 ;
							
							if(over==2) {
								Text gameOver = new Text("GameOver");
								gameOver.setFill(Color.RED);
								gameOver.setX(60);
								gameOver.setY(YMAX/2);
								gameOver.setStyle("-fx-font: 70 arial;");
								pane.getChildren().add(gameOver);
								start=false;
								}
							
							if(over==20)
								System.exit(0);
							
							if(start) {
								Controller.moveTDown(object);
								scoreText.setText("Score:"+Integer.toString(score));
								lineText.setText("Lines:"+Integer.toString(linesNo));
								level.setText("Level:"+Integer.toString(taskNo));
								iv2.setImage(getImage(nextObject));
								iv3.setImage(getImage(thirdObject));
								iv4.setImage(getImage(fourthObject));
								iv5.setImage(getImage(fifthObject));
								if(tempObject!=null)
									ivtemp.setImage(getImage(tempObject));
								
								Controller.RemoveProjection();
								Controller.Projection(object);
							
								if(linesNo > threshold*taskNo && taskNo<10 ) {
									falling[taskNo-1].cancel();
									period-=25 ;
									taskNo++;
									if(taskNo==5||taskNo==10)
										Animation.animationLevel();
									falling[taskNo-1].schedule(task[taskNo-1], 0 , period);
								}
							}	
						}
					});
				} 
			};
		}
		falling[0].schedule(task[0], 0 , period);
	}
	
	public static void moveByKeyPress(Form form) { // 鍵盤控制
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(start) {
					switch(event.getCode()) {
						case RIGHT:
							Controller.moveTRight(form);
							break;
						case LEFT:
							Controller.moveTLeft(form);
							break;
						case UP:
							Controller.moveTrun(form);
							break;
						case DOWN:
							Controller.moveTDown(form);
							break;
						case SPACE:
								Controller.pressSpace(form);
							break;
						case C :
							Controller.swith(form);
					default:
						break;
				}
			}
			}
		});	
	
	}
	
	private Image getImage(Form form) { // 預覽下一個 tetrimino
		if(form.getName().equals("j"))
			return new Image("picture/j.png");
		else if (form.getName().equals("l"))
			return new Image("picture/l.png");
		else if (form.getName().equals("o"))
			return new Image("picture/o.png");
		else if (form.getName().equals("s"))
			return new Image("picture/s.png");
		else if (form.getName().equals("z"))
			return new Image("picture/z.png");
		else if (form.getName().equals("t"))
			return new Image("picture/t.png");
		else if (form.getName().equals("i"))
			return new Image("picture/i.png");
		else 
			return new Image("picture/unknown.png");
	}
	
	private static boolean checkOver() {
		for(int i = 0 ; i < FIELD.length ; i++) {
			if(FIELD[i][0]==1)
				return true;
		}
		return false;
		
	}
}
	



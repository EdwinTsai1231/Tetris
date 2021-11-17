import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Controller{
	//Variables
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static final int XMAX = Main.XMAX;
	public static final int YMAX = Main.YMAX;
	public static final int[][] FIELD = Main.FIELD;
	
	public static Form projectForm; 
	static Rectangle Pa = new Rectangle (SIZE-1 , SIZE-1);
	static Rectangle Pb = new Rectangle (SIZE-1 , SIZE-1);
	static Rectangle Pc = new Rectangle (SIZE-1 , SIZE-1);
	static Rectangle Pd = new Rectangle (SIZE-1 , SIZE-1);
	public static int combo = 0 ;
	public static int scoreLines = 0 ;
	

	public static void moveTRight(Form form) { // 先判斷移動後在不在界線
		if(form.a.getX()+SIZE<=XMAX-SIZE && form.b.getX()+SIZE<=XMAX-SIZE && form.c.getX()+SIZE<=XMAX-SIZE && form.d.getX()+SIZE<=XMAX-SIZE) {
			int movea = FIELD[(int)(form.a.getX()/SIZE)+1][(int)(form.a.getY()/SIZE)];
			int moveb = FIELD[(int)(form.b.getX()/SIZE)+1][(int)(form.b.getY()/SIZE)];
			int movec = FIELD[(int)(form.c.getX()/SIZE)+1][(int)(form.c.getY()/SIZE)];
			int moved = FIELD[(int)(form.d.getX()/SIZE)+1][(int)(form.d.getY()/SIZE)];
			if(movea==0 && moveb == 0 && movec == 0 && moved == 0) { //判斷所欲移動的空格是不是空的
				form.a.setX(form.a.getX()+MOVE);
				form.b.setX(form.b.getX()+MOVE);
				form.c.setX(form.c.getX()+MOVE);
				form.d.setX(form.d.getX()+MOVE);
				
				RemoveProjection();
				Projection(form);
			}
			 
		}	
	}
	
	public static void moveTLeft(Form form) {
		if(form.a.getX()-SIZE>=0 && form.b.getX()-SIZE>=0 && form.c.getX()-SIZE>=0 && form.d.getX()-SIZE>=0) {
			int movea = FIELD[(int)(form.a.getX()/SIZE)-1][(int)(form.a.getY()/SIZE)];
			int moveb = FIELD[(int)(form.b.getX()/SIZE)-1][(int)(form.b.getY()/SIZE)];
			int movec = FIELD[(int)(form.c.getX()/SIZE)-1][(int)(form.c.getY()/SIZE)];
			int moved = FIELD[(int)(form.d.getX()/SIZE)-1][(int)(form.d.getY()/SIZE)];
			if(movea==0 && moveb == 0 && movec == 0 && moved == 0) {
				form.a.setX(form.a.getX()-MOVE);
				form.b.setX(form.b.getX()-MOVE);
				form.c.setX(form.c.getX()-MOVE);
				form.d.setX(form.d.getX()-MOVE);
				
				RemoveProjection();
				Projection(form);
			}
		}	
	}
	
	public static Form makeTetri() { //產生tetrimino 並設置4個rectangle的位置 type 1 
		String name ;
		Rectangle a = new Rectangle (SIZE-1 , SIZE-1);
		Rectangle b = new Rectangle (SIZE-1 , SIZE-1);
		Rectangle c = new Rectangle (SIZE-1 , SIZE-1);
		Rectangle d = new Rectangle (SIZE-1 , SIZE-1);
		int ran = ((int)(Math.random()*100));
		if(ran<15) {
			name = "j";
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
		}
		else if(ran<30) {
			name = "l";
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2 + SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
		}
		else if(ran<45) {
			name = "o";
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
		}
		else if(ran<60) {
			name = "s";
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
		}
		else if(ran<75) {
			name = "z";
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
		}
		else if(ran<90) {
			name = "t";
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
		}
		else {
			name = "i";
			a.setX(XMAX / 2 - SIZE - SIZE);
			b.setX(XMAX / 2 - SIZE);
			c.setX(XMAX / 2);
			d.setX(XMAX / 2 + SIZE);
		}
		return new Form (a,b,c,d,name);		
	}
	
	public static void moveTDown(Form form) { //整個tetrimino往下移
		//先判斷到達與否
		if(form.a.getY()==YMAX-SIZE ||form.b.getY()==YMAX-SIZE ||form.c.getY()==YMAX-SIZE ||form.d.getY()==YMAX-SIZE ||
			moveTa(form) || moveTb(form) || moveTc(form) || moveTd(form) ) {
			FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)] = 1;
			FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)] = 1;
			FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)] = 1;
			FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)] = 1;
			removeRows(Main.pane); //清除排成一列的rectangle
			
			/*for(int j = 0 ; j <FIELD[0].length ; j++) {
				for(int k = 0 ; k<FIELD.length;k++)
					System.out.print(FIELD[k][j]+" ");
				System.out.println("");
			}
			System.out.println();*/
			
			
			Form a =  Main.nextObject ;
			Main.nextObject = Main.thirdObject;
			Main.thirdObject = Main.fourthObject;
			Main.fourthObject = Main.fifthObject;
			Main.fifthObject = makeTetri();
			Main.object = a ;
			Main.pane.getChildren().addAll(Main.object.a , Main.object.b , Main.object.c , Main.object.d);
			
			Main.moveByKeyPress(Main.object);
			
			
		}
		if(form.a.getY()+MOVE<YMAX && form.b.getY()+MOVE<YMAX && form.c.getY()+MOVE<YMAX && form.d.getY()+MOVE<YMAX ) {
			int movea = FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)+1];
			int moveb = FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)+1];
			int movec = FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)+1];
			int moved = FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)+1];
			if(movea==0 && moveb==0 && movec==0 && moved==0) {//判斷下一格是不是empty
				form.a.setY(form.a.getY()+MOVE);
				form.b.setY(form.b.getY()+MOVE);
				form.c.setY(form.c.getY()+MOVE);
				form.d.setY(form.d.getY()+MOVE);
			}
			
		}
		
	}
	private static boolean moveTa(Form form) { //判斷rectangle a 的下一格是不是empty
		return (FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)+1]==1) ;
	}
	private static boolean moveTb(Form form) {
		return (FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)+1]==1) ;
	}
	private static boolean moveTc(Form form) {
		return (FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)+1]==1) ;
	}
	private static boolean moveTd(Form form) {
		return (FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)+1]==1) ;
	}
	
	public static void moveTrun(Form form) { // 旋轉(換type)
		int formType = form.formType;
		Rectangle a = form.a;
		Rectangle b = form.b;
		Rectangle c = form.c;
		Rectangle d = form.d;
		
		switch(form.getName()) {
			case"j":
				if(formType == 1 && valCh(a,2,0) && valCh(b,1,1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveRight(form.b);
					moveUp(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,0,-2) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,1)) {
					moveDown(form.a);
					moveDown(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,-2,0) && valCh(b,-1,-1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveLeft(form.b);
					moveDown(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,0,2) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,-1)) {
					moveUp(form.a);
					moveUp(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
			case "l":
				if( formType == 1 && valCh(a,0,-2) && valCh(b,-1,-1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveDown(form.a);
					moveDown(form.a);
					moveLeft(form.b);
					moveDown(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,-2,0) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,-1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,0,2) && valCh(b,1,1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveUp(form.a);
					moveUp(form.a);
					moveRight(form.b);
					moveUp(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,2,0) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
			case "o":
				break;
			case "s":
				if( formType == 1 && valCh(a,0,-2) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveDown(form.a);
					moveDown(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,0,2) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveUp(form.a);
					moveUp(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,0,-2) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveDown(form.a);
					moveDown(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,0,2) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveUp(form.a);
					moveUp(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
			case "z":
				if( formType == 1 && valCh(a,2,0) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,-2,0) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,2,0) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,-1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,-2,0) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
			case "t":
				if( formType == 1 && valCh(a,1,1) && valCh(b,0,0) && valCh(c,-1,1) && valCh(d,-1,-1)) {
					moveRight(form.a);
					moveUp(form.a);
					moveLeft(form.c);
					moveUp(form.c);
					moveLeft(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,1,-1) && valCh(b,0,0) && valCh(c,1,1) && valCh(d,-1,1)) {
					moveRight(form.a);
					moveDown(form.a);
					moveRight(form.c);
					moveUp(form.c);
					moveLeft(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,-1,-1) && valCh(b,0,0) && valCh(c,1,-1) && valCh(d,1,1)) {
					moveLeft(form.a);
					moveDown(form.a);
					moveRight(form.c);
					moveDown(form.c);
					moveRight(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,-1,1) && valCh(b,0,0) && valCh(c,-1,-1) && valCh(d,1,-1)) {
					moveLeft(form.a);
					moveUp(form.a);
					moveLeft(form.c);
					moveDown(form.c);
					moveRight(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
			case "i":
				if( formType == 1 && valCh(a,2,-2) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveDown(form.a);
					moveDown(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 2 && valCh(a,-2,2) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,-1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveUp(form.a);
					moveUp(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
				if( formType == 3 && valCh(a,2,-2) && valCh(b,1,-1) && valCh(c,0,0) && valCh(d,-1,1)) {
					moveRight(form.a);
					moveRight(form.a);
					moveDown(form.a);
					moveDown(form.a);
					moveRight(form.b);
					moveDown(form.b);
					moveLeft(form.d);
					moveUp(form.d);
					form.changeType();
					break;
				}
				if( formType == 4 && valCh(a,-2,2) && valCh(b,-1,1) && valCh(c,0,0) && valCh(d,1,-1)) {
					moveLeft(form.a);
					moveLeft(form.a);
					moveUp(form.a);
					moveUp(form.a);
					moveLeft(form.b);
					moveUp(form.b);
					moveRight(form.d);
					moveDown(form.d);
					form.changeType();
					break;
				}
		}
		RemoveProjection();
		Projection(form);
	}
	private static boolean valCh(Rectangle rect , int x , int y) { // 能不能轉
		boolean valx = false ;
		boolean valy = false ;
		if(x >= 0)
			valx = rect.getX() + x*MOVE <= XMAX - SIZE ;
		if(x < 0)
			valx = rect.getX() + x*MOVE >= 0 ;
		if(y >= 0)
			valy = rect.getY() - y*MOVE > 0 ;
		if(y < 0)
			valy = rect.getY() - y*MOVE < YMAX;
		return valx && valy && FIELD[(int)(rect.getX()/SIZE)+x][(int)(rect.getY()/SIZE)-y] == 0;
	}
	private static void moveUp(Rectangle rect){ //rectangle 往上
		if(rect.getY()-MOVE > 0)
			rect.setY(rect.getY()-MOVE);
	}
	private static void moveDown(Rectangle rect){ //rectangle 往下
		if(rect.getY()+MOVE < YMAX)
			rect.setY(rect.getY()+MOVE);
	}
	private static void moveRight(Rectangle rect){ //rectangle 往右
		if(rect.getX()+MOVE <= XMAX - SIZE)
			rect.setX(rect.getX() + MOVE);
	}
	private static void moveLeft(Rectangle rect){ //rectangle 往左
		if(rect.getX()-MOVE >= 0)
			rect.setX(rect.getX()-MOVE);
	}
	
	private static void removeRows(Pane pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Node> newRects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		int full = 0 ;
		//尋找哪一列是滿的
		for(int i = 0 ; i < FIELD[0].length ; i++) {
			for(int j = 0 ; j < FIELD.length ; j++) {
				if(FIELD[j][i]==1)
					full++;
			}
			if(full == FIELD.length) {
				lines.add(i);
				scoreLines++;
			}
			
			full = 0;
		}
		
		if(lines.size()==4)
			Animation.animationTetris();
		//若是有滿的
		if(lines.size()>0) {
			combo++;
			CountingScore.countScore();
			scoreLines = 0;
			if(combo>=2) 
				Animation.animationCombo();
			do {
				Main.linesNo++;
				
				for(Node node:pane.getChildren()) {
					if(node instanceof Rectangle) {
						rects.add(node);
					}
				}
				for(Node node : rects) {
					Rectangle a = (Rectangle) node ;
					if(a.getY() == lines.get(0)*SIZE) {
						FIELD[(int)a.getX()/SIZE][(int)a.getY()/SIZE] = 0 ;
						pane.getChildren().remove(a);
					}else {
						newRects.add(a);
					}
				}
				for(Node node : newRects) {
					Rectangle a = (Rectangle) node ;
					if(a.getY() < lines.get(0)*SIZE) {
						FIELD[(int)a.getX()/SIZE][(int)a.getY()/SIZE] = 0 ;
						a.setY(a.getY()+SIZE);
					}
				}
				lines.remove(0);
				newRects.clear();
				rects.clear();
				
				for(Node node : pane.getChildren()) {
					if(node instanceof Rectangle) {
						rects.add(node);
					}
				}
				for(Node node : rects) {
					Rectangle a = (Rectangle) node;
					FIELD[(int)a.getX()/SIZE][(int)a.getY()/SIZE]=1;
				}
				
				rects.clear();
				
			}while(lines.size()>0);
		}else {
			combo = 0;
		}
	}
	
	public static void pressSpace(Form form) {
		for(int i = 0 ; i <FIELD[0].length ; i++) {
			if(form.a.getY()+i*SIZE==YMAX-SIZE ||form.b.getY()+i*SIZE==YMAX-SIZE ||form.c.getY()+i*SIZE==YMAX-SIZE ||form.d.getY()+i*SIZE==YMAX-SIZE ||
					FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)+i+1]==1 || FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)+i+1]==1 ||
					FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)+i+1]==1 || FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)+i+1]==1 ) {
						FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)+i] = 1;
						FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)+i] = 1;
						FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)+i] = 1;
						FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)+i] = 1;
						form.a.setY(form.a.getY()+SIZE*i);
						form.b.setY(form.b.getY()+SIZE*i);
						form.c.setY(form.c.getY()+SIZE*i);
						form.d.setY(form.d.getY()+SIZE*i);
						removeRows(Main.pane); //清除排成一列的rectangle
					
						Form a =  Main.nextObject ;
						Main.nextObject = Main.thirdObject;
						Main.thirdObject = Main.fourthObject;
						Main.fourthObject = Main.fifthObject;
						Main.fifthObject = makeTetri();
						Main.object = a ;
						Main.pane.getChildren().addAll(Main.object.a , Main.object.b , Main.object.c , Main.object.d);
						Main.moveByKeyPress(Main.object);
						
						
						/*for(int j = 0 ; j <FIELD[0].length ; j++) {
							for(int k = 0 ; k<FIELD.length;k++)
								System.out.print(FIELD[k][j]+" ");
							System.out.println("");
						}
						System.out.println(); */
						
						break;
			}else 
				continue;
		
		}
	}
	
	public static void swith(Form form) { // switch //need to fix after the  still in the border 
		if(Main.tempObject==null) {
			Main.tempObject = form ;
			Main.pane.getChildren().remove(form.a);
			Main.pane.getChildren().remove(form.b);
			Main.pane.getChildren().remove(form.c);
			Main.pane.getChildren().remove(form.d);
			Form a =  Main.nextObject ;
			Main.nextObject = Main.thirdObject;
			Main.thirdObject = Main.fourthObject;
			Main.fourthObject = Main.fifthObject;
			Main.fifthObject = makeTetri();
			Main.object = a ;
			Main.pane.getChildren().addAll(Main.object.a , Main.object.b , Main.object.c , Main.object.d);
			Main.moveByKeyPress(Main.object);
		}else {
			Form a = null;
			if(Main.tempObject.getName()=="j") {
				if(form.c.getX()-SIZE>=0 && form.c.getY()-SIZE>0 && form.c.getX()<=XMAX-SIZE) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()-SIZE);
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX()-SIZE);
					Main.tempObject.b.setY(form.c.getY());
					Main.tempObject.d.setX(form.c.getX()+SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="l") {
				if(form.c.getX()-SIZE>=0 && form.c.getX()+SIZE <=XMAX-SIZE && form.c.getY()-SIZE>0 ) {
				a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()+SIZE);
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX()+SIZE);
					Main.tempObject.b.setY(form.c.getY());
					Main.tempObject.d.setX(form.c.getX()-SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="o") {
				if(form.c.getX()+SIZE <=XMAX-SIZE && form.c.getY()-SIZE>0) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX());
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX()+SIZE);
					Main.tempObject.b.setY(form.c.getY()-SIZE);
					Main.tempObject.d.setX(form.c.getX()+SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="s") {
				if(form.c.getX()-SIZE>=0 && form.c.getX()+SIZE <=XMAX-SIZE && form.c.getY()-SIZE>0) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()+SIZE);
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX());
					Main.tempObject.b.setY(form.c.getY()-SIZE);
					Main.tempObject.d.setX(form.c.getX()-SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="z") {
				if(form.c.getX()-SIZE>=0 && form.c.getX()+SIZE <=XMAX-SIZE && form.c.getY()-SIZE>0) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()-SIZE);
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX());
					Main.tempObject.b.setY(form.c.getY()-SIZE);
					Main.tempObject.d.setX(form.c.getX()+SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="t") {
				if(form.c.getX()-SIZE>=0 && form.c.getX()+SIZE <=XMAX-SIZE && form.c.getY()-SIZE>0) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()-SIZE);
					Main.tempObject.a.setY(form.c.getY()-SIZE);
					Main.tempObject.b.setX(form.c.getX());
					Main.tempObject.b.setY(form.c.getY()-SIZE);
					Main.tempObject.d.setX(form.c.getX()+SIZE);
					Main.tempObject.d.setY(form.c.getY()-SIZE);
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}
			}
			else if(Main.tempObject.getName()=="i") {
				if(form.c.getX()-2*SIZE>=0 && form.c.getX()+SIZE <=XMAX-SIZE) {
					a = form ;
					Main.pane.getChildren().remove(form.a);
					Main.pane.getChildren().remove(form.b);
					Main.pane.getChildren().remove(form.c);
					Main.pane.getChildren().remove(form.d);
					Main.pane.getChildren().addAll( Main.tempObject.a, Main.tempObject.b, Main.tempObject.c, Main.tempObject.d);
					Main.tempObject.formType=1;
					Main.tempObject.c.setX(form.c.getX());
					Main.tempObject.c.setY(form.c.getY());
					Main.tempObject.a.setX(form.c.getX()-2*SIZE);
					Main.tempObject.a.setY(form.c.getY());
					Main.tempObject.b.setX(form.c.getX()-SIZE);
					Main.tempObject.b.setY(form.c.getY());
					Main.tempObject.d.setX(form.c.getX()+SIZE);
					Main.tempObject.d.setY(form.c.getY());
					form = Main.tempObject ;
					Main.tempObject = a ;
					Main.object = form;
					Main.moveByKeyPress(Main.object);
				}		
			}
			else
				return;
		}
	}	
	
	public static void Projection(Form form) {
		projectForm.a.setX(form.a.getX());
		projectForm.b.setX(form.b.getX());
		projectForm.c.setX(form.c.getX());
		projectForm.d.setX(form.d.getX());
		if(projectForm.a.getY()==form.a.getY()||projectForm.a.getY()==form.b.getY()||projectForm.a.getY()==form.c.getY()||projectForm.a.getY()==form.d.getY()) {
			RemoveProjection();
			return;
		}
		if(projectForm.b.getY()==form.a.getY()||projectForm.b.getY()==form.b.getY()||projectForm.b.getY()==form.c.getY()||projectForm.b.getY()==form.d.getY()) {
			RemoveProjection();
			return;
		}
		if(projectForm.c.getY()==form.a.getY()||projectForm.c.getY()==form.b.getY()||projectForm.c.getY()==form.c.getY()||projectForm.c.getY()==form.d.getY()) {
			RemoveProjection();
			return;
		}
		if(projectForm.d.getY()==form.a.getY()||projectForm.d.getY()==form.b.getY()||projectForm.d.getY()==form.c.getY()||projectForm.d.getY()==form.d.getY()) {
			RemoveProjection();
			return;
		}
		
		for(int i = 0 ; i <FIELD[0].length ; i++) {
			if(form.a.getY()+i*SIZE==YMAX-SIZE ||form.b.getY()+i*SIZE==YMAX-SIZE ||form.c.getY()+i*SIZE==YMAX-SIZE ||form.d.getY()+i*SIZE==YMAX-SIZE ||
					FIELD[(int)form.a.getX()/SIZE][(int)(form.a.getY()/SIZE)+i+1]==1 || FIELD[(int)form.b.getX()/SIZE][(int)(form.b.getY()/SIZE)+i+1]==1 ||
					FIELD[(int)form.c.getX()/SIZE][(int)(form.c.getY()/SIZE)+i+1]==1 || FIELD[(int)form.d.getX()/SIZE][(int)(form.d.getY()/SIZE)+i+1]==1 ) { 
				projectForm.a.setY(form.a.getY()+SIZE*i);
				projectForm.b.setY(form.b.getY()+SIZE*i);
				projectForm.c.setY(form.c.getY()+SIZE*i);
				projectForm.d.setY(form.d.getY()+SIZE*i);
				break;
				
			}
		}
			Main.pane.getChildren().addAll(projectForm.a,projectForm.b,projectForm.c,projectForm.d);
	}
	
	public static void RemoveProjection() {
		Main.pane.getChildren().remove(projectForm.a);
		Main.pane.getChildren().remove(projectForm.b);
		Main.pane.getChildren().remove(projectForm.c);
		Main.pane.getChildren().remove(projectForm.d);
	}
}




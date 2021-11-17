import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form   {
	//Variables 
	Rectangle a ;
	Rectangle b ;
	Rectangle c ;
	Rectangle d ;
	private String name ;
	Color color ;
	public int formType =1;
	
	//constructor
	public Form(Rectangle a , Rectangle b , Rectangle c , Rectangle d ,String name) {
		this.a = a ;
		this.b = b ;
		this.c = c ;
		this.d = d ;
		this.name = name ;
		switch(name) {
		case "j":
			color = Color.HOTPINK;
			break;
		case "l":
			color = Color.ORANGE;
			break;
		case "o":
			color = Color.YELLOW;
			break;
		case "s":
			color = Color.RED;
			break;
		case "z":
			color = Color.LIMEGREEN;
			break;
		case "t":
			color = Color.PURPLE; 
			break;
		case "i":
			color = Color.AQUA;
			break;
		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}
	public Form(Rectangle a , Rectangle b , Rectangle c , Rectangle d) {
		this.a = a ;
		this.b = b ;
		this.c = c ;
		this.d = d ;
		
		this.a.setFill(Color.GRAY);
		this.b.setFill(Color.GRAY);
		this.c.setFill(Color.GRAY);
		this.d.setFill(Color.GRAY);
	}
	
	public String getName() { return name ;}
	
	public void changeType() {
		if(formType!=4) {
			formType++;
		}else {
			formType = 1;
		}
	}
}
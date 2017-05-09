import java.awt.Color;
import java.util.ArrayList;
import processing.core.PGraphics;

public class Animation extends MovingShape{
	private ArrayList<Image> list;
	private int currentIndex, millisBetweenAnimation;
	double xScale, yScale;
	private long prevSwitch;
	public Animation(int xPos, int yPos, double xScale, double yScale, double xSpeed, double ySpeed, int millisBetweenAnimation, ArrayList<Image>list) {
		super(xPos, yPos, (int)(list.get(0).getWidth()*xScale), (int)(list.get(0).getHeight()*yScale), xSpeed, ySpeed, Color.white);
		this.list = list;
		this.millisBetweenAnimation = millisBetweenAnimation;
		this.xScale = xScale;
		this.yScale = yScale;
		currentIndex = 0;
		prevSwitch = System.currentTimeMillis();
	}
	
	public Animation(int xPos, int yPos, double xScale, double yScale,int millisBetweenAnimation, ArrayList<Image>list){
		super(xPos, yPos, (int)(list.get(0).getWidth()*xScale), (int)(list.get(0).getHeight()*yScale), 0, 0, Color.white);
		this.list = list;
		this.millisBetweenAnimation = millisBetweenAnimation;
		this.xScale = xScale;
		this.yScale = yScale;
		currentIndex = 0;
		prevSwitch = System.currentTimeMillis();
		
	}
	
	public void draw(PGraphics g){
		double tempXScale = list.get(currentIndex).getXScale();
		double tempYScale = list.get(currentIndex).getYScale();
		list.get(currentIndex).setScale(tempXScale*xScale, tempYScale*yScale);
		list.get(currentIndex).draw(g);
		list.get(currentIndex).setScale(tempXScale, tempYScale);
	}
	
	public void goToNext(){
		if(currentIndex != list.size() - 1){
			currentIndex++;
		}else{
			currentIndex = 0;
		}
		prevSwitch = System.currentTimeMillis();
		setWidth((int)(list.get(currentIndex).getWidth()*xScale));
		setHeight((int)(list.get(currentIndex).getHeight()*yScale));
	}
	
	public void update(){
		super.update();
		for(BasicShape b : list){
			b.setPos(getX(),getY());
		}
		if(System.currentTimeMillis() - prevSwitch >= millisBetweenAnimation){
			goToNext();
		}
	}
	
	public void stopCollision(ArrayList<BasicShape> colList){
		super.stopCollision(colList);
		for(BasicShape b : list){
			b.setPos(getX(),getY());
		}
	}public ArrayList<Image> getList(){
		return list;
	}
	public void setScale(double xScale, double yScale){
		this.xScale = xScale;
		this.yScale = yScale;
		setWidth((int)(list.get(currentIndex).getWidth()*xScale));
		setHeight((int)(list.get(currentIndex).getHeight()*yScale));
	}
	public void setXScale(double xScale){
		this.xScale = xScale;
		setWidth((int)(list.get(currentIndex).getWidth()*xScale));
	}
	public void setYScale(double yScale){
		this.yScale = yScale;
		setHeight((int)(list.get(currentIndex).getHeight()*yScale));
	}
	
	public double getXScale(){
		return xScale;
	}
	
	public double getYScale(){
		return yScale;
	}
	

}

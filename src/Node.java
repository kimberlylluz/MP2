import java.awt.Color;

public class Node {
	Node next, left, right;
	Color color;
	int frequency;
	
	public Node(Color color, int frequency){
		this.color = color;
		this.frequency = frequency;
	}
	
	public Node(Color color, int frequency, Node left, Node right){
		this.left = left;
		this.right = right;
		this.color = color;
		this.frequency = frequency;
	}
	
	public Node getRight(){
		return right;
	}
	
	public void setRight(Node right){
		this.right = right;
	}
	
	public Node getLeft(){
		return right;
	}
	
	public void setLeft(Node left){
		this.left = left;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
	
	public Node getNext(){
		return next;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public Color getColor(){
		return color;
	}
}

import java.awt.Color;

public class Heap {
	int size;
	int frequency;
	int currentSize;
	
	public Heap(int size){
		this.size = size;
		currentSize = 0;
	}
	
	public void insert(Node[] node){
		minSort(node);
	}
	
	public void minSort(Node[] node){
		currentSize = node.length;
		heapify(node);
		for (int i=size; i > 0 ; i --){
			swap(node, 0, i);
			size = size-1;
			minHeap(node, 0);
		}
	}
	
	public void heapify(Node[] node){
		size = node.length - 1;
		for(int i = size/2 ; i>=0; i--){
			minHeap(node, i);
		}
	}
	
	public void minHeap(Node[] node, int i){
		int max = i;
		int left = 2*i;
		int right = 2*i+1;
		
		if (left<=size && node[left].getFrequency() > node[i].getFrequency()){
			max = left;
		}
		
		if (right <= size && node[right].getFrequency() > node[max].getFrequency()){
			max = right;
		}
		
		if (max != i){
			swap(node, i, max);
			minHeap(node, max);
		}
	}
	
	public void swap(Node[] node, int i, int j){
		int temp = node[i].getFrequency();
		Color tempColor = node[i].getColor();
		node[i].setFrequency(node[j].getFrequency());
		node[i].setColor(node[j].getColor());
		node[i].setFrequency(temp);
		node[j].setColor(tempColor);;
	}
	
	public void delete(Node[] node){
		Node temp[] = new Node[node.length-1];
		node[0] = node[node.length-1];
		System.arraycopy(node, 0, temp, 0, node.length-1);
		size = node.length-1;
		node = temp;
	}
}

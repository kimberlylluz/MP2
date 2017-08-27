import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.regex.*;

public class Main extends JPanel{
	String imageName;
	String freqName, yunoName, renderFilename;
	private Test test;
	public static JLabel mainLabel;
	public static JButton originalImage, compressedImage;
	ImageIcon icon = new ImageIcon(getClass().getResource("bg.png"));
	boolean clickedB = false, clickedF = false, clickedT = false, clickedC = false, clickedR = false, cNewImage = false, trainable = false, compressable = false, renderable = false, done = false;
	private JButton buttons, train, compress, render, file, newImage, exit, close, openButton, trainButton, compressButton, renderButton;
	
	Heap heap;
	int n = 0, count = 0, numPix = 0, maxPixels = 0, frequency = 0, r = 0, g =  0, b = 0, red = 0, green = 0, blue = 0, width = 368, height = 424;
	Color[] color;
	BufferedImage image;
	boolean exist = false;
	Color[] entryPixels = null;
	Node[] node = new Node[numPix];
	Node root = null, head = null, current = null;	
	
	public Main(Test test){
		this.test = test;
		setLayout(null);
		
		mainLabel = new JLabel(icon);
		mainLabel.setLocation(0,0);
		mainLabel.setSize(1000,650);
		
		compressedImage = new JButton();
		compressedImage.setLocation(554,99);
		compressedImage.setSize(368,424);
		compressedImage.setEnabled(false);
		mainLabel.add(modifyButton(compressedImage));
		
		file = new JButton(new ImageIcon(getClass().getResource("file.png")));
        file.setRolloverIcon(new ImageIcon(getClass().getResource("filehover.png")));
		file.setLocation(5,4);
		file.setSize(62, 39);
		mainLabel.add(modifyButton(file));
		
		newImage = new JButton(new ImageIcon(getClass().getResource("new1.png")));
        newImage.setRolloverIcon(new ImageIcon(getClass().getResource("new1hover.png")));
		newImage.setLocation(15,34);
		newImage.setSize(164, 54);
		newImage.setVisible(false);
		mainLabel.add(modifyButton(newImage));
		
		exit = new JButton(new ImageIcon(getClass().getResource("exit.png")));
        exit.setRolloverIcon(new ImageIcon(getClass().getResource("exithover.png")));
		exit.setLocation(15,88);
		exit.setSize(164,41);
		exit.setVisible(false);
		mainLabel.add(modifyButton(exit));
		
		originalImage = new JButton();
		originalImage.setLocation(73,98);
		originalImage.setSize(371,426);
		originalImage.setVisible(false);
		mainLabel.add(modifyButton(originalImage));
		
		buttons = new JButton(new ImageIcon(getClass().getResource("buttons.png")));
        buttons.setRolloverIcon(new ImageIcon(getClass().getResource("buttonshover.png")));
		buttons.setLocation(60,3);
		buttons.setSize(93, 40);
		mainLabel.add(modifyButton(buttons));
		
		train = new JButton(new ImageIcon(getClass().getResource("train.png")));
        train.setRolloverIcon(new ImageIcon(getClass().getResource("trainhover.png")));
		train.setLocation(67,37);
		train.setSize(164, 48);
		train.setVisible(false);
		mainLabel.add(modifyButton(train));
		
		compress = new JButton(new ImageIcon(getClass().getResource("compress.png")));
        compress.setRolloverIcon(new ImageIcon(getClass().getResource("compresshover.png")));
		compress.setLocation(67,85);
		compress.setSize(164, 37);
		compress.setVisible(false);
		mainLabel.add(modifyButton(compress));
		
		render = new JButton(new ImageIcon(getClass().getResource("render.png")));
        render.setRolloverIcon(new ImageIcon(getClass().getResource("renderhover.png")));
		render.setLocation(67,122);
		render.setSize(164,39);
		render.setVisible(false);
		mainLabel.add(modifyButton(render));
		
		close = new JButton(new ImageIcon(getClass().getResource("close.png")));
        close.setRolloverIcon(new ImageIcon(getClass().getResource("closehover.png")));
		close.setLocation(975,5);
		close.setSize(20,21);
		mainLabel.add(modifyButton(close));
		
		openButton = new JButton(new ImageIcon(getClass().getResource("open.png")));
        openButton.setRolloverIcon(new ImageIcon(getClass().getResource("open.png")));
		openButton.setLocation(90,569);
		openButton.setSize(88, 59);
		openButton.setVisible(true);
		mainLabel.add(modifyButton(openButton));
		
		trainButton = new JButton(new ImageIcon(getClass().getResource("trainButton.png")));
        trainButton.setRolloverIcon(new ImageIcon(getClass().getResource("trainButton.png")));
		trainButton.setLocation(210,570);
		trainButton.setSize(172, 55);
		trainButton.setVisible(true);
		mainLabel.add(modifyButton(trainButton));
		
		compressButton = new JButton(new ImageIcon(getClass().getResource("compressButton.png")));
        compressButton.setRolloverIcon(new ImageIcon(getClass().getResource("compressButton.png")));
		compressButton.setLocation(415,570);
		compressButton.setSize(172, 55);
		compressButton.setVisible(true);
		mainLabel.add(modifyButton(compressButton));
		
		renderButton = new JButton(new ImageIcon(getClass().getResource("renderButton.png")));
        renderButton.setRolloverIcon(new ImageIcon(getClass().getResource("renderButton.png")));
		renderButton.setLocation(626,570);
		renderButton.setSize(172,55);
		renderButton.setVisible(true);
		mainLabel.add(modifyButton(renderButton));
		
		Handler handler = new Handler();
		renderButton.addActionListener(handler);
		compressButton.addActionListener(handler);
		trainButton.addActionListener(handler);
		openButton.addActionListener(handler);
		newImage.addActionListener(handler);
		compress.addActionListener(handler);
		buttons.addActionListener(handler);
		render.addActionListener(handler);
		close.addActionListener(handler);
		train.addActionListener(handler);
		exit.addActionListener(handler);
		file.addActionListener(handler);
	}
	
	private JButton modifyButton(JButton button){
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}
	
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == buttons){
				if(clickedB == true){
					buttons.setIcon(new ImageIcon(getClass().getResource("buttons.png")));
					train.setVisible(false);
					compress.setVisible(false);
					render.setVisible(false);
					clickedB = false;
				}else{
					buttons.setIcon(new ImageIcon(getClass().getResource("buttonsclicked.png")));
					train.setVisible(true);
					compress.setVisible(true);
					render.setVisible(true);
					clickedB = true;
				}
			}else if(event.getSource() == file){
				if(clickedF == true){
					file.setIcon(new ImageIcon(getClass().getResource("file.png")));
					newImage.setVisible(false);
					exit.setVisible(false);
					clickedF = false;
				}else{
					file.setIcon(new ImageIcon(getClass().getResource("fileclicked.png")));
					newImage.setVisible(true);
					exit.setVisible(true);
					clickedF = true;
				}
			}else if(event.getSource() == newImage){
				file.setIcon(new ImageIcon(getClass().getResource("file.png")));
				newImage.setVisible(false);
				exit.setVisible(false);
				clickedF = false;
				clearAll();
			}else if(event.getSource() == train){
				if(clickedT == true){
					train.setIcon(new ImageIcon(getClass().getResource("train.png")));
					trainButton.setVisible(true);
					clickedT = false;
				}else{
					train.setIcon(new ImageIcon(getClass().getResource("trainhover.png")));
					trainButton.setVisible(false);
					clickedT = true;
				}
			}else if(event.getSource() == compress){
				if(clickedC == true){
					compress.setIcon(new ImageIcon(getClass().getResource("compress.png")));
					compressButton.setVisible(true);
					clickedC = false;
				}else{
					compress.setIcon(new ImageIcon(getClass().getResource("compresshover.png")));
					compressButton.setVisible(false);
					clickedC = true;
				}
			}else if(event.getSource() == render){
				if(clickedR == true){
					render.setIcon(new ImageIcon(getClass().getResource("render.png")));
					renderButton.setVisible(true);
					clickedR = false;
				}else{
					render.setIcon(new ImageIcon(getClass().getResource("renderhover.png")));
					renderButton.setVisible(false);
					clickedR = true;
				}
			}else if(event.getSource() == exit){
				System.exit(0);
			}else if(event.getSource() == close){
				System.exit(0);
			}else if(event.getSource() == openButton){
				if(cNewImage == true){
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
					chooser.setFileFilter(filter);
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						imageName = chooser.getSelectedFile().getAbsolutePath();

						JLabel label1 = new JLabel();
						label1.setBounds(76, 100, 368, 424);
						
						try{
							image = ImageIO.read(new File(imageName));
						}catch(IOException ex){}
						
						label1.setSize(width, height);
						label1.setLayout(new BorderLayout());
						Opicture opic = new Opicture(imageName);
						opic.setSize(width,height);
						opic.setOpaque(true);
						label1.add(opic);
						mainLabel.add(label1);
						mainLabel.repaint();
						
						System.out.println("Ready to compress " + imageName);
						trainable = true;
						
						originalImage.setVisible(false);
						trainButton.setRolloverIcon(new ImageIcon(getClass().getResource("trainButtonhover.png")));
						System.out.println("Ready to train");
					}
				}
			}else if(event.getSource() == trainButton){
				if(trainable == true){
					freqName = JOptionPane.showInputDialog(null, "Enter filename:");
					
					System.out.println("processing");
					
					color = new Color[image.getWidth() * image.getHeight()];
					
					for (int y = 0; y < image.getHeight(); y++) {
						for (int x = 0; x < image.getWidth(); x++) {
							color[maxPixels] = new Color(image.getRGB(x, y));
							maxPixels++;
						}
					}
	
					System.out.println("processing.");
					findUnique();
					System.out.println("processing..");
					countFreq();
					System.out.println("processing...");
					makeList();
					System.out.println("processing....");
					constructTree();
					
					System.out.println("processing.....");
					visitNode(root, "");
					System.out.println("processing......");
					
					compressable = true;
					compressButton.setRolloverIcon(new ImageIcon(getClass().getResource("compressButtonhover.png")));
					System.out.println("Ready to compress.");
				}
			}else if(event.getSource() == compressButton){
				if(compressable == true){
					yunoName = JOptionPane.showInputDialog(null, "Enter filename:");
					compress();
					
					renderable = true;
					renderButton.setRolloverIcon(new ImageIcon(getClass().getResource("renderButtonhover.png")));
					System.out.println("Ready to render.");
				}
			}else if(event.getSource() == renderButton){
				if(renderable == true){
					try{
						image = ImageIO.read(new File(imageName));
					}catch(IOException ex){}
					
					int width = image.getWidth();
					int height = image.getHeight();
					
					renderFilename = JOptionPane.showInputDialog(null, "Enter filename(.png):");
					done = true;
					System.out.println("processing");
					
					JLabel label = new JLabel();
					label.setBounds(555, 100, 368, 424);	
					label.setLayout(new BorderLayout());
					Picture pic = new Picture(renderFilename, yunoName, freqName, width, height);
					
					pic.setSize(368,424);
					label.add(pic);
					mainLabel.add(label);
					mainLabel.repaint();
					
				}
			}
		}
	}
	
	public void compress(){
		boolean checker = false;
		
		File file = new File(yunoName);
		File file2 = new File(freqName);
		
		try{
			FileWriter fileWriter = new FileWriter(file, true);		
			FileReader fileReader = new FileReader(file2);   
			String code;
			Scanner scan;
			
			for (int i = 0; i<color.length; i++){
				fileReader = new FileReader(file2);
				scan = null;
				scan = new Scanner(fileReader);
				
				
				checker = false;
				
				while (scan.hasNext()){
					code = scan.next();
					
					if (color[i].toString().equals(code)){
						fileWriter.write(scan.next()+"\n");
						checker = true;
						break;
					}
					else{
						scan.next();
					}
				}
				
				if (checker == false){
					System.out.println(color[i].toString());
				}
				
				scan.close();
				scan = null;
				fileReader.close();
			}
			
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
	}
	
	public void visitNode(Node node, String string){
		File file2 = new File(freqName);
		
		try{
			FileWriter fileWriter = new FileWriter(file2, true);
			if(node.left!=null){
				visitNode(node.left, string + "0");
			}
			
			if (node.right!=null){
				visitNode(node.right, string + "1");
			}
			
			if (node.left == null && node.right == null && n < numPix){
				fileWriter.write(node.getColor()+"\n");
				fileWriter.write(string+"\n");
			}
			
			fileWriter.flush();
			fileWriter.close();
		}
		
		catch(IOException io){
			io.printStackTrace();
		}
	}
	
	public void sortedInsert(Node parent){
         Node current;
 
         if (head == null || head.frequency >= parent.frequency){
            parent.next = head;
            head = parent;
         }else{
            current = head;
 
            while (current.next != null && current.next.frequency < parent.frequency)
                  current = current.next;
 
            parent.next = current.next;
            current.next = parent;
         }        
         root = head;
     }
	
	public Node removeFirst(){
		Node nodeRemove = head;
		if(nodeRemove != null){
			head = nodeRemove.getNext();
			nodeRemove.setNext(null);
		}
		return nodeRemove;
	}
	
	public void constructTree(){
		try{
			for(int i = 0; i < numPix; i++){
				Node parent = new Node(null, 0, null, null);
				
				Node leftChild = removeFirst();
				parent.setLeft(leftChild);
				
				Node rightChild = removeFirst();
				parent.setRight(rightChild);
				
				parent.setFrequency(rightChild.getFrequency() + leftChild.getFrequency());
				
				sortedInsert(parent);
			}
		}catch(NullPointerException ex){}
	}
	
	public void makeList(){
		for (int i = 0; i<numPix; i++){
			if (head == null){
				count++;
				head = node[i];
				current = head;
			}else{	
				count++;
				current.setNext(node[i]);
				current = current.getNext();
			}
		}
	}
	
	public void findUnique(){
		for (int i = 0; i< color.length ; i++){
			exist = false;
			/*r = (int)(color[i].getRed() * 0.299);
			g = (int)(color[i].getGreen() * 0.587);
			b = (int)(color[i].getBlue() *0.114);*/
			
			r = color[i].getRed();
			g = color[i].getGreen();
			b = color[i].getBlue();
			
			for (int j = i-1; j >= 0; j--){
				/*red = (int)(color[j].getRed() * 0.299);
				green = (int)(color[j].getGreen() * 0.587);
				blue = (int)(color[j].getBlue() *0.114);*/
				
				red = color[j].getRed();
				green = color[j].getGreen();
				blue = color[j].getBlue();
				
				if (r == red && g == green && b == blue){
					exist = true;
					break;
				}
			}
			
			if(exist == false){
				if (numPix<=0){
					numPix++;
					entryPixels = new Color[numPix];
					entryPixels[numPix-1] = color[i];
				}else{
					numPix++;
					Color[] temp = new Color[numPix];
					
					System.arraycopy(entryPixels, 0, temp, 0, entryPixels.length);
					
					entryPixels = new Color[numPix];
					entryPixels = temp;
					entryPixels[numPix-1] = color[i];
				}
			}
		}
	}
	
	public void countFreq(){
		File file = new File("Frequencies.huff");
		
		try{
			FileWriter fileWriter = new FileWriter(file, true);
			for (int i = 0; i < entryPixels.length ; i++){
				for (int j = 0; j< color.length ; j++){
					if ( entryPixels[i].getRGB() == color[j].getRGB())
						frequency++;
				}
				
				if (frequency!= 0){
					fileWriter.write(entryPixels[i].toString()+"\n");
					fileWriter.write(frequency+"\n");
					
					if (count<=0){
						count++;
						node = new Node[count];
						
						node[count-1] = new Node(entryPixels[i], frequency);
						heap = new Heap(count);
						heap.insert(node);
					}else{
						count++;
						Node[] temp = new Node[count];
						
						System.arraycopy(node, 0, temp, 0, node.length);
						
						node = new Node[count];
						node = temp;
						node[count-1] = new Node(entryPixels[i], frequency);
						heap = new Heap(count);
						heap.insert(node);
					}
				}
				frequency = 0;
			}
			fileWriter.flush();
			fileWriter.close();
		}catch(IOException io){
			io.printStackTrace();
		}
	}

	public void clearAll(){
		cNewImage = true;
		openButton.setRolloverIcon(new ImageIcon(getClass().getResource("openhover.png")));
		System.out.println("Upload image.");
		trainButton.setRolloverIcon(new ImageIcon(getClass().getResource("trainButton.png")));
		compressButton.setRolloverIcon(new ImageIcon(getClass().getResource("compressButton.png")));
		renderButton.setRolloverIcon(new ImageIcon(getClass().getResource("renderButton.png")));
		
		originalImage.setIcon(new ImageIcon(getClass().getResource("button.png")));
		originalImage.setVisible(true);
		
		clickedB = false;
		clickedF = false;
		clickedT = false; 
		clickedC = false;
		clickedR = false;
		trainable = false;
		compressable = false;
		renderable = false;
		done = false;
		exist = false;
		
		heap = null;
		n = 0; 
		count = 0;
		numPix = 0;
		maxPixels = 0;
		frequency = 0;
		image = null;
		r = 0;
		g = 0;
		b = 0;

		entryPixels = null;
		Node[] node = new Node[numPix];
		red = 0;
		green = 0;
		blue = 0;
		root = null;
		head = null; 
		current = null;
		
		width = 368;
		height = 424;
	}
}
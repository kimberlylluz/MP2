import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel{
	private Test test;
	public static JLabel mainLabel;
	private JButton buttons, train, compress, render, file, newImage, exit, close, trainButton, compressButton, renderButton;
	ImageIcon icon = new ImageIcon(getClass().getResource("bg.png"));
	boolean clickedB = false, clickedF = false, clickedT = false, clickedC = false, clickedR = false;
	
	public Main(Test test){
		this.test = test;
		setLayout(null);
		
		mainLabel = new JLabel(icon);
		mainLabel.setLocation(0,0);
		mainLabel.setSize(1000,650);
		
		file = new JButton(new ImageIcon(getClass().getResource("file.png")));
        file.setRolloverIcon(new ImageIcon(getClass().getResource("filehover.png")));
		file.setLocation(5,4);
		file.setSize(62, 39);
		mainLabel.add(modifyButton(file));
		
		newImage = new JButton(new ImageIcon(getClass().getResource("new.png")));
        newImage.setRolloverIcon(new ImageIcon(getClass().getResource("newhover.png")));
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
		
		trainButton = new JButton(new ImageIcon(getClass().getResource("trainButton.png")));
        trainButton.setRolloverIcon(new ImageIcon(getClass().getResource("trainButtonhover.png")));
		trainButton.setLocation(201,570);
		trainButton.setSize(172, 55);
		trainButton.setVisible(true);
		mainLabel.add(modifyButton(trainButton));
		
		compressButton = new JButton(new ImageIcon(getClass().getResource("compressButton.png")));
        compressButton.setRolloverIcon(new ImageIcon(getClass().getResource("compressButtonhover.png")));
		compressButton.setLocation(415,570);
		compressButton.setSize(172, 55);
		compressButton.setVisible(true);
		mainLabel.add(modifyButton(compressButton));
		
		renderButton = new JButton(new ImageIcon(getClass().getResource("renderButton.png")));
        renderButton.setRolloverIcon(new ImageIcon(getClass().getResource("renderButtonhover.png")));
		renderButton.setLocation(626,570);
		renderButton.setSize(172,55);
		renderButton.setVisible(true);
		mainLabel.add(modifyButton(renderButton));
		
		Handler handler = new Handler();
		buttons.addActionListener(handler);
		file.addActionListener(handler);
		train.addActionListener(handler);
		compress.addActionListener(handler);
		render.addActionListener(handler);
		close.addActionListener(handler);
		exit.addActionListener(handler);
	}
	
	private JButton modifyButton (JButton button){
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
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
			}
			else if(event.getSource() == file){
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
			}
			else if(event.getSource() == newImage){
			}
			else if(event.getSource() == exit){
				System.exit(0);
			}
			else if(event.getSource() == close){
				System.exit(0);
			}
			else if(event.getSource() == train){
				if(clickedT == true){
					train.setIcon(new ImageIcon(getClass().getResource("train.png")));
					trainButton.setVisible(true);
					clickedT = false;
				}else{
					train.setIcon(new ImageIcon(getClass().getResource("trainhover.png")));
					trainButton.setVisible(false);
					clickedT = true;
				}
			}
			else if(event.getSource() == compress){
				if(clickedC == true){
					compress.setIcon(new ImageIcon(getClass().getResource("compress.png")));
					compressButton.setVisible(true);
					clickedC = false;
				}else{
					compress.setIcon(new ImageIcon(getClass().getResource("compresshover.png")));
					compressButton.setVisible(false);
					clickedC = true;
				}
			}
			else if(event.getSource() == render){
				if(clickedR == true){
					render.setIcon(new ImageIcon(getClass().getResource("render.png")));
					renderButton.setVisible(true);
					clickedR = false;
				}else{
					render.setIcon(new ImageIcon(getClass().getResource("renderhover.png")));
					renderButton.setVisible(false);
					clickedR = true;
				}
			}
		}
	}
	
}
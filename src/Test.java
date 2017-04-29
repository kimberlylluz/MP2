import java.awt.*;
import javax.swing.*; 

public class Test extends JFrame{
	private static Test instance;
	public final Main main;
	private final JPanel jpanel;
	private final CardLayout layout;
	
	public Test(){
		setUndecorated(true);
        setLayout(null);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);

		main = new Main(this);
		main.setFocusable(false);
		main.setOpaque(false);

		layout = new CardLayout();
		
		jpanel = new JPanel();
        jpanel.setLocation(0,0);
		jpanel.setSize(1000,650);
		jpanel.setLayout(layout);
		
		jpanel.add(main, "Main");
		add(jpanel);
		
		setVisible(true);
	}
	
	public static Test getInstance(){
        if(instance == null)
            instance = new Test();
        return instance; 
    }

    public void switchCard(String string){
		layout.show(jpanel, string);
    }
	
	public static void main(String[] args){
		Test test = new Test();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(1000, 650);
		test.setResizable(false);
		test.setLocationRelativeTo(null);
		test.setContentPane(test.main.mainLabel);
		test.setVisible(true);
		test.setLayout(null);
	}
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Picture extends JPanel{
	Scanner scanBits, scanCodes;
	String renderFilename, yunoName, freqName, bits, prevLine, code;
	int width, height, y=0;
	Color colors;
	File f;
	BufferedImage img;
	boolean hasMatch = false;
	
	public Picture(String renderFilename, String yunoName, String freqName, int width, int height){
		this.renderFilename = renderFilename;
		this.yunoName = yunoName;
		this.freqName = freqName;
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		f = new File(renderFilename);
	
		try{
			int x = -1, red, green, blue;
			FileReader fileBits = new FileReader(yunoName);
			FileReader fileCodes = new FileReader(freqName);
			
			scanBits = new Scanner(fileBits);
			scanCodes = new Scanner(fileCodes);
			
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
					
			while(scanBits.hasNext() && y!=height){
				x++;
				bits = scanBits.next();  
				fileCodes = new FileReader(freqName);
				scanCodes = new Scanner(fileCodes);
				  
				while (scanCodes.hasNext()){
					prevLine = scanCodes.next();
					if (scanCodes.next().equals(bits)){
						Pattern p = Pattern.compile("java.awt.Color\\[r=(.*),g=(.*),b=(.*)\\]");
						Matcher m = p.matcher(prevLine);
							if (m.matches()) {
								red = Integer.parseInt(m.group(1));
								green = Integer.parseInt(m.group(2));
								blue = Integer.parseInt(m.group(3));
								colors = new Color(red, green, blue);
								img.setRGB(x, y, colors.getRGB());
							}
							scanCodes.close();
							scanCodes = null;
							break;
					}
				}
				if (x%width == width-1){
					x = -1;
					y++;
				} 
				if (y == height){
					scanBits.close();
					scanBits = null;
					fileCodes.close();
					fileBits.close();
					break;
				}
			}
			g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			ImageIO.write(img, "PNG", f); 
		}catch(IOException ex){
			ex.printStackTrace();
		}
		System.out.println("New Image Saved.");	
	}
}
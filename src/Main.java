import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
//from   ww  w . j a v a 2  s  . co m
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

  public static void main(String avg[]) throws Exception {
    BufferedImage img = ImageIO.read(new URL(
        "http://www.java2s.com/style/download.png"));
    //ImageIcon icon = new ImageIcon(img);
    Toolkit t = Toolkit.getDefaultToolkit();
    Image icon = t.createImage(new URL(
        "http://www.java2s.com/style/download.png"));
    JFrame frame = new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setSize(200, 300);
    //JLabel lbl = new JLabel();
    //lbl.setIcon(icon);
    frame.add(icon);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public class ImgComponent extends 
}
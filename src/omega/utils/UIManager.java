package omega.utils;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
public class UIManager {
	
	public static Color c1 = Color.decode("#132162");
	public static Color c2 = Color.decode("#070707");
	public static Color c3 = Color.decode("#3CE5DD");
	public static Color glow = Color.BLACK;
	public static Color  TOOLMENU_COLOR1 = Color.decode("#f0b40f");
	public static Color TOOLMENU_COLOR1_SHADE = new Color(TOOLMENU_COLOR1.getRed(), TOOLMENU_COLOR1.getGreen(), TOOLMENU_COLOR1.getBlue(), 40);
	public static Color TOOLMENU_COLOR2 = Color.decode("#D34D42");
	public static Color TOOLMENU_COLOR2_SHADE = new Color(TOOLMENU_COLOR2.getRed(), TOOLMENU_COLOR2.getGreen(), TOOLMENU_COLOR2.getBlue(), 40);
	public static Color TOOLMENU_COLOR3 = Color.decode("#22d5d5");
	public static Color TOOLMENU_COLOR3_SHADE = new Color(TOOLMENU_COLOR3.getRed(), TOOLMENU_COLOR3.getGreen(), TOOLMENU_COLOR3.getBlue(), 40);
	public static Color TOOLMENU_COLOR4 = Color.decode("#EB7201");
	public static Color TOOLMENU_COLOR4_SHADE = new Color(TOOLMENU_COLOR4.getRed(), TOOLMENU_COLOR4.getGreen(), TOOLMENU_COLOR4.getBlue(), 40);
	public static Color TOOLMENU_COLOR5 = new Color(16, 62, 110);
	public static Color TOOLMENU_COLOR5_SHADE = new Color(16, 62, 110, 40);
	public static Color TOOLMENU_GRADIENT = Color.decode("#132132");
	public static Color ALPHA = new Color(0, 0, 0, 0);
	
	//Some Backgrounds
	public static Color back1 = Color.decode("#111111");
	public static Color back2 = Color.decode("#0c0c0d");
	public static Color back3 = Color.decode("#161616");
	static{
		try{
			if(isDarkMode())
				FlatDarkLaf.install();
			else
				FlatLightLaf.install();
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, UIManager.class.getResourceAsStream("/UbuntuMono-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, UIManager.class.getResourceAsStream("/Ubuntu-Bold.ttf")));
			
			if(!isDarkMode()) {
				c3 = new Color(0, 0, 255, 130);
				c1 = new Color(0, 0, 255, 40);
				c2 = Color.WHITE;
				
				TOOLMENU_COLOR1 = new Color(26, 36, 219);
				TOOLMENU_COLOR1_SHADE = new Color(26, 36, 219, 40);
				TOOLMENU_COLOR2 = new Color(223, 33, 15);
				TOOLMENU_COLOR2_SHADE = new Color(223, 33, 15, 40);
				TOOLMENU_COLOR3 = new Color(126, 20, 219);
				TOOLMENU_COLOR3_SHADE = new Color(126, 20, 219, 40);
				TOOLMENU_COLOR4 = new Color(200, 103, 0);
				TOOLMENU_COLOR4_SHADE = new Color(200, 103, 0, 40);
				TOOLMENU_GRADIENT = new Color(200, 200, 200, 100);
				
				back1 = Color.decode("#f1f1f1");
				back2 = Color.decode("#f0f0f0");
				back3 = Color.decode("#D3D4D4");
			}
			else {
				glow = Color.WHITE;
				c1 = Color.decode("#132162");
				c2 = Color.decode("#070707");
				c3 = Color.decode("#3CE5DD");
				TOOLMENU_COLOR1 = Color.decode("#f0b40f");
				TOOLMENU_COLOR1_SHADE = new Color(TOOLMENU_COLOR1.getRed(), TOOLMENU_COLOR1.getGreen(), TOOLMENU_COLOR1.getBlue(), 40);
				TOOLMENU_COLOR2 = Color.decode("#D34D42");
				TOOLMENU_COLOR2_SHADE = new Color(TOOLMENU_COLOR2.getRed(), TOOLMENU_COLOR2.getGreen(), TOOLMENU_COLOR2.getBlue(), 40);
				TOOLMENU_COLOR3 = Color.decode("#22d5d5");
				TOOLMENU_COLOR3_SHADE = new Color(TOOLMENU_COLOR3.getRed(), TOOLMENU_COLOR3.getGreen(), TOOLMENU_COLOR3.getBlue(), 40);
				TOOLMENU_COLOR4 = Color.decode("#EB7201");
				TOOLMENU_COLOR4_SHADE = new Color(TOOLMENU_COLOR4.getRed(), TOOLMENU_COLOR4.getGreen(), TOOLMENU_COLOR4.getBlue(), 40);
				TOOLMENU_COLOR5 = Color.decode("#7f6021");
				TOOLMENU_COLOR5_SHADE = new Color(TOOLMENU_COLOR4.getRed(), TOOLMENU_COLOR4.getGreen(), TOOLMENU_COLOR4.getBlue(), 40);
				TOOLMENU_GRADIENT = Color.decode("#132132");
				
				back1 = Color.decode("#111111");
				back2 = Color.decode("#0c0c0d");
				back3 = Color.decode("#161616");
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public static final Font PX12 = new Font("Ubuntu Mono", Font.BOLD, 12);
	public static final Font PX14 = new Font("Ubuntu Mono", Font.BOLD, 14);
	public static final Font PX16 = new Font("Ubuntu Mono", Font.BOLD, 16);
	public static final Font PX18 = new Font("Ubuntu Mono", Font.BOLD, 18);
	public static final Font PX20 = new Font("Ubuntu Mono", Font.BOLD, 20);
	public static final Font PX22 = new Font("Ubuntu Mono", Font.BOLD, 22);
	public static final Font PX26 = new Font("Ubuntu Mono", Font.BOLD, 26);
	public static final Font PX28 = new Font("Ubuntu Mono", Font.BOLD, 28);
	public static final Font PX40 = new Font("Ubuntu Mono", Font.BOLD, 40);
	public static final Font UBUNTU_PX12 = new Font("Ubuntu", Font.BOLD, 12);
	public static final Font UBUNTU_PX14 = new Font("Ubuntu", Font.BOLD, 14);
	public static final Font UBUNTU_PX16 = new Font("Ubuntu", Font.BOLD, 16);
	
	public static boolean isDarkMode(){
		return true;
	}
}

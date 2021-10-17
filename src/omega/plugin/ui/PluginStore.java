package omega.plugin.ui;
import java.awt.geom.RoundRectangle2D;

import omega.plugin.PluginManager;

import omega.utils.IconManager;

import omega.comp.TextComp;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static omega.utils.UIManager.*;
import static omega.comp.Animations.*;
public class PluginStore extends JFrame{
	public PluginManager pluginManager;
	
	public TextComp iconComp;
	public TextComp titleComp;
	public TextComp closeComp;
	
	public PluginStore(PluginManager pluginManager){
		super("Plugin Store");
		this.pluginManager = pluginManager;
		setUndecorated(true);
		setSize(500, 450);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(null);
		panel.setBackground(back1);
		setContentPane(panel);
		setLayout(null);
		setResizable(false);
		init();
	}

	public void init(){
		iconComp = new TextComp(IconManager.ideImage64, 25, 25, back2, back2, back2, null);
		iconComp.setBounds(0, 0, 30, 30);
		iconComp.setClickable(false);
		iconComp.setArc(0, 0);
		add(iconComp);
		
		titleComp = new TextComp("Plugin Store", back2, back2, glow, null);
		titleComp.setBounds(30, 0, getWidth() - 60, 30);
		titleComp.setFont(PX14);
		titleComp.setArc(0, 0);
		titleComp.setClickable(false);
		titleComp.attachDragger(this);
		add(titleComp);

		closeComp = new TextComp(IconManager.fluentcloseImage, 20, 20, back2, c2, TOOLMENU_COLOR2_SHADE, this::dispose);
		closeComp.setBounds(getWidth() - 30, 0, 30, 30);
		closeComp.setArc(0, 0);
		add(closeComp);
	}

	public void doPostInit(){
		
	}

	@Override
	public void setSize(int width, int height){
		super.setSize(width, height);
		setShape(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
	}

	@Override
	public void setVisible(boolean value){
	     super.setVisible(value);
		if(value){
	          doPostInit();
		}
	}
}

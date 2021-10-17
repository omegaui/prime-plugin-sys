package omega.plugin.ui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.URL;

import omega.utils.IconManager;

import omega.plugin.Downloader;
import omega.plugin.PluginCategory;

import javax.imageio.ImageIO;

import omega.comp.FlexPanel;
import omega.comp.TextComp;

import javax.swing.JComponent;

import static omega.utils.UIManager.*;
import static omega.comp.Animations.*;
public class RemotePluginComp extends FlexPanel{
	
	public RemotePluginInfo remotePluginInfo;
	
	public TextComp iconComp;
	public TextComp nameComp;
	public TextComp sizeComp;
	public TextComp descriptionComp;
	public TextComp downloadComp;
	public TextComp categoryComp;
	
	public volatile boolean enter = false;
	
	public RemotePluginComp(RemotePluginInfo remotePluginInfo, int width, int height){
		super(null, c2, null);
		setArc(0, 0);
		setBorderColor(glow);
		this.remotePluginInfo = remotePluginInfo;
		setSize(width, height);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				setEnter(true);
			}
			@Override
			public void mouseExited(MouseEvent e){
				setEnter(false);
			}
		});
		init();
	}

	public void init(){
		iconComp = new TextComp(IconManager.ideImage64, getHeight(), getHeight(), c2, c2, c2, null);
		iconComp.setBounds(0, 0, getHeight(), getHeight());
		iconComp.setArc(0, 0);
		iconComp.setClickable(false);
		add(iconComp);

		nameComp = new TextComp(remotePluginInfo.name, remotePluginInfo.description, c2, c2, PluginCategory.getSuitableColor(remotePluginInfo.category), null);
		nameComp.setBounds(getHeight(), 0, getWidth() - 150, 25);
		nameComp.setFont(PX14);
		nameComp.setClickable(false);
		nameComp.setArc(0, 0);
		nameComp.alignX = 5;
		add(nameComp);

		sizeComp = new TextComp(remotePluginInfo.size, TOOLMENU_COLOR5_SHADE, c2, TOOLMENU_COLOR5, null);
		sizeComp.setBounds(getHeight(), 30, 100, 25);
		sizeComp.setArc(10, 10);
		sizeComp.setClickable(false);
		sizeComp.alignX = 5;
		sizeComp.setFont(UBUNTU_PX14);
		add(sizeComp);
	}

	public void setEnter(boolean enter){
		this.enter = enter;
		setPaintBorder(enter);
		repaint();
	}

	public void loadIcon(){
		try{
			iconComp.image = ImageIO.read(remotePluginInfo.imageURL);
			iconComp.repaint();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

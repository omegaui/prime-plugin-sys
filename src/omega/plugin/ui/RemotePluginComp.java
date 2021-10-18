package omega.plugin.ui;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

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

	public PluginStore pluginStore;
	
	public RemotePluginInfo remotePluginInfo;
	
	public TextComp iconComp;
	public TextComp nameComp;
	public TextComp sizeComp;
	public TextComp descriptionComp;
	public TextComp installComp;
	public TextComp categoryComp;
	
	public volatile boolean enter = false;
	
	public RemotePluginComp(PluginStore pluginStore, RemotePluginInfo remotePluginInfo, int width, int height){
		super(null, c2, null);
		setArc(0, 0);
		setBorderColor(glow);
		this.pluginStore = pluginStore;
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
		iconComp.addMouseListener(getMouseListeners()[0]);
		add(iconComp);

		nameComp = new TextComp(remotePluginInfo.name + " " + remotePluginInfo.version, c2, c2, PluginCategory.getSuitableColor(remotePluginInfo.category), null);
		nameComp.setBounds(getHeight(), 2, getWidth() - 160, 25);
		nameComp.setFont(PX14);
		nameComp.setClickable(false);
		nameComp.setPaintTextGradientEnabled(true);
		nameComp.setGradientColor(TOOLMENU_COLOR5);
		nameComp.setArc(0, 0);
		nameComp.alignX = 5;
		nameComp.addMouseListener(getMouseListeners()[0]);
		add(nameComp);

		sizeComp = new TextComp(remotePluginInfo.size, c2, c2, TOOLMENU_COLOR5, null);
		sizeComp.setBounds(getHeight(), 30, 100, 25);
		sizeComp.setPaintTextGradientEnabled(true);
		sizeComp.setGradientColor(nameComp.color3);
		sizeComp.setArc(10, 10);
		sizeComp.setClickable(false);
		sizeComp.alignX = 5;
		sizeComp.setFont(UBUNTU_PX12);
		sizeComp.addMouseListener(getMouseListeners()[0]);
		add(sizeComp);

		descriptionComp = new TextComp(remotePluginInfo.description, c2, c2, TOOLMENU_COLOR3, null);
		descriptionComp.setBounds(sizeComp.getX() + sizeComp.getWidth() + 2, 30, getWidth() - getHeight() - 100 - 100 - 100, 25);
		descriptionComp.setFont(UBUNTU_PX12);
		descriptionComp.setArc(0, 0);
		descriptionComp.setClickable(false);
		descriptionComp.alignX = 5;
		descriptionComp.addMouseListener(getMouseListeners()[0]);
		add(descriptionComp);

		installComp = new TextComp(pluginStore.pluginManager.isPluginInstalled(remotePluginInfo.fileName) ? "Uninstall" : "Install", TOOLMENU_COLOR1_SHADE, back2, TOOLMENU_COLOR1, this::installAction);
		installComp.setBounds(getWidth() - 100, 2, 99, 25);
		installComp.setFont(PX14);
		installComp.setArc(2, 2);
		add(installComp);

		categoryComp = new TextComp((remotePluginInfo.category.equals("") ? "All" : remotePluginInfo.category.toUpperCase()), back2, back2, glow, null);
		categoryComp.setBounds(getWidth() - 100, 30, 99, 25);
		categoryComp.setFont(UBUNTU_PX12);
		categoryComp.setArc(0, 0);
		categoryComp.setClickable(false);
		categoryComp.setPaintTextGradientEnabled(true);
		categoryComp.setGradientColor(PluginCategory.getSuitableColor(remotePluginInfo.category));
		categoryComp.addMouseListener(getMouseListeners()[0]);
		add(categoryComp);
	}

	public void installAction(){
		if(pluginStore.pluginManager.isPluginInstalled(remotePluginInfo.fileName)){
			pluginStore.pluginManager.uninstallPlugin(pluginStore, remotePluginInfo.name);
		}
		else {
			pluginStore.downloadPlugin(remotePluginInfo);
		}
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
			System.err.println("Unable to Read Icon of Plugin \"" + remotePluginInfo.name + "\"");
			e.printStackTrace();
		}
	}
}

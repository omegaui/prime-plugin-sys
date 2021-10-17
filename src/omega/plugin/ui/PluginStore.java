package omega.plugin.ui;
import java.awt.Dimension;

import java.util.LinkedList;

import java.awt.geom.RoundRectangle2D;

import omega.plugin.PluginManager;

import omega.utils.IconManager;

import omega.comp.TextComp;
import omega.comp.FlexPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static omega.utils.UIManager.*;
import static omega.comp.Animations.*;
public class PluginStore extends JFrame{
	public PluginManager pluginManager;
	
	public TextComp iconComp;
	public TextComp titleComp;
	public TextComp closeComp;

	public TextComp statusComp;

	public RemotePluginInfoLoader remotePluginInfoLoader;

	public FlexPanel contentPanel;
	public JScrollPane contentScrollPane;
	public JPanel panel;

	public LinkedList<RemotePluginComp> remotePluginComps = new LinkedList<>();
	
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

		statusComp = new TextComp("", back1, back1, glow, null);
		statusComp.setBounds(0, getHeight() - 25, getWidth(), 25);
		statusComp.setFont(PX14);
		statusComp.setClickable(false);
		statusComp.setArc(0, 0);
		statusComp.alignX = 10;
		setStatus(null);
		add(statusComp);

		contentPanel = new FlexPanel(null, c2, null);
		contentPanel.setBounds(5, 60, getWidth() - 10, getHeight() - 100);
		contentPanel.setArc(10, 10);
		add(contentPanel);

		contentScrollPane = new JScrollPane(panel = new JPanel(null));
		contentScrollPane.setBounds(5, 5, contentPanel.getWidth() - 10, contentPanel.getHeight() - 10);
		contentScrollPane.setBackground(back1);
		contentScrollPane.setBorder(null);
		panel.setBackground(back1);
		contentPanel.add(contentScrollPane);
	}

	public void genView(){
		remotePluginComps.forEach(panel::remove);
		remotePluginComps.clear();
		
		int block = 0;
		for(RemotePluginInfo info : remotePluginInfoLoader.remotePluginInfos){
			RemotePluginComp comp = new RemotePluginComp(info, contentScrollPane.getWidth(), 60);
			comp.setLocation(0, block);
			panel.add(comp);
			remotePluginComps.add(comp);

			block += 60;
		}
		panel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), block));
		layout();
		repaint();

		new Thread(()->{
			remotePluginComps.forEach(comp->{
				comp.loadIcon();
			});
		}).start();
	}

	public void doPostInit(){
		remotePluginInfoLoader = new RemotePluginInfoLoader(this);
		new Thread(()->{
			remotePluginInfoLoader.loadRemotePluginInfos();
			if(!remotePluginInfoLoader.remotePluginInfos.isEmpty())
				genView();
		}).start();
	}

	public void setStatus(String text){
		if(text == null)
			statusComp.setText("Copyright 2020 Omega UI. All Right Reserved.");
		else
			statusComp.setText(text);
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

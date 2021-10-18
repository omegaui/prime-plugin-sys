package omega.plugin;
import omega.utils.IconManager;

import java.awt.geom.RoundRectangle2D;

import omega.comp.EdgeComp;
import omega.comp.TextComp;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Color;

import static omega.utils.UIManager.*;
public final class PluginCategory extends JDialog{
	public static final String EDITING = "editing";
	public static final String UTILITY = "utility";
	public static final String SDK = "sdk";
	public static final String LANGUAGE_SUPPORT = "language-support";
	public static final String ANY_CATEGORY = "";

	public TextComp titleComp;
	public TextComp closeComp;

	public EdgeComp editingTagComp;
	public EdgeComp utilityTagComp;
	public EdgeComp sdkTagComp;
	public EdgeComp langSupportTagComp;
	public EdgeComp anyTagComp;

	public String currentCategory = ANY_CATEGORY;

	public PluginCategory(JFrame frame){
		super(frame, true);
		setUndecorated(true);
		setSize(250, 190);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel(null);
		panel.setBackground(back1);
		setContentPane(panel);
		setLayout(null);
		init();
	}

	public void init(){
		titleComp = new TextComp("Select Category", c2, c2, glow, null);
		titleComp.setBounds(0, 0, getWidth() - 30, 30);
		titleComp.setFont(PX14);
		titleComp.setClickable(false);
		titleComp.setArc(0, 0);
		titleComp.attachDragger(this);
		add(titleComp);

		closeComp = new TextComp(IconManager.fluentcloseImage, 20, 20, TOOLMENU_COLOR1_SHADE, c2, glow, this::cancel);
		closeComp.setBounds(getWidth() - 30, 0, 30, 30);
		closeComp.setArc(0, 0);
		add(closeComp);
		
		editingTagComp = new EdgeComp("Editing", glow, c2, getSuitableColor(EDITING), ()->{
			currentCategory = EDITING;
			dispose();
		});
		editingTagComp.setBounds(5, 40, getWidth() - 10, 25);
		editingTagComp.setFont(PX14);
		add(editingTagComp);
		
		utilityTagComp = new EdgeComp("Utility", glow, c2, getSuitableColor(UTILITY), ()->{
			currentCategory = UTILITY;
			dispose();
		});
		utilityTagComp.setBounds(5, 70, getWidth() - 10, 25);
		utilityTagComp.setFont(PX14);
		add(utilityTagComp);
		
		sdkTagComp = new EdgeComp("SDK", glow, c2, getSuitableColor(SDK), ()->{
			currentCategory = SDK;
			dispose();
		});
		sdkTagComp.setBounds(5, 100, getWidth() - 10, 25);
		sdkTagComp.setFont(PX14);
		add(sdkTagComp);
		
		langSupportTagComp = new EdgeComp("Lang-Support", glow, c2, getSuitableColor(LANGUAGE_SUPPORT), ()->{
			currentCategory = LANGUAGE_SUPPORT;
			dispose();
		});
		langSupportTagComp.setBounds(5, 130, getWidth() - 10, 25);
		langSupportTagComp.setFont(PX14);
		add(langSupportTagComp);
		
		anyTagComp = new EdgeComp("All", glow, c2, getSuitableColor(ANY_CATEGORY), ()->{
			currentCategory = ANY_CATEGORY;
			dispose();
		});
		anyTagComp.setBounds(5, 160, getWidth() - 10, 25);
		anyTagComp.setFont(PX14);
		add(anyTagComp);
	}

	public String makeChoice(String category){
		this.currentCategory = category;
		
		editingTagComp.setLookLikeLabel(category.equals(EDITING));
		utilityTagComp.setLookLikeLabel(category.equals(UTILITY));
		sdkTagComp.setLookLikeLabel(category.equals(SDK));
		langSupportTagComp.setLookLikeLabel(category.equals(LANGUAGE_SUPPORT));
		anyTagComp.setLookLikeLabel(category.equals(ANY_CATEGORY));
		
		setVisible(true);
		
		return currentCategory;
	}

	public void cancel(){
		currentCategory = null;
		dispose();
	}

	@Override
	public void setSize(int width, int height){
		super.setSize(width, height);
		setShape(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
	}

	public static Color getSuitableColor(String category){
		if(category.equals(EDITING))
			return TOOLMENU_COLOR1;
		if(category.equals(UTILITY))
			return TOOLMENU_COLOR3;
		if(category.equals(SDK))
			return TOOLMENU_COLOR4;
		if(category.equals(LANGUAGE_SUPPORT))
			return TOOLMENU_COLOR5;
		return TOOLMENU_COLOR2;
	}
}

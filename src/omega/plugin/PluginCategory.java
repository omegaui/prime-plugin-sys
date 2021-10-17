package omega.plugin;
import java.awt.Color;

import static omega.utils.UIManager.*;
public final class PluginCategory {
	public static final String EDITING = "editing";
	public static final String UTILITY = "utility";
	public static final String SDK = "sdk";
	public static final String LANGUAGE_SUPPORT = "language-support";

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

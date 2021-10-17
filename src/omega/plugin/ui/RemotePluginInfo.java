package omega.plugin.ui;
import omega.plugin.PluginCategory;

import java.net.URL;

import java.util.LinkedList;
public class RemotePluginInfo {
	public String name;
	public String description;
	public String author;
	public String license;
	public String size;
	public PluginCategory category;
	public URL imageURL;
	public LinkedList<URL> screenshotsURLs;
}

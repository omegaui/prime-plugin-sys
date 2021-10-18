package omega.plugin.ui;
import java.net.URL;

import java.util.LinkedList;
public class RemotePluginInfo {
	public String name;
	public String description;
	public String author;
	public String license;
	public String size;
	public String category;
	public String fileName;
	public URL pluginFileURL;
	public URL imageURL;
	public LinkedList<URL> screenshotsURLs = new LinkedList<>();
}

package omega.plugin;
import java.util.LinkedList;

import java.net.URLClassLoader;
import java.net.URL;

import java.io.File;
public class PluginLoader {
	public PluginManager pluginManager;

	public URLClassLoader classLoader;

	public LinkedList<String> pluginClassNames = new LinkedList<>();
	
	public PluginLoader(PluginManager pluginManager){
		this.pluginManager = pluginManager;
		putPluginsOnClasspath();
	}

	public void putPluginsOnClasspath(){
		try{
			File[] plugins = PluginManager.PLUGINS_DIRECTORY.listFiles((e)->e.getName().endsWith(".jar"));
			if(plugins == null || plugins.length == 0)
				return;
			URL[] urls = new URL[plugins.length];
			for(int i = 0; i < plugins.length; i++){
				urls[i] = plugins[i].toURL();
				pluginClassNames.add(plugins[i].getName().substring(0, plugins[i].getName().lastIndexOf(".")));
			}
			classLoader = URLClassLoader.newInstance(urls);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public Plugin loadPlugin(String pluginClassName){
		try{
			return (Plugin)classLoader.loadClass(pluginClassName).newInstance();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
}

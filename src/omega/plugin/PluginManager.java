package omega.plugin;
import java.util.LinkedList;

import java.io.File;

import omega.database.DataBase;
public class PluginManager extends DataBase{
	public static final String PLUGINS_DATA_BASE = ".omega-ide" + File.separator + ".pluginDB";
	public static final File PLUGINS_DIRECTORY = new File(".omega-ide" + File.separator + "plugins");

	public PluginLoader pluginLoader;
	public LinkedList<Plugin> plugins = new LinkedList<>();
	
	public PluginManager(){
		super(PLUGINS_DATA_BASE);
		load();
	}

	public void load(){
		pluginLoader = new PluginLoader(this);
		pluginLoader.pluginClassNames.forEach((name)->{
			Plugin plugin = pluginLoader.loadPlugin(name);
			if(plugin != null)
				plugins.add(plugin);
		});
		getDataSetNames().forEach((pluginName)->{
			if(getEntryAt(pluginName, 0).getValueAsBoolean()){
				Plugin plugin = getPluginObject(pluginName);
				if(plugin != null) {
					if(plugin.init()){
						plugin.enable();
					}
				}
			}
		});
	}

	public Plugin getPluginObject(String pluginName){
		for(Plugin plugin : plugins){
			if(plugin.getName().equals(pluginName))
				return plugin;
		}
		return null;
	}

	public void put(Plugin plugin, boolean enabled){
		updateEntry(plugin.getName(), String.valueOf(enabled), 0);
		if(!plugin.needsRestart()){
			if(plugin.init())
				plugin.enable();
		}
	}

	public static void main(String[] args){
		new PluginManager();
	}
}

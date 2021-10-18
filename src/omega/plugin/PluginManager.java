package omega.plugin;
import omega.utils.ChoiceDialog;

import omega.plugin.ui.PluginStore;

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

	public synchronized void uninstallPlugin(PluginStore store, String name){
		int choice = ChoiceDialog.makeChoice(store, "Do You Want to Uninstall This Plugin?", "Yes", "No");
		if(choice == ChoiceDialog.CHOICE1){
			store.setStatus("Deleting " + name + " ...");
			Plugin plugin = getPluginObject(name);
			File file = new File(PLUGINS_DIRECTORY.getAbsolutePath(), getPluginObject(name).getClass().getName() + ".jar");
			if(file.delete()){
				store.setStatus(null);
				plugins.remove(plugin);
				store.refresh();
			}
			else
				store.setStatus("Unable to Delete Plugin : " + name);
		}
	}

	public synchronized boolean isPluginInstalled(String pluginName){
		for(Plugin plx : plugins){
			if(plx.getName().equals(pluginName))
				return true;
		}
		return false;
	}

	public static void main(String[] args){
		new PluginStore(new PluginManager()).setVisible(true);
	}
}

package omega.plugin;
public interface Plugin {
	boolean init();
	boolean enable();
	boolean disable();
	boolean needsRestart();
	String getName();
	String getVersion();
	String getAuthor();
	String getDescription();
	String getSizeInMegaBytes();
}

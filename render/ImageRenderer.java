package render;
import java.io.IOException;

/**
 * Abstract class for image saving.
 * Provides interface for different image formats (TGA, PNG, etc.)
 * 
 * @author KepsyIn
 */
public abstract class ImageRenderer {
	
	protected static final String DEFAULT_FILENAME = "default_image";
	
	/**
	 * Saves image buffer to file.
	 * 
	 * @param filename The filename
	 * @param buffer Image buffer (3 bytes per pixel: Blue, Green, Red)
	 * @param width Image width
	 * @param height Image height
	 * @throws IOException If I/O error occurs
	 */
	public abstract void save(String filename, byte buffer[], int width, int height) throws IOException;
	
	/**
	 * Gets the file extension for this format.
	 * 
	 * @return The file extension
	 */
	protected abstract String getExtension();
	
	/**
	 * Gets the format name for display.
	 * 
	 * @return The format name
	 */
	protected abstract String getFormatName();
}

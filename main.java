import demo.DemoManager;

/**
 * Main entry point for the raytracing application.
 * This class serves as the application launcher and delegates all work to DemoManager.
 * 
 * @author Raytracing Project
 * @version 1.0
 */
public class main {
	
	/**
	 * Main method - entry point of the application.
	 * Parses command line arguments and initiates the raytracing demo.
	 * 
	 * @param args Command line arguments passed to DemoManager.run()
	 */
	public static void main(String[] args) {
		DemoManager.run(args);
	}
}


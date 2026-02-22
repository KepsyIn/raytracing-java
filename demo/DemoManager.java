package demo;

import raytracing.Scene;
import render.RenderTga;

/**
 * Manages raytracing demonstration.
 * Handles arguments, help display, rendering and file saving.
 * 
 * @author KepsyIn
 */
public class DemoManager {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String OUTPUT_FILENAME = "raytracing_scene";
	private static final boolean ANTIALIASING = true;
	private static final int SAMPLES = 4;
	
	/**
	 * Starts the raytracing demonstration.
	 * 
	 * @param args Command line arguments
	 */
	public static void run(String[] args) {
		if (args.length <= 0) {
			printError("missing arguments (type -h for help)");
			return;
		}
		
		if (args[0].equals("-h")) {
			displayHelp();
			return;
		}
		
		try {
			int sceneNumber = Integer.parseInt(args[0]);
			renderScene(sceneNumber);
		} catch (NumberFormatException e) {
			printError("insert a valid scene number or type -h for help: " + e);
		}
	}
	
	/**
	 * Displays help with available scenes.
	 */
	private static void displayHelp() {
		System.out.println("> the program takes one argument <number_of_the_scene> you want to generate.");
		System.out.println(" - there are " + SceneContainer.getTotalScenes() + " available scenes in this raytracing program: ");
		
		for (int i = 1; i <= SceneContainer.getTotalScenes(); i++) {
			System.out.println("\t" + i + " - " + SceneContainer.getSceneDescription(i));
		}
	}
	
	/**
	 * Renders the requested scene.
	 * 
	 * @param sceneNumber The scene number to render
	 */
	private static void renderScene(int sceneNumber) {
		Scene scene = SceneContainer.buildScene1();
		
		switch(sceneNumber) {
			case 1: scene = SceneContainer.buildScene1(); break;
			case 2: scene = SceneContainer.buildScene2(); break;
			case 3: scene = SceneContainer.buildScene3(); break;
			case 4: scene = SceneContainer.buildScene4(); break;
			case 5: scene = SceneContainer.buildScene5(); break;
			default:
				printError("No scene associated with argument: " + sceneNumber);
				return;
		}
		
		try {
			System.out.println("> start raytracing");
			System.out.println(" - start generating the scene");
			
			byte[] sceneBuffer;
			if (ANTIALIASING) {
				System.out.println("  + generating scene with anti-aliasing (" + SAMPLES + " samples)");
				sceneBuffer = scene.draw(WIDTH, HEIGHT, SAMPLES);
			} else {
				sceneBuffer = scene.draw(WIDTH, HEIGHT);
			}
			
			System.out.println(" - finish generating the scene");
			
			System.out.println(" - start generating " + OUTPUT_FILENAME + ".tga file");

			RenderTga.saveTGA(OUTPUT_FILENAME + ".tga", sceneBuffer, WIDTH, HEIGHT);
			System.out.println(" - finish generating " + OUTPUT_FILENAME + ".tga file");
			
			System.out.println("> finish raytracing");
		} catch (Exception e) {
			printError("TGA file not created: " + e);
		}
	}
	
	/**
	 * Displays an error message.
	 * 
	 * @param message The error message
	 */
	private static void printError(String message) {
		System.err.println(message);
	}
}

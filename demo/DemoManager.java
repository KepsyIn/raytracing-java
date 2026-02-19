package demo;

import raytracing.Scene;
import render.RenderTga;

/**
 * Classe responsable de la gestion complète de la démonstration du raytracing.
 * Elle gère les arguments, affiche l'aide, lance le rendu et sauvegarde les fichiers.
 */
public class DemoManager {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String OUTPUT_FILENAME = "raytracing_scene";
	private static final boolean ANTIALIASING = true;
	private static final int SAMPLES = 4;
	
	/**
	 * Lance la démonstration du raytracing en fonction des arguments fournis.
	 * 
	 * @param args Arguments de la ligne de commande
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
	 * Affiche l'aide avec la liste de toutes les scènes disponibles.
	 */
	private static void displayHelp() {
		System.out.println("> the program takes one argument <number_of_the_scene> you want to generate.");
		System.out.println(" - there are " + SceneContainer.getTotalScenes() + " available scenes in this raytracing program: ");
		
		for (int i = 1; i <= SceneContainer.getTotalScenes(); i++) {
			System.out.println("\t" + i + " - " + SceneContainer.getSceneDescription(i));
		}
	}
	
	/**
	 * Génère et rend la scène demandée.
	 * 
	 * @param sceneNumber Le numéro de la scène à créer
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
	 * Affiche un message d'erreur sur la sortie d'erreur standard.
	 * 
	 * @param message Le message d'erreur à afficher
	 */
	private static void printError(String message) {
		System.err.println(message);
	}
}

package render;
import java.io.IOException;

/**
 * Classe abstraite responsable de la sauvegarde d'images.
 * Fournit une interface commune pour les différents formats d'image (TGA, PNG, etc.)
 */
public abstract class ImageRenderer {
	
	protected static final String DEFAULT_FILENAME = "default_image";
	
	/**
	 * Sauvegarde un buffer d'image dans le format spécifié par l'implémentation.
	 * 
	 * @param filename Nom du fichier à créer
	 * @param buffer Buffer contenant l'image. 3 bytes par pixel ordonnés ainsi : Blue, Green, Red
	 * @param width Largeur de l'image
	 * @param height Hauteur de l'image
	 * @throws IOException Si une erreur d'I/O se produit
	 */
	public abstract void save(String filename, byte buffer[], int width, int height) throws IOException;
	
	/**
	 * Retourne l'extension du fichier de ce format.
	 * 
	 * @return L'extension du fichier (ex: "tga", "png")
	 */
	protected abstract String getExtension();
	
	/**
	 * Retourne le nom du format pour l'affichage.
	 * 
	 * @return Le nom du format (ex: "TGA", "PNG")
	 */
	protected abstract String getFormatName();
}

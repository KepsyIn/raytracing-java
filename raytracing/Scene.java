package raytracing;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import utils.Vec3f;

/**
 * Represents the raytracing scene containing all models and light sources.
 * The Scene class is responsible for ray casting, color computation, and rendering.
 * It uses the Phong illumination model to compute lighting for each pixel.
 * 
 * @author KepsyIn
 * @version 1.0
 */
public class Scene {
	
	private List<Model> modelList;
	
	private List<LightSource> lightSources;
	
	private Vec3f viewerPosition;
	
	public static final Vec3f DEFAULT_VIEWER_POS = new Vec3f(0,0,0);
	
	public static final int DEFAULT_SIZE = 100;
	public static final int DEFAULT_DISTANCE = 1;
	
	public static final double EPSILON = 1E-4;
	
	private static final double LAMBDA_MAX = Double.MAX_VALUE;
	
	private static final LightSource DEFAULT_LIGHT = new LightSource(); 

	/**
	 * Creates an empty scene with default viewer position at origin.
	 * Light sources and models must be added separately.
	 */
	public Scene() {
		viewerPosition = DEFAULT_VIEWER_POS;
		
		modelList = new ArrayList<>();
		lightSources = new ArrayList<>();
		
	}
	
	/**
	 * Creates a scene with a light source and models.
	 * 
	 * @param lightSource The primary light source for the scene
	 * @param model Variable number of Model objects to include in the scene
	 */
	public Scene( LightSource lightSource , Model... model) {
		this();
		for(Model m : model) {
			modelList.add(m);
		}
	}
	
	/**
	 * Computes the color of a pixel by tracing a ray through the scene.
	 * Uses recursive ray tracing with reflection bounces to compute final pixel color.
	 * Applies the Phong illumination model with ambient, diffuse, and specular components.
	 * Implements shadow casting and reflection recursion up to specified level.
	 * 
	 * @param rayStart The starting point of the ray (camera position)
	 * @param rayDirection The direction vector of the ray
	 * @param niv The recursion level for reflections (decrements with each bounce)
	 * @return A float array [R, G, B] with values in range [0, 1]
	 */
	public float[] findColor(Vec3f rayStart, Vec3f rayDirection, int niv) {
		
	    double lambdaMin = LAMBDA_MAX;
	    Model objmin = null;
	    

	    // Trouver l'objet d'intersection le plus proche
	    for (Model m : modelList) {
	        Vec3f tempStart = new Vec3f(rayStart);
	        Vec3f tempDirection = new Vec3f(rayDirection);
	        double lambda = m.getIntersection(tempStart, tempDirection);

	        if (lambda < lambdaMin && lambda > EPSILON ) {
	            lambdaMin = lambda;
	            objmin = m;
	        }
	    }

	    // Si un objet d'intersection est trouvé
	    if (objmin != null) {
	    	
	    	Vec3f c = new Vec3f(); // Initialiser à 0
	    	
	        Vec3f P = new Vec3f(rayStart).add(new Vec3f(rayDirection).scale((float) lambdaMin));
	        Vec3f normal = objmin.getNormal(P);

	        // Vérifier chaque source de lumière
	        for (LightSource src : lightSources) {
	        	
	            Vec3f lightDir = new Vec3f(src.position).sub(P);
	            boolean visible = true;
	            
	            Vec3f shadowRayStart = new Vec3f(P);
                Vec3f shadowRayDir = new Vec3f(lightDir);
                
	            // Vérifier les ombres pour chaque objet
	            for (Model m : modelList) {
	            	
                    double lambdaOmbrage = m.getIntersection(shadowRayStart, shadowRayDir);

                    if (lambdaOmbrage > EPSILON && lambdaOmbrage < 1 ) {
                        visible = false;
                    }
	                
	            }

	            if (visible) {
	            	
	                Vec3f nlightDir = new Vec3f(lightDir).normalize();
	                
	                float nDoth = Math.max(new Vec3f(normal).dotProduct(nlightDir), 0);
	                
	                Vec3f diffuseColor = new Vec3f(src.color).scale(objmin.getColor().x, objmin.getColor().y, objmin.getColor().z).scale(nDoth);
	                
	                c = new Vec3f(c).add(diffuseColor);
	                
	                Vec3f specularColor = new Vec3f(src.specular).scale(objmin.getSpecular().x, objmin.getSpecular().y, objmin.getSpecular().z).scale((float) Math.pow(nDoth, objmin.getShininess()));
	                
	                c = new Vec3f(c).add(specularColor);
	                
	            }
	        }
	        
	        float reflexionCoeff = objmin.getReflexionCoeff();
	        
	        if (niv > 0) {
	            Vec3f reflectionDir = new Vec3f(rayDirection).sub(new Vec3f(normal).scale(2 * new Vec3f(normal).dotProduct(rayDirection))).normalize();
	            Vec3f newRayStart = new Vec3f(P);
	            float[] reflectedColor = findColor(newRayStart, reflectionDir, niv - 1);
	            c = new Vec3f(c).add(new Vec3f(reflectedColor[0], reflectedColor[1], reflectedColor[2]).scale(reflexionCoeff));
	        }
	        
	        correctColor(c);
	        
	        return new float[]{c.x, c.y, c.z};
	    }
	    
		return new float[] {0,0,0};
		
	}
	
	/**
	 * Applies anti-aliasing to an image by averaging samples per pixel.
	 * (Currently not fully implemented in this version)
	 * 
	 * @param image The original image buffer
	 * @param width The width of the image
	 * @param height The height of the image
	 * @param sample The number of samples per dimension (sample x sample total samples per pixel)
	 * @return The anti-aliased image buffer
	 */
	public byte[] applyAntiAliasing(byte[] image, int width, int height, int sample) {
	    byte[] newImage = new byte[image.length];

	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	            float[] avgColor = new float[3];
	            int count = 0;

	            

	            int index = 3 * (y * width + x);
	            newImage[index] = (byte) (avgColor[0] / count);
	            newImage[index + 1] = (byte) (avgColor[1] / count);
	            newImage[index + 2] = (byte) (avgColor[2] / count);
	        }
	    }

	    return newImage;
	}
	
	public byte[] draw( int width , int height ) {
		
		int D = DEFAULT_DISTANCE;
		
		byte buffer[] = new byte[3*width*height];
		
		for( float ye = 0 ; ye < height; ye++ ) {
			for( float xe = 0 ; xe < width; xe++ ) {
				int index = (int) (3*((ye*width)+xe));
				
				float x = ( xe - ( width / 2 ) ) / width ;
				float y = ( ye - ( height / 2 ) ) / width ;
				
				Vec3f primaryRayDirection = new Vec3f(x,y,-D);
				
				float[] couleur = this.findColor(viewerPosition, primaryRayDirection,5);
				
				buffer[index] = (byte) (couleur[0] * 255);
				buffer[index+1] = (byte) (couleur[1] * 255);
				buffer[index+2] = (byte) (couleur[2] * 255);
				
			}
		}
		
		return buffer;	
	}
	
	/**
	 * Renders the scene to a byte buffer with anti-aliasing (supersampling).
	 * Multiple rays are cast per pixel in a grid pattern to reduce aliasing artifacts.
	 * 
	 * @param width The width of the output image in pixels
	 * @param height The height of the output image in pixels
	 * @param samples The number of samples per dimension (samples x samples total samples per pixel)
	 * @return A byte buffer containing RGB values (3 bytes per pixel)
	 */
	public byte[] draw(int width, int height, int samples) {
	    int D = DEFAULT_DISTANCE;
	    byte[] buffer = new byte[3 * width * height];

	    for (int ye = 0; ye < height; ye++) {
	        for (int xe = 0; xe < width; xe++) {
	            int index = 3 * ((ye * width) + xe);
	            float[] color = new float[]{0, 0, 0};

	            for (int i = 0; i < samples; i++) {
	                for (int j = 0; j < samples; j++) {
	                    float dx = (i + 0.5f) / samples - 0.5f;
	                    float dy = (j + 0.5f) / samples - 0.5f;

	                    float nx = (xe + dx - width / 2) / (float) width;
	                    float ny = (ye + dy - height / 2) / (float) width;

	                    Vec3f primaryRayDirection = new Vec3f(nx, ny, -D);

	                    float[] sampleColor = findColor(viewerPosition, primaryRayDirection, 5);

	                    color[0] += sampleColor[0];
	                    color[1] += sampleColor[1];
	                    color[2] += sampleColor[2];
	                }
	            }

	            float coeff = 1.0f / (samples * samples);
	            color[0] *= coeff;
	            color[1] *= coeff;
	            color[2] *= coeff;

	            buffer[index] = (byte) (Math.min(color[0] * 255, 255));
	            buffer[index + 1] = (byte) (Math.min(color[1] * 255, 255));
	            buffer[index + 2] = (byte) (Math.min(color[2] * 255, 255));
	        }
	    }

	    return buffer;
	}
	
	/**
	 * Renders the scene using default resolution (100x100 pixels).
	 * 
	 * @return A byte buffer containing the rendered image
	 */
	public byte[] draw() {
		return this.draw(DEFAULT_SIZE, DEFAULT_SIZE);
	}

	/**
	 * Gets the list of all models in this scene.
	 * 
	 * @return A List of Model objects
	 */
	public List<Model> getModelList() {
		return modelList;
	}

	/**
	 * Sets the list of all models in this scene.
	 * 
	 * @param modelList A List of Model objects to set
	 */
	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * Adds a model to the scene.
	 * 
	 * @param m The Model object to add
	 */
	public void addModel( Model m ) {
		this.modelList.add(m);
	}
	
	/**
	 * Adds a light source to the scene.
	 * Multiple light sources can contribute to the scene's illumination.
	 * 
	 * @param l The LightSource object to add
	 */
	public void addLightSource( LightSource l ) {
		this.lightSources.add(l);
	}
	
	/**
	 * Gets the list of all light sources in the scene.
	 * 
	 * @return A List of LightSource objects
	 */
	public List<LightSource> getLightSources(){
		return this.lightSources;
	}

	/**
	 * Gets the viewer/camera position in the scene.
	 * This is the point from which rays are cast into the scene.
	 * 
	 * @return The Vec3f representing the viewer position
	 */
	public Vec3f getViewerPosition() {
		return viewerPosition;
	}
	
	/**
	 * Clamps color component values to the valid range [0, 1].
	 * Ensures all RGB values are within valid bounds after computation.
	 * 
	 * @param that The Vec3f color vector to clamp
	 */
	private void correctColor(Vec3f that) {
	    that.x = Math.max(0, Math.min(1, that.x));
	    that.y = Math.max(0, Math.min(1, that.y));
	    that.z = Math.max(0, Math.min(1, that.z));
	}
	
	
}

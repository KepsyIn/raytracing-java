package raytracing;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import utils.Vec3;

/**
 * Represents the raytracing scene.
 * Manages models, light sources and ray casting.
 * 
 * @author KepsyIn
 */
public class Scene {
	
	private List<Model> modelList;
	
	private List<LightSource> lightSources;
	
	private Vec3 viewerPosition;
	
	public static final Vec3 DEFAULT_VIEWER_POS = new Vec3(0,0,0);
	
	public static final int DEFAULT_SIZE = 100;
	public static final int DEFAULT_DISTANCE = 1;
	
	public static final double EPSILON = 1E-4;
	
	private static final double LAMBDA_MAX = Double.MAX_VALUE;
	
	private static final LightSource DEFAULT_LIGHT = new LightSource(); 

	/**
	 * Creates an empty scene.
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
	 * @param lightSource The primary light source
	 * @param model Models to include
	 */
	public Scene( LightSource lightSource , Model... model) {
		this();
		for(Model m : model) {
			modelList.add(m);
		}
	}
	
	/**
	 * Computes the color of a pixel by ray tracing.
	 * Uses recursive reflection and Phong illumination model.
	 * 
	 * @param rayStart Ray starting point
	 * @param rayDirection Ray direction
	 * @param niv Recursion level for reflections
	 * @return [R, G, B] color values in range [0, 1]
	 */
	public float[] findColor(Vec3 rayStart, Vec3 rayDirection, int niv) {
		
	    double lambdaMin = LAMBDA_MAX;
	    Model objmin = null;
	    

	    // Trouver l'objet d'intersection le plus proche
	    for (Model m : modelList) {
	        Vec3 tempStart = new Vec3(rayStart);
	        Vec3 tempDirection = new Vec3(rayDirection);
	        double lambda = m.getIntersection(tempStart, tempDirection);

	        if (lambda < lambdaMin && lambda > EPSILON ) {
	            lambdaMin = lambda;
	            objmin = m;
	        }
	    }

	    // Si un objet d'intersection est trouvé
	    if (objmin != null) {
	    	
	    	Vec3 c = new Vec3(); // Initialiser à 0
	    	
	        Vec3 P = new Vec3(rayStart).add(new Vec3(rayDirection).scale((float) lambdaMin));
	        Vec3 normal = objmin.getNormal(P);

	        // Vérifier chaque source de lumière
	        for (LightSource src : lightSources) {
	        	
	            Vec3 lightDir = new Vec3(src.position).sub(P);
	            boolean visible = true;
	            
	            Vec3 shadowRayStart = new Vec3(P);
                Vec3 shadowRayDir = new Vec3(lightDir);
                
	            // Vérifier les ombres pour chaque objet
	            for (Model m : modelList) {
	            	
                    double lambdaOmbrage = m.getIntersection(shadowRayStart, shadowRayDir);

                    if (lambdaOmbrage > EPSILON && lambdaOmbrage < 1 ) {
                        visible = false;
                    }
	                
	            }

	            if (visible) {
	            	
	                Vec3 nlightDir = new Vec3(lightDir).normalize();
	                
	                float nDoth = Math.max(new Vec3(normal).dotProduct(nlightDir), 0);
	                
	                Vec3 diffuseColor = new Vec3(src.color).scale(objmin.getColor().x, objmin.getColor().y, objmin.getColor().z).scale(nDoth);
	                
	                c = new Vec3(c).add(diffuseColor);
	                
	                Vec3 specularColor = new Vec3(src.specular).scale(objmin.getSpecular().x, objmin.getSpecular().y, objmin.getSpecular().z).scale((float) Math.pow(nDoth, objmin.getShininess()));
	                
	                c = new Vec3(c).add(specularColor);
	                
	            }
	        }
	        
	        float reflexionCoeff = objmin.getReflexionCoeff();
	        
	        if (niv > 0) {
	            Vec3 reflectionDir = new Vec3(rayDirection).sub(new Vec3(normal).scale(2 * new Vec3(normal).dotProduct(rayDirection))).normalize();
	            Vec3 newRayStart = new Vec3(P);
	            float[] reflectedColor = findColor(newRayStart, reflectionDir, niv - 1);
	            c = new Vec3(c).add(new Vec3(reflectedColor[0], reflectedColor[1], reflectedColor[2]).scale(reflexionCoeff));
	        }
	        
	        correctColor(c);
	        
	        return new float[]{c.x, c.y, c.z};
	    }
	    
		return new float[] {0,0,0};
		
	}
	
	/**
	 * Applies anti-aliasing by averaging samples per pixel.
	 * 
	 * @param image Original image buffer
	 * @param width Image width
	 * @param height Image height
	 * @param sample Samples per dimension
	 * @return Anti-aliased image buffer
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
				
				Vec3 primaryRayDirection = new Vec3(x,y,-D);
				
				float[] couleur = this.findColor(viewerPosition, primaryRayDirection,5);
				
				buffer[index] = (byte) (couleur[0] * 255);
				buffer[index+1] = (byte) (couleur[1] * 255);
				buffer[index+2] = (byte) (couleur[2] * 255);
				
			}
		}
		
		return buffer;	
	}
	
	/**
	 * Renders the scene with anti-aliasing.
	 * 
	 * @param width Image width
	 * @param height Image height
	 * @param samples Samples per dimension
	 * @return Rendered image buffer
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

	                    Vec3 primaryRayDirection = new Vec3(nx, ny, -D);

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
	 * Renders the scene with default resolution.
	 * 
	 * @return Rendered image buffer
	 */
	public byte[] draw() {
		return this.draw(DEFAULT_SIZE, DEFAULT_SIZE);
	}

	/**
	 * Gets the model list.
	 * 
	 * @return List of models
	 */
	public List<Model> getModelList() {
		return modelList;
	}

	/**
	 * Sets the model list.
	 * 
	 * @param modelList List of models
	 */
	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}
	
	/**
	 * Adds a model to the scene.
	 * 
	 * @param m The model to add
	 */
	public void addModel( Model m ) {
		this.modelList.add(m);
	}
	
	/**
	 * Adds a light source to the scene.
	 * 
	 * @param l The light source to add
	 */
	public void addLightSource( LightSource l ) {
		this.lightSources.add(l);
	}
	
	/**
	 * Gets the light sources.
	 * 
	 * @return List of light sources
	 */
	public List<LightSource> getLightSources(){
		return this.lightSources;
	}

	/**
	 * Gets the viewer position.
	 * 
	 * @return The viewer position
	 */
	public Vec3 getViewerPosition() {
		return viewerPosition;
	}
	
	/**
	 * Clamps color values to [0, 1].
	 * 
	 * @param that The color vector to clamp
	 */
	private void correctColor(Vec3 that) {
	    that.x = Math.max(0, Math.min(1, that.x));
	    that.y = Math.max(0, Math.min(1, that.y));
	    that.z = Math.max(0, Math.min(1, that.z));
	}
	
	
}

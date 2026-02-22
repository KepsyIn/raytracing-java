package raytracing;

import utils.Vec3f;

/**
 * Represents a point light source in the raytracing scene.
 * Uses Phong lighting model with ambient, diffuse, and specular components.
 * 
 * @author KepsyIn
 */
public class LightSource {
	
	public Vec3f ambient;
	public Vec3f position;
	public Vec3f diffuse;
	public Vec3f specular;
	public Vec3f color;
	
	public static final Vec3f DEFAULT_COLOR = new Vec3f(1,1,1);
	public static final Vec3f DEFAULT_AMBIENT = new Vec3f(0.2f,0.2f,0.2f);
	public static final Vec3f DEFAULT_POSITION = new Vec3f(0,0,0);
	public static final Vec3f DEFAULT_DIFFUSE = new Vec3f(0.8f,0.8f,0.8f);
	public static final Vec3f DEFAULT_SPECULAR = new Vec3f(1,1,1);
		
	/**
	 * Creates a light with all components customized.
	 * 
	 * @param color The light color
	 * @param position The 3D position
	 * @param ambient Ambient component
	 * @param diffuse Diffuse component
	 * @param specular Specular component
	 */
	public LightSource(Vec3f color ,Vec3f position, Vec3f ambient, Vec3f diffuse, Vec3f specular) {
		this.color = color;
		this.ambient = ambient;
		this.position = position;
		this.diffuse = diffuse;
		this.specular = specular;
	}
	
	/**
	 * Creates a light with default components.
	 */
	public LightSource() {
		this(DEFAULT_COLOR,DEFAULT_POSITION,DEFAULT_AMBIENT,DEFAULT_DIFFUSE,DEFAULT_SPECULAR);
	}
	
	/**
	 * Creates a light with color and position.
	 * Uses default ambient, diffuse, and specular components.
	 * 
	 * @param color The light color
	 * @param position The 3D position
	 */
	public LightSource(Vec3f color , Vec3f position ) {
		this(color,position,DEFAULT_AMBIENT,DEFAULT_DIFFUSE,DEFAULT_SPECULAR);
	}

	/**
	 * Gets the ambient light component.
	 * 
	 * @return The ambient light color
	 */
	public Vec3f getAmbient() {
		return ambient;
	}

	/**
	 * Sets the ambient light component.
	 * 
	 * @param ambient The ambient light color
	 */
	public void setAmbient(Vec3f ambient) {
		this.ambient = ambient;
	}

	/**
	 * Gets the position.
	 * 
	 * @return The 3D position
	 */
	public Vec3f getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position The 3D position
	 */
	public void setPosition(Vec3f position) {
		this.position = position;
	}

	/**
	 * Gets the diffuse light component.
	 * 
	 * @return The diffuse light color vector
	 */
	public Vec3f getDiffuse() {
		return diffuse;
	}

	/**
	 * Sets the diffuse light component.
	 * 
	 * @param diffuse The diffuse light color vector to set
	 */
	public void setDiffuse(Vec3f diffuse) {
		this.diffuse = diffuse;
	}

	/**
	 * Gets the specular light component.
	 * Specular light creates bright highlights and reflections on shiny surfaces.
	 * 
	 * @return The specular light color vector
	 */
	public Vec3f getSpecular() {
		return specular;
	}

	/**
	 * Sets the specular light component.
	 * 
	 * @param specular The specular light color vector to set
	 */
	public void setSpecular(Vec3f specular) {
		this.specular = specular;
	}

	/**
	 * Gets the primary color of this light source.
	 * 
	 * @return The color vector of the light
	 */
	public Vec3f getColor() {
		return color;
	}

	/**
	 * Sets the primary color of this light source.
	 * 
	 * @param color The color vector to set
	 */
	public void setColor(Vec3f color) {
		this.color = color;
	}
}

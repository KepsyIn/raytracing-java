package model;

import utils.Vec3f;

/**
 * Abstract base class for 3D models in the raytracing scene.
 * 
 * @author KepsyIn
 */
public abstract class Model {
	
	public Vec3f color;
	public Vec3f specular;
	public double shininess;
	
	public float reflexionCoeff = 0;
	
	public static final Vec3f DEFAULT_SPECULAR = new Vec3f(1,1,1);
	
	public static final Vec3f DEFAULT_COLOR = new Vec3f(1,1,1); // white
	
	public Model( Vec3f color ) {
		this.color = color;
		this.specular = DEFAULT_SPECULAR;
		this.shininess = 100;
	}
	
	public Model() {
		this(DEFAULT_COLOR);
	}
	
	/**
	 * Computes the intersection of a ray with this model.
	 * @param P Ray starting point
	 * @param v Ray direction
	 * @return Intersection distance lambda
	 */
	public abstract double getIntersection( Vec3f P , Vec3f v );
	
	public abstract Vec3f getNormal(Vec3f P);
	
	/**
	 * Gets the color.
	 * 
	 * @return The RGB color
	 */
	public Vec3f getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color The RGB color
	 */
	public void setColor(Vec3f color) {
		this.color = color;
	}

	/**
	 * Gets the specular reflection.
	 * 
	 * @return The specular reflection color
	 */
	public Vec3f getSpecular() {
		return specular;
	}

	/**
	 * Sets the specular reflection.
	 * 
	 * @param specular The specular reflection color
	 */
	public void setSpecular(Vec3f specular) {
		this.specular = specular;
	}

	/**
	 * Gets the shininess.
	 * 
	 * @return The shininess value
	 */
	public double getShininess() {
		return this.shininess;
	}

	/**
	 * Gets the reflection coefficient.
	 * 
	 * @return The reflection coefficient
	 */
	public float getReflexionCoeff() {
		return reflexionCoeff;
	}

	/**
	 * Sets the reflection coefficient of this model.
	 * Values should range from 0 (no reflection) to 1 (perfect reflection).
	 * 
	 * @param reflexionCoeff The reflection coefficient to set
	 */
	public void setReflexionCoeff(float reflexionCoeff) {
		this.reflexionCoeff = reflexionCoeff;
	}

	/**
	 * Sets the shininess/specular exponent of this model.
	 * Higher values create a sharper, more reflective surface.
	 * 
	 * @param shininess The shininess value to set
	 */
	public void setShininess(double shininess) {
		this.shininess = shininess;
	}
	
	
	

	
	
	
	
}

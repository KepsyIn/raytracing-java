package model;

import utils.Vec3;

/**
 * Abstract base class for 3D models in the raytracing scene.
 * 
 * @author KepsyIn
 */
public abstract class Model {
	
	public Vec3 color;
	public Vec3 specular;
	public double shininess;
	
	public float reflexionCoeff = 0;
	
	public static final Vec3 DEFAULT_SPECULAR = new Vec3(1,1,1);
	
	public static final Vec3 DEFAULT_COLOR = new Vec3(1,1,1); // white
	
	public Model( Vec3 color ) {
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
	public abstract double getIntersection( Vec3 P , Vec3 v );
	
	public abstract Vec3 getNormal(Vec3 P);
	
	/**
	 * Gets the color.
	 * 
	 * @return The RGB color
	 */
	public Vec3 getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 * 
	 * @param color The RGB color
	 */
	public void setColor(Vec3 color) {
		this.color = color;
	}

	/**
	 * Gets the specular reflection.
	 * 
	 * @return The specular reflection color
	 */
	public Vec3 getSpecular() {
		return specular;
	}

	/**
	 * Sets the specular reflection.
	 * 
	 * @param specular The specular reflection color
	 */
	public void setSpecular(Vec3 specular) {
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

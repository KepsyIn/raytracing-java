package model;

import utils.Vec3f;

/**
 * Abstract base class representing a 3D model/shape in the raytracing scene.
 * All renderable objects (spheres, planes, etc.) must extend this class.
 * Provides common properties like color, specular reflection, and shininess.
 * 
 * @author KepsyIn
 * @version 1.0
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
	 * function who compute the intersection of the model and the ray
	 * @param P starting point
	 * @param v direction
	 * @return the lambda result
	 */
	public abstract double getIntersection( Vec3f P , Vec3f v );
	
	public abstract Vec3f getNormal(Vec3f P);
	
	/**
	 * Gets the color of this model.
	 * 
	 * @return The Vec3f representing the RGB color
	 */
	public Vec3f getColor() {
		return color;
	}

	/**
	 * Sets the color of this model.
	 * 
	 * @param color The Vec3f representing the RGB color to set
	 */
	public void setColor(Vec3f color) {
		this.color = color;
	}

	/**
	 * Gets the specular reflection color of this model.
	 * 
	 * @return The Vec3f representing the specular reflection properties
	 */
	public Vec3f getSpecular() {
		return specular;
	}

	/**
	 * Sets the specular reflection color of this model.
	 * 
	 * @param specular The Vec3f representing the specular reflection properties
	 */
	public void setSpecular(Vec3f specular) {
		this.specular = specular;
	}

	/**
	 * Gets the shininess/specular exponent of this model.
	 * Higher values create a sharper, more reflective surface.
	 * 
	 * @return The shininess value
	 */
	public double getShininess() {
		return this.shininess;
	}

	/**
	 * Gets the reflection coefficient of this model.
	 * Values range from 0 (no reflection) to 1 (perfect reflection).
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

package model;

import utils.Vec3f;

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
	
	public Vec3f getColor() {
		return color;
	}

	public void setColor(Vec3f color) {
		this.color = color;
	}

	public Vec3f getSpecular() {
		return specular;
	}

	public void setSpecular(Vec3f specular) {
		this.specular = specular;
	}

	public double getShininess() {
		return this.shininess;
	}

	public float getReflexionCoeff() {
		return reflexionCoeff;
	}

	public void setReflexionCoeff(float reflexionCoeff) {
		this.reflexionCoeff = reflexionCoeff;
	}

	public void setShininess(double shininess) {
		this.shininess = shininess;
	}
	
	
	

	
	
	
	
}

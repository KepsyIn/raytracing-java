package model;

import utils.Vec3f;

/**
 * Represents a sphere (ball) in the 3D raytracing scene.
 * A sphere is defined by its radius and center point.
 * The sphere equation is: |P - center|² = radius²
 * 
 * @author KepsyIn
 * @version 1.0
 */
public class Sphere extends Model{
	
	private double rayon;
	private Vec3f center;
	
	/**
	 * Creates a new Sphere with the specified radius and center position.
	 * 
	 * @param rayon The radius of the sphere
	 * @param center The center position of the sphere
	 */
	public Sphere( double rayon , Vec3f center ) {
		super();
		this.center = center;
		this.rayon = rayon;
	}

	/**
	 * Computes the intersection of a ray with this sphere.
	 * Uses the quadratic formula: ax² + bx + c = 0
	 * where a = |v|², b = 2(v·CP), c = |CP|² - r²
	 * Returns the closest positive lambda value.
	 * 
	 * @param P The starting point of the ray
	 * @param v The direction vector of the ray
	 * @return The closest positive intersection distance lambda, or 0 if no intersection
	 */
	@Override
	public double getIntersection(Vec3f P, Vec3f v) {
		
		double alpha = new Vec3f(v).dotProduct(v);
		Vec3f CP = new Vec3f().setSub(P, center);
		
		double beta = v.dotProduct(CP);
		
		
		double gamma = CP.dotProduct(CP) - ( rayon * rayon );
		double delta = ( beta * beta ) - ( alpha * gamma ) ;
		
		if( delta > 0 ) { // pas d'intersection
			
			double lambda1 = ( -beta - Math.sqrt(delta) ) / alpha;
			double lambda2 = ( -beta + Math.sqrt(delta) ) / alpha;
			
			if( lambda1 > 0 ) { 
				return lambda1; // lambda1 < lambda2 
			} else if( lambda1 < 0 && lambda2 > 0 ) {
				return lambda2;
			} 	
		}
		
		return 0;
	}

	/**
	 * Gets the radius of this sphere.
	 * 
	 * @return The radius value
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Sets the radius of this sphere.
	 * 
	 * @param rayon The new radius value
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

	/**
	 * Gets the center position of this sphere.
	 * 
	 * @return The Vec3f representing the center coordinates
	 */
	public Vec3f getCenter() {
		return center;
	}

	/**
	 * Sets the center position of this sphere.
	 * 
	 * @param center The Vec3f representing the new center coordinates
	 */
	public void setCenter(Vec3f center) {
		this.center = center;
	}
	
	/**
	 * Computes the normal vector to the sphere surface at a given point.
	 * The normal at a point on a sphere points radially outward from the center.
	 * Formula: normal = (P - center) / |P - center|
	 * 
	 * @param P A point on the sphere surface
	 * @return The normalized normal vector at point P
	 */
	@Override	
	public Vec3f getNormal(Vec3f P) {
		Vec3f normal = new Vec3f(P).sub(this.center);
		return normal.normalize(); 
	}
	
}

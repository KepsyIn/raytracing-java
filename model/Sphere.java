package model;

import utils.Vec3f;

/**
 * Represents a sphere in 3D raytracing scene.
 * Defined by radius and center point.
 * 
 * @author KepsyIn
 */
public class Sphere extends Model{
	
	private double rayon;
	private Vec3f center;
	
	/**
	 * Creates a sphere with radius and center.
	 * 
	 * @param rayon The radius
	 * @param center The center position
	 */
	public Sphere( double rayon , Vec3f center ) {
		super();
		this.center = center;
		this.rayon = rayon;
	}

	/**
	 * Computes ray-sphere intersection.
	 * 
	 * @param P Ray starting point
	 * @param v Ray direction
	 * @return Closest positive intersection distance or 0 if none
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
	 * Gets the radius.
	 * 
	 * @return The radius
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Sets the radius.
	 * 
	 * @param rayon The new radius
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

	/**
	 * Gets the center position.
	 * 
	 * @return The center coordinates
	 */
	public Vec3f getCenter() {
		return center;
	}

	/**
	 * Sets the center position.
	 * 
	 * @param center The new center coordinates
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

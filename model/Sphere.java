package model;

import utils.Vec3;

/**
 * Represents a sphere in 3D raytracing scene.
 * Defined by radius and center point.
 * 
 * @author KepsyIn
 */
public class Sphere extends Model{
	
	private double rayon;
	private Vec3 center;
	
	/**
	 * Creates a sphere with radius and center.
	 * 
	 * @param rayon The radius
	 * @param center The center position
	 */
	public Sphere( double rayon , Vec3 center ) {
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
	public double getIntersection(Vec3 P, Vec3 v) {
		
		double alpha = new Vec3(v).dotProduct(v);
		Vec3 CP = new Vec3().setSub(P, center);
		
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
	public Vec3 getCenter() {
		return center;
	}

	/**
	 * Sets the center position.
	 * 
	 * @param center The new center coordinates
	 */
	public void setCenter(Vec3 center) {
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
	public Vec3 getNormal(Vec3 P) {
		Vec3 normal = new Vec3(P).sub(this.center);
		return normal.normalize(); 
	}
	
}

package model;

import utils.Vec3;

/**
 * Represents a planar surface in 3D raytracing scene.
 * Defined by a point and normal vector.
 * 
 * @author KepsyIn
 */
public class Plan extends Model {
	
	public Vec3 normal;

	public Vec3 point;
	
	/**
	 * Creates a plane with a point and normal vector.
	 * 
	 * @param A A point on the plane
	 * @param normal The normal vector
	 */
	public Plan(Vec3 A , Vec3 normal ) {
		super();
		this.point = A;
		this.normal = normal;
	}

	/**
	 * Computes ray-plane intersection.
	 * 
	 * @param P Ray starting point
	 * @param v Ray direction
	 * @return Intersection distance or -1 if no intersection
	 */
	@Override
	public double getIntersection(Vec3 P, Vec3 v) {
        
        double AdotN = new Vec3(point).dotProduct(normal);
        double PdotN = new Vec3(P).dotProduct(normal);
        
        double VdotN = new Vec3(v).dotProduct(normal);
        
        if( VdotN < 0 ) {
        	double lambda = (AdotN - PdotN) / VdotN;
        	return lambda;
        }
        
        return -1;
        
        
	}
	
	/**
	 * Gets the normal vector.
	 * 
	 * @return The normal vector
	 */
	public Vec3 getNormal() {
		return normal;
	}
	
	/**
	 * Gets the normal vector at a point.
	 * Normal is constant for planes.
	 * 
	 * @param P A point (unused)
	 * @return The normal vector
	 */
	public Vec3 getNormal(Vec3 P ) {
		return normal;
	}

	/**
	 * Sets the normal vector.
	 * 
	 * @param normal The new normal vector
	 */
	public void setNormal(Vec3 normal) {
		this.normal = normal;
	}

	/**
	 * Gets a point on the plane.
	 * 
	 * @return A point on the plane
	 */
	public Vec3 getPoint() {
		return point;
	}

	/**
	 * Sets the point defining the plane.
	 * 
	 * @param point A point on the plane
	 */
	public void setPoint(Vec3 point) {
		this.point = point;
	}

}

package model;

import utils.Vec3f;

/**
 * Represents a planar surface in 3D raytracing scene.
 * Defined by a point and normal vector.
 * 
 * @author KepsyIn
 */
public class Plan extends Model {
	
	public Vec3f normal;

	public Vec3f point;
	
	/**
	 * Creates a plane with a point and normal vector.
	 * 
	 * @param A A point on the plane
	 * @param normal The normal vector
	 */
	public Plan(Vec3f A , Vec3f normal ) {
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
	public double getIntersection(Vec3f P, Vec3f v) {
        
        double AdotN = new Vec3f(point).dotProduct(normal);
        double PdotN = new Vec3f(P).dotProduct(normal);
        
        double VdotN = new Vec3f(v).dotProduct(normal);
        
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
	public Vec3f getNormal() {
		return normal;
	}
	
	/**
	 * Gets the normal vector at a point.
	 * Normal is constant for planes.
	 * 
	 * @param P A point (unused)
	 * @return The normal vector
	 */
	public Vec3f getNormal(Vec3f P ) {
		return normal;
	}

	/**
	 * Sets the normal vector.
	 * 
	 * @param normal The new normal vector
	 */
	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}

	/**
	 * Gets a point on the plane.
	 * 
	 * @return A point on the plane
	 */
	public Vec3f getPoint() {
		return point;
	}

	/**
	 * Sets the point defining the plane.
	 * 
	 * @param point A point on the plane
	 */
	public void setPoint(Vec3f point) {
		this.point = point;
	}

}

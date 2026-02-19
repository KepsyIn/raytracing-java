package model;

import utils.Vec3f;

/**
 * Represents a planar surface (infinite plane) in the 3D raytracing scene.
 * A plane is defined by a point and a normal vector.
 * Planes are used to create walls, floors, and other flat surfaces.
 * 
 * @author KepsyIn
 * @version 1.0
 */
public class Plan extends Model {
	
	public Vec3f normal;

	public Vec3f point;
	
	/**
	 * Creates a new Plan with the specified point and normal vector.
	 * 
	 * @param A A point on the plane
	 * @param normal The normal vector to the plane
	 */
	public Plan(Vec3f A , Vec3f normal ) {
		super();
		this.point = A;
		this.normal = normal;
	}

	/**
	 * Computes the intersection of a ray with this plane.
	 * Uses the mathematical formula: lambda = (A·N - P·N) / (V·N)
	 * where A is a point on the plane, P is ray start, V is ray direction, and N is the normal.
	 * 
	 * @param P The starting point of the ray
	 * @param v The direction vector of the ray
	 * @return The intersection distance (lambda) if positive, -1 if no intersection
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
	 * Gets the normal vector of this plane.
	 * The normal is constant across all points on an infinite plane.
	 * 
	 * @return The normal vector of the plane
	 */
	public Vec3f getNormal() {
		return normal;
	}
	
	/**
	 * Gets the normal vector of this plane at a specific point.
	 * For a plane, the normal is constant everywhere, so the point parameter is ignored.
	 * 
	 * @param P A point (not used for planes since normal is constant)
	 * @return The normal vector of the plane
	 */
	public Vec3f getNormal(Vec3f P ) {
		return normal;
	}

	/**
	 * Sets the normal vector of this plane.
	 * 
	 * @param normal The new normal vector for the plane
	 */
	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}

	/**
	 * Gets a point that lies on this plane.
	 * 
	 * @return A Vec3f representing a point on the plane
	 */
	public Vec3f getPoint() {
		return point;
	}

	/**
	 * Sets the point that defines this plane.
	 * 
	 * @param point A point on the plane
	 */
	public void setPoint(Vec3f point) {
		this.point = point;
	}

}

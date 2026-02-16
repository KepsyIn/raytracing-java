package model;

import utils.Vec3f;

public class Plan extends Model {
	
	public Vec3f normal;

	public Vec3f point;
	
	public Plan(Vec3f A , Vec3f normal ) {
		super();
		this.point = A;
		this.normal = normal;
	}

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
	
	public Vec3f getNormal() {
		return normal;
	}
	
	public Vec3f getNormal(Vec3f P ) {
		return normal;
	}

	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}

	public Vec3f getPoint() {
		return point;
	}

	public void setPoint(Vec3f point) {
		this.point = point;
	}

}

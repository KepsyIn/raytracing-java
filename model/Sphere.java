package model;

import utils.Vec3f;

public class Sphere extends Model{
	
	private double rayon;
	private Vec3f center;
	
	public Sphere( double rayon , Vec3f center ) {
		super();
		this.center = center;
		this.rayon = rayon;
	}

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

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

	public Vec3f getCenter() {
		return center;
	}

	public void setCenter(Vec3f center) {
		this.center = center;
	}
	
	@Override	
	public Vec3f getNormal(Vec3f P) {
		Vec3f normal = new Vec3f(P).sub(this.center);
		return normal.normalize(); 
	}
	
}

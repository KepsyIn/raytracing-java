package raytracing;

import utils.Vec3f;

public class LightSource {
	
	public Vec3f ambient;
	public Vec3f position;
	public Vec3f diffuse;
	public Vec3f specular;
	public Vec3f color;
	
	public static final Vec3f DEFAULT_COLOR = new Vec3f(1,1,1);
	public static final Vec3f DEFAULT_AMBIENT = new Vec3f(0.2f,0.2f,0.2f);
	public static final Vec3f DEFAULT_POSITION = new Vec3f(0,0,0);
	public static final Vec3f DEFAULT_DIFFUSE = new Vec3f(0.8f,0.8f,0.8f);
	public static final Vec3f DEFAULT_SPECULAR = new Vec3f(1,1,1);
		
	public LightSource(Vec3f color ,Vec3f position, Vec3f ambient, Vec3f diffuse, Vec3f specular) {
		this.color = color;
		this.ambient = ambient;
		this.position = position;
		this.diffuse = diffuse;
		this.specular = specular;
	}
	
	public LightSource() {
		this(DEFAULT_COLOR,DEFAULT_POSITION,DEFAULT_AMBIENT,DEFAULT_DIFFUSE,DEFAULT_SPECULAR);
	}
	
	public LightSource(Vec3f color , Vec3f position ) {
		this(color,position,DEFAULT_AMBIENT,DEFAULT_DIFFUSE,DEFAULT_SPECULAR);
	}

	public Vec3f getAmbient() {
		return ambient;
	}

	public void setAmbient(Vec3f ambient) {
		this.ambient = ambient;
	}

	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}

	public Vec3f getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(Vec3f diffuse) {
		this.diffuse = diffuse;
	}

	public Vec3f getSpecular() {
		return specular;
	}

	public void setSpecular(Vec3f specular) {
		this.specular = specular;
	}

	public Vec3f getColor() {
		return color;
	}

	public void setColor(Vec3f color) {
		this.color = color;
	}
}

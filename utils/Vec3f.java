package utils;

public class Vec3f
{
	/**
	 * x, y and z values of the current vector.
	 * These are public to allow fast access and simple use.
	 */
	public float x,y,z;

	/**
	 * Default Constructor
	 */
	public Vec3f()
	{
		this.x=this.y=this.z=0.F;
	}

	/**
	 * Constructor with initialisation
	 * @param x,y,z values to place into current vector
	 */
	public Vec3f(final float x,final float y,final float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}

	/**
	 * Constructor by copy
	 * @param that vector to be copied in current vector
	 */
	public Vec3f(final Vec3f that)
	{
		this.x=that.x;
		this.y=that.y;
		this.z=that.z;
	}

	/**
	 * Set current vector's value to 0.0
	 * @return current vector
	 */
	public Vec3f reset()
	{
		this.x=this.y=this.z=0.F;
		return this;
	}

	/**
	 * Copy "that" vector in current vector
	 * @param that vector to be copied
	 * @return current vector
	 */
	public Vec3f set(final Vec3f that)
	{
		this.x=that.x;
		this.y=that.y;
		this.z=that.z;
		return this;
	}

	/**
	 * Copy x, y and z in current vector
	 * @param x,y,z values to place into current vector
	 * @return current vector
	 */
	public Vec3f set(final float x,final float y,final float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		return this;
	}

	/**
	 * @return square of the length of current vector
	 */
	public float lengthSquare()
	{
			return this.x*this.x+this.y*this.y+this.z*this.z;
	}
	/**
	 * @return length of current vector
	 */
	public float length()
	{
			return (float)Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	}

	/**
	 * Normalize current vector
	 * @return current vector
	 */
	public Vec3f normalize()
	{
		float l=this.lengthSquare();
		if (l==0.F) return this;
		l=(float)Math.sqrt(l);
		return this.scale(1.F/l);
	}

	public Vec3f add(final Vec3f that)
	{
		this.x+=that.x;
		this.y+=that.y;
		this.z+=that.z;
		return this;
	}

	public Vec3f setAdd(final Vec3f v1, final Vec3f v2)
	{
		this.x=v1.x+v2.x;
		this.y=v1.y+v2.y;
		this.z=v1.z+v2.z;
		return this;
	}

	public Vec3f sub(final Vec3f that)
	{
		this.x-=that.x;
		this.y-=that.y;
		this.z-=that.z;
		return this;
	}

	public Vec3f setSub(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.x-v2.x;
		this.y=v1.y-v2.y;
		this.z=v1.z-v2.z;
		return this;
	}

	public Vec3f scale(final float scale)
	{
		this.x*=scale;
		this.y*=scale;
		this.z*=scale;
		return this;
	}

	public Vec3f scale(final float scalex,final float scaley,final float scalez)
	{
		this.x*=scalex;
		this.y*=scaley;
		this.z*=scalez;
		return this;
	}

	public Vec3f setScale(final float scale,final Vec3f that)
	{
		this.x=scale*that.x;
		this.y=scale*that.y;
		this.z=scale*that.z;
		return this;
	}

	public Vec3f setScale(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.x*v2.x;
		this.y=v1.y*v2.y;
		this.z=v1.z*v2.z;
		return this;
	}
	
	public Vec3f addScale(final float scale,final Vec3f that)
	{
		this.x+=scale*that.x;
		this.y+=scale*that.y;
		this.z+=scale*that.z;
		return this;
	}

	public Vec3f setMatMultiply(final float[] mat,final Vec3f v)
	{
		this.x=mat[0]*v.x+mat[1]*v.y+mat[2]*v.z;
		this.y=mat[3]*v.x+mat[4]*v.y+mat[5]*v.z;
		this.z=mat[6]*v.x+mat[7]*v.y+mat[8]*v.z;
		return this;
	}

	public Vec3f setTransposeMatMultiply(final float[] mat,final Vec3f v)
	{
		this.x=mat[0]*v.x+mat[3]*v.y+mat[6]*v.z;
		this.y=mat[1]*v.x+mat[4]*v.y+mat[7]*v.z;
		this.z=mat[2]*v.x+mat[5]*v.y+mat[8]*v.z;
		return this;
	}

	public float dotProduct(final Vec3f v)
	{
		return this.x*v.x+this.y*v.y+this.z*v.z;
	}

	public Vec3f setCrossProduct(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.y*v2.z-v1.z*v2.y;
		this.y=v1.z*v2.x-v1.x*v2.z;
		this.z=v1.x*v2.y-v1.y*v2.x;
		return this;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + "," + this.z + ")";
		
	}

}

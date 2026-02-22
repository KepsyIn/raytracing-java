package utils;

/**
 * 3D vector with float components.
 * Provides vector operations: addition, subtraction, scaling, dot/cross products.
 * 
 * @author KepsyIn
 */
public class Vec3f
{

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

	/**
	 * Adds another vector to this vector (component-wise).
	 * this = this + that
	 * 
	 * @param that The vector to add
	 * @return This vector after the operation
	 */
	public Vec3f add(final Vec3f that)
	{
		this.x+=that.x;
		this.y+=that.y;
		this.z+=that.z;
		return this;
	}

	/**
	 * Sets this vector to the result of component-wise addition of two vectors.
	 * this = v1 + v2
	 * 
	 * @param v1 The first vector
	 * @param v2 The second vector
	 * @return This vector after the operation
	 */
	public Vec3f setAdd(final Vec3f v1, final Vec3f v2)
	{
		this.x=v1.x+v2.x;
		this.y=v1.y+v2.y;
		this.z=v1.z+v2.z;
		return this;
	}

	/**
	 * Subtracts another vector from this vector (component-wise).
	 * this = this - that
	 * 
	 * @param that The vector to subtract
	 * @return This vector after the operation
	 */
	public Vec3f sub(final Vec3f that)
	{
		this.x-=that.x;
		this.y-=that.y;
		this.z-=that.z;
		return this;
	}

	/**
	 * Sets this vector to the result of component-wise subtraction of two vectors.
	 * this = v1 - v2
	 * 
	 * @param v1 The first vector (minuend)
	 * @param v2 The second vector (subtrahend)
	 * @return This vector after the operation
	 */
	public Vec3f setSub(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.x-v2.x;
		this.y=v1.y-v2.y;
		this.z=v1.z-v2.z;
		return this;
	}

	/**
	 * Scales this vector by a uniform scalar value.
	 * this = this * scale
	 * 
	 * @param scale The scaling factor to multiply by
	 * @return This vector after the operation
	 */
	public Vec3f scale(final float scale)
	{
		this.x*=scale;
		this.y*=scale;
		this.z*=scale;
		return this;
	}

	/**
	 * Scales this vector by separate scalar values for each component (non-uniform scaling).
	 * this = (this.x * scalex, this.y * scaley, this.z * scalez)
	 * 
	 * @param scalex The scaling factor for the x component
	 * @param scaley The scaling factor for the y component
	 * @param scalez The scaling factor for the z component
	 * @return This vector after the operation
	 */
	public Vec3f scale(final float scalex,final float scaley,final float scalez)
	{
		this.x*=scalex;
		this.y*=scaley;
		this.z*=scalez;
		return this;
	}

	/**
	 * Sets this vector to the result of scaling another vector by a scalar.
	 * this = scale * that
	 * 
	 * @param scale The scaling factor
	 * @param that The vector to scale
	 * @return This vector after the operation
	 */
	public Vec3f setScale(final float scale,final Vec3f that)
	{
		this.x=scale*that.x;
		this.y=scale*that.y;
		this.z=scale*that.z;
		return this;
	}

	/**
	 * Sets this vector to the result of component-wise multiplication of two vectors.
	 * this = (v1.x * v2.x, v1.y * v2.y, v1.z * v2.z)
	 * 
	 * @param v1 The first vector
	 * @param v2 The second vector
	 * @return This vector after the operation
	 */
	public Vec3f setScale(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.x*v2.x;
		this.y=v1.y*v2.y;
		this.z=v1.z*v2.z;
		return this;
	}

	/**
	 * Adds a scaled vector to this vector (composite operation).
	 * this = this + (scale * that)
	 * 
	 * @param scale The scaling factor
	 * @param that The vector to scale and add
	 * @return This vector after the operation
	 */
	public Vec3f addScale(final float scale,final Vec3f that)
	{
		this.x+=scale*that.x;
		this.y+=scale*that.y;
		this.z+=scale*that.z;
		return this;
	}

	/**
	 * Multiplies this vector by a 3x3 matrix (row-major order).
	 * this = mat * this
	 * 
	 * @param mat A 9-element float array representing a 3x3 matrix in row-major order
	 * @param v The vector to multiply
	 * @return This vector after the operation
	 */
	public Vec3f setMatMultiply(final float[] mat,final Vec3f v)
	{
		this.x=mat[0]*v.x+mat[1]*v.y+mat[2]*v.z;
		this.y=mat[3]*v.x+mat[4]*v.y+mat[5]*v.z;
		this.z=mat[6]*v.x+mat[7]*v.y+mat[8]*v.z;
		return this;
	}

	/**
	 * Multiplies this vector by the transpose of a 3x3 matrix (column-major order).
	 * this = mat^T * this
	 * 
	 * @param mat A 9-element float array representing a 3x3 matrix in row-major order (transposed internally)
	 * @param v The vector to multiply
	 * @return This vector after the operation
	 */
	public Vec3f setTransposeMatMultiply(final float[] mat,final Vec3f v)
	{
		this.x=mat[0]*v.x+mat[3]*v.y+mat[6]*v.z;
		this.y=mat[1]*v.x+mat[4]*v.y+mat[7]*v.z;
		this.z=mat[2]*v.x+mat[5]*v.y+mat[8]*v.z;
		return this;
	}

	/**
	 * Computes the dot product of this vector with another vector.
	 * Dot product measures the angle between two vectors.
	 * Returns: this.x*v.x + this.y*v.y + this.z*v.z
	 * 
	 * @param v The vector to compute dot product with
	 * @return The dot product as a float
	 */
	public float dotProduct(final Vec3f v)
	{
		return this.x*v.x+this.y*v.y+this.z*v.z;
	}

	/**
	 * Computes the cross product of two vectors and stores result in this vector.
	 * The cross product produces a vector perpendicular to both input vectors.
	 * this = v1 × v2
	 * 
	 * @param v1 The first vector
	 * @param v2 The second vector
	 * @return This vector after the operation
	 */
	public Vec3f setCrossProduct(final Vec3f v1,final Vec3f v2)
	{
		this.x=v1.y*v2.z-v1.z*v2.y;
		this.y=v1.z*v2.x-v1.x*v2.z;
		this.z=v1.x*v2.y-v1.y*v2.x;
		return this;
	}
	
	/**
	 * Returns a string representation of this vector in the format (x,y,z).
	 * 
	 * @return A string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + "," + this.z + ")";
	}

}

package org.domainobject.animation.sp.simple.torus;

import org.domainobject.animation.sp.arrayobject.Pos4NormalTexture;

public class Triangle {

	private Pos4NormalTexture v0 = new Pos4NormalTexture();
	private Pos4NormalTexture v1 = new Pos4NormalTexture();
	private Pos4NormalTexture v2 = new Pos4NormalTexture();

	public Triangle()
	{
	}

	public void setVertex0()
	{

	}

	public void setVertex1()
	{

	}

	public void setVertex2()
	{

	}

	@Override
	public boolean equals(Object obj)
	{
		Triangle other = (Triangle) obj;
		return v0.equals(other.v0) && v1.equals(other.v1) && v2.equals(other.v2);
	}

	@Override
	public int hashCode()
	{
		int h = v0.hashCode();
		h = h * 31 + v1.hashCode();
		h = h * 31 + v2.hashCode();
		return h;
	}


}

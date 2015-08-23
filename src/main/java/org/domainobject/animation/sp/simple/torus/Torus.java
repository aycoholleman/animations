package org.domainobject.animation.sp.simple.torus;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.arrayobject.Pos4NormalTexture;
import org.domainobject.animation.sp.arrayobject._IndexedMemoryFast;

import static org.domainobject.animation.sp.util.Math3D.*;
import static java.lang.Math.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;


public class Torus extends Animation {

	public static void main(String[] args)
	{
		new Torus().start();
	}

	private final _IndexedMemoryFast<Pos4NormalTexture> vertices = Pos4NormalTexture.indexFast(1000, false);

	private void createTorus(float majorRadius, float minorRadius, int numMajor, int numMinor)
	{
		_IndexedMemoryFast<Pos4NormalTexture> verts = vertices;

		double majorStep = 2.0f * M3D_PI / numMajor;
		double minorStep = 2.0f * M3D_PI / numMinor;

		int i, j;
		for (i = 0; i < numMajor; ++i) {
			double a0 = i * majorStep;
			double a1 = a0 + majorStep;
			float x0 = (float) cos(a0);
			float y0 = (float) sin(a0);
			float x1 = (float) cos(a1);
			float y1 = (float) sin(a1);

			Pos4NormalTexture vertex = verts.newInstance();

			for (j = 0; j <= numMinor; ++j) {
				double b = j * minorStep;
				float c = (float) cos(b);
				float r = minorRadius * c + majorRadius;
				float z = minorRadius * (float) sin(b);

				// First point
				vertex.texture.st(m3dDiv(i, numMajor), m3dDiv(j, numMinor));
				vertex.normal.xyz(x0 * c, y0 * c, z / minorRadius);

			}

		}
	}
}

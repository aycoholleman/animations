package org.domainobject.animation.sp.simple.torus;

import static java.lang.Math.*;
import static org.domainobject.animation.sp.util.Math3D.*;

import org.domainobject.animation.sp.Animation;
import org.domainobject.animation.sp.arrayobject.IndexedMemoryLazy;
import org.domainobject.animation.sp.arrayobject.Pos4NormalTexture;


public class Torus extends Animation {

	public static void main(String[] args)
	{
		new Torus().start();
	}

	private final IndexedMemoryLazy<Pos4NormalTexture> vertices = Pos4NormalTexture.indexLazy(1000, false);

	private void createTorus(float majorRadius, float minorRadius, int numMajor, int numMinor)
	{
		IndexedMemoryLazy<Pos4NormalTexture> verts = vertices;

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

			Pos4NormalTexture vertex;

			for (j = 0; j <= numMinor; ++j) {
				double b = j * minorStep;
				float c = (float) cos(b);
				float r = minorRadius * c + majorRadius;
				float z = minorRadius * (float) sin(b);

				// First point
				vertex = verts.make();
				vertex.texture.set(m3ddiv(i, numMajor), m3ddiv(j, numMinor));
				vertex.normal.set(x0 * c, y0 * c, z / minorRadius).normalize();
				vertex.position.xyzw(x0 * r, y0 * r, z);

				// Second point
				vertex = verts.make();
				vertex.texture.set(m3ddiv(i + 1, numMajor), m3ddiv(j, numMinor));
				vertex.normal.set(x1 * c, y1 * c, z / minorRadius).normalize();
				vertex.position.xyzw(x1 * r, y1 * r, z);

				// Next one over
				b = (j + 1) * minorStep;
				c = (float) cos(b);
				r = minorRadius * c + majorRadius;
				z = minorRadius * (float) sin(b);

				// 3rd point
				vertex = verts.make();
				vertex.texture.set(m3ddiv(i, numMajor), m3ddiv(j + 1, numMinor));
				vertex.normal.set(x0 * c, y0 * c, z / minorRadius).normalize();
				vertex.position.xyzw(x0 * r, y0 * r, z);

				// 4th point
				vertex = verts.make();
				vertex.texture.set(m3ddiv(i + 1, numMajor), m3ddiv(j + 1, numMinor));
				vertex.normal.set(x1 * c, y1 * c, z / minorRadius).normalize();
				vertex.position.xyzw(x1 * r, y1 * r, z);
				

			}

		}
	}
}

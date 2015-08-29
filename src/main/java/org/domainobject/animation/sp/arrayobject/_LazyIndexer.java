package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;

interface _LazyIndexer {
	
	Class<?> getIndexType();
	
	ByteBuffer createIndicesBuffer();

	void assignIndex(int objNum, Integer index);

	void assignIndex(int objNum, int index);

	void burnIndices(ByteBuffer idxBuf, int numObjs);
	
}

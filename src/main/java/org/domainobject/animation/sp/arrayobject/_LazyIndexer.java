package org.domainobject.animation.sp.arrayobject;

import java.nio.ByteBuffer;

interface _LazyIndexer {
	
	Class<?> getIndexType();
	
	ByteBuffer createIndicesBuffer();

	void index(int objNum, Integer index);

	void index(int objNum, int index);

	void burnIndices(ByteBuffer idxBuf, int numObjs);

	void burnDummy(ByteBuffer idxBuf, int numObjs);
	
}

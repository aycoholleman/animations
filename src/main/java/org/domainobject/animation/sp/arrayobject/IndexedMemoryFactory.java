package org.domainobject.animation.sp.arrayobject;

public class IndexedMemoryFactory {

	private IndexType indexType = IndexType.AUTO;
	private IndexMode indexMode = IndexMode.DIRECT;
	private Duplicity duplicity = Duplicity.FEW;

	public IndexType getIndexType()
	{
		return indexType;
	}

	public void setIndexType(IndexType indexType)
	{
		this.indexType = indexType;
	}

	public IndexMode getIndexMode()
	{
		return indexMode;
	}

	public void setIndexMode(IndexMode indexMode)
	{
		this.indexMode = indexMode;
	}

	public Duplicity getDuplicity()
	{
		return duplicity;
	}

	public void setDuplicity(Duplicity duplicity)
	{
		this.duplicity = duplicity;
	}

}

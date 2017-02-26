package model.processing;

public abstract class DeclaratorBase<DSLType> {

	protected DSLType dsl;

	public abstract void declare();

	public void setDSL(DSLType dsl) {
		this.dsl = dsl;
	}

}

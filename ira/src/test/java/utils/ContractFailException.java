package utils;

public class ContractFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContractFailException(String name) {
		super(name);
	}

}

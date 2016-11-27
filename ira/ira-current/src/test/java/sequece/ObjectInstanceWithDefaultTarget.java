package sequece;

class ObjectInstanceWithDefaultTarget extends ObjectInstance {

	public ObjectInstanceWithDefaultTarget(String name, ObjectInstance defaultTarget) {
		super(name);
		this.defaultTarget = defaultTarget;
	}

	private final ObjectInstance defaultTarget;

	public ObjectInstance send(String message) {
		return send(message, defaultTarget);
	}

}
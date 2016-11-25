package sequece;

class Exchange {

	private final String name;

	private final ObjectInstance receiver;

	private final ObjectInstance sender;

	public String getName() {
		return name;
	}

	public ObjectInstance getReceiver() {
		return receiver;
	}

	public ObjectInstance getSender() {
		return sender;
	}

	public Exchange(ObjectInstance sender, String message, ObjectInstance receiver) {
		this.receiver = receiver;
		this.sender = sender;
		this.name = message;
	}

}
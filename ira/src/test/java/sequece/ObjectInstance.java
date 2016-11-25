package sequece;

import java.util.List;

class ObjectInstance {

	private List<Exchange> exchanges;

	private String name;

	private String role;

	public ObjectInstance(String name) {
		this.name = name;
	}

	public ObjectInstance send(String message, ObjectInstance target) {
		return target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public void returns(String string, ObjectInstanceWithDefaultTarget aalisa) {
	}

}
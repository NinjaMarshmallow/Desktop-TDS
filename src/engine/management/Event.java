package engine.management;

import java.util.List;

public class Event {
	
	private List<?> list;
	
	public Event(List<?> list) {
		this.list = list;
	}
	
	public List<?> getList() {
		return this.list;
	}
}

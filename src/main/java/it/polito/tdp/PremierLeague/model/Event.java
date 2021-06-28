package it.polito.tdp.PremierLeague.model;

public class Event {
	
	public enum EventType{
		GOAL,
		ESPLUSIONE,
		INFORTUNIO
	};
	
	private EventType type;

	public Event(EventType type) {
		super();
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	
}

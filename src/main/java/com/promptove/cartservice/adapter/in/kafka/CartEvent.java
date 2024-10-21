package com.promptove.cartservice.adapter.in.kafka;

public class CartEvent {
	private String productUuid;
	private EventType eventType;

	public CartEvent(String productUuid, EventType eventType) {
		this.productUuid = productUuid;
		this.eventType = eventType;
	}

	public String getProductUuid() {
		return productUuid;
	}

	public EventType getEventType() {
		return eventType;
	}
}

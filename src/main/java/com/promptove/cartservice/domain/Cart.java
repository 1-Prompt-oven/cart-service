package com.promptove.cartservice.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cart {

	private Long cartId;
	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;
	private LocalDateTime createdAt;

	@Builder
	public Cart(String memberUuid, String productUuid, boolean selected, boolean deleted, LocalDateTime createdAt) {
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}

	public void update(boolean selected) {
		this.selected = selected;
	}

	public static Cart from(String memberUuid, String productUuid, boolean selected, boolean deleted,
		LocalDateTime createdAt) {
		return Cart.builder()
			.memberUuid(memberUuid)
			.productUuid(productUuid)
			.selected(selected)
			.deleted(deleted)
			.createdAt(createdAt)
			.build();
	}

}

package com.promptove.cartservice.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Cart {

	private Long id;
	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;
	private LocalDateTime createdAt;

	@Builder
	public Cart(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted, LocalDateTime createdAt) {
		this.id = id;
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}

	public void updateSelected(boolean selected) {
		this.selected = selected;
	}

	public void updateDeleted(boolean deleted) {
		this.deleted = deleted;
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

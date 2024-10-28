package com.promptove.cartservice.domain.model;

import java.time.LocalDateTime;

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
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	@Builder
	public Cart(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted,
		LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
		this.id = id;
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public static Cart from(String memberUuid, String productUuid, boolean selected, boolean deleted,
		LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
		return Cart.builder()
			.memberUuid(memberUuid)
			.productUuid(productUuid)
			.selected(selected)
			.deleted(deleted)
			.createdDate(createdDate)
			.lastModifiedDate(lastModifiedDate)
			.build();
	}
}

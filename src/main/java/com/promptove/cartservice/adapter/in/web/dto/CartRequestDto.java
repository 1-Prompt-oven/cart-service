package com.promptove.cartservice.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartRequestDto {
	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;
	private LocalDateTime createdAt;

	public CartEntity toEntity() {
		return CartEntity.builder()
			.memberUuid(memberUuid)
			.productUuid(productUuid)
			.selected(selected)
			.deleted(deleted)
			.createdAt(createdAt)
			.build();
	}
}

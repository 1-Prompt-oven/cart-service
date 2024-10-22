package com.promptove.cartservice.adapter.in.web.vo;

import java.time.LocalDateTime;

import com.promptove.cartservice.adapter.in.web.dto.CartRequestDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartRequestVo {
	private String memberUuid;
	private String productUuid;
	private LocalDateTime createdAt;

	public CartRequestDto toDto() {
		return CartRequestDto.builder()
			.memberUuid(memberUuid)
			.productUuid(productUuid)
			.selected(true)
			.deleted(false)
			.createdAt(createdAt)
			.build();
	}
}

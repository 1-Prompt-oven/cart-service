package com.promptove.cartservice.adapter.in.web.vo;

import com.promptove.cartservice.adapter.in.web.dto.CartUpdateDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateVo {
	private String memberUuid;
	private boolean selected;

	public CartUpdateDto toDto(String productUuid) {
		return CartUpdateDto.builder()
			.memberUuid(memberUuid)
			.productUuid(productUuid)
			.selected(selected)
			.build();
	}
}
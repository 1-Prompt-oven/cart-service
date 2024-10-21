package com.promptove.cartservice.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateDto {

	private String memberUuid;
	private String productUuid;
	private boolean selected;
}

package com.promptove.cartservice.application.port.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateDto {
	private String memberUuid;
	private String productUuid;
	private boolean selected;
}
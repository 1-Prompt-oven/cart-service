package com.promptove.cartservice.adapter.in.web.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateVo {
	private String memberUuid;
	private String productUuid;
	private boolean selected;
}
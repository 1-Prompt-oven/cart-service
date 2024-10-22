package com.promptove.cartservice.adapter.in.web.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartDeleteRequestVo {
	private String memberUuid;
	private String productUuid;
}

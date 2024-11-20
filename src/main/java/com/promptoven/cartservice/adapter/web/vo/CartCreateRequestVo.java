package com.promptoven.cartservice.adapter.web.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartCreateRequestVo {
	private String memberUuid;
	private String productUuid;
}

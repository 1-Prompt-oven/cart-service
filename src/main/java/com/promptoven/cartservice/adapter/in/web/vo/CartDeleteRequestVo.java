package com.promptoven.cartservice.adapter.in.web.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartDeleteRequestVo {
    private Long cartId;
}

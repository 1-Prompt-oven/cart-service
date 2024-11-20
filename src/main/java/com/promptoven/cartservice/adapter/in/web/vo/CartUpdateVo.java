package com.promptoven.cartservice.adapter.in.web.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateVo {
    private Long cartId;
    private boolean selected;
}
package com.promptoven.cartservice.adapter.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartUpdateVo {
    private Long cartId;
    private boolean selected;
}
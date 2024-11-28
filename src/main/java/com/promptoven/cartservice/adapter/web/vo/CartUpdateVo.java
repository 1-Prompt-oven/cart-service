package com.promptoven.cartservice.adapter.web.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartUpdateVo {

    private final Long cartId;
    
    private final boolean selected;
}
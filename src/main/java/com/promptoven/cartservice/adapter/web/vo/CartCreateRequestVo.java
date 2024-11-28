package com.promptoven.cartservice.adapter.web.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartCreateRequestVo {

    private final String memberUuid;
    
    private final String productUuid;
}

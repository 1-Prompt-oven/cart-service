package com.promptoven.cartservice.adapter.web.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartDeleteRequestVo {

    private final Long cartId;

    @JsonCreator
    public CartDeleteRequestVo(@JsonProperty("cartId") Long cartId) {
        this.cartId = cartId;
    }
}

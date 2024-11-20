package com.promptoven.cartservice.adapter.in.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDeleteRequestVo {

    private Long cartId;
}

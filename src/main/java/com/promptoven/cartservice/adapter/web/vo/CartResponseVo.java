package com.promptoven.cartservice.adapter.web.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CartResponseVo {

    private final Long id;

    private final String memberUuid;

    private final String productUuid;

    private final boolean selected;

    private final boolean deleted;

    private final LocalDateTime createdDate;

    private final LocalDateTime lastModifiedDate;
}
package com.promptoven.cartservice.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CartInportDto {
    //여러가지 컬럼이 다 들어감
    private Long cartId;

    private String memberUuid;

    private String productUuid;

    private boolean selected;

    private boolean deleted;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}

package com.promptoven.cartservice.application.port.out.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
//여러가지 컬럼이 다 들어감
public class CartOutportDto {

    private Long id;

    private String memberUuid;

    private String productUuid;

    private boolean selected;

    private boolean deleted;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}

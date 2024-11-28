package com.promptoven.cartservice.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Cart {

    private Long id;
    private String memberUuid;
    private String productUuid;
    private boolean selected;
    private boolean deleted;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static Cart from(String memberUuid, String productUuid, boolean selected, boolean deleted,
                            LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        return Cart.builder()
                .memberUuid(memberUuid)
                .productUuid(productUuid)
                .selected(selected)
                .deleted(deleted)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}

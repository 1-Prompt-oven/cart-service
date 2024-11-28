package com.promptoven.cartservice.adapter.mysql.entity;

import com.promptoven.cartservice.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberUuid;
    private String productUuid;
    private boolean selected;
    private boolean deleted;

    @Builder
    public CartEntity(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted,
                      LocalDateTime createdAt) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.productUuid = productUuid;
        this.selected = selected;
        this.deleted = deleted;
    }
}

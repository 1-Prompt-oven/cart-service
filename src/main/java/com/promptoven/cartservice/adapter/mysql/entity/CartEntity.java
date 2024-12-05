package com.promptoven.cartservice.adapter.mysql.entity;

import com.promptoven.cartservice.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "cart_entity", indexes = {
        @Index(name = "idx_cart_entity_member_uuid", columnList = "member_uuid"),
        @Index(name = "idx_cart_entity_member_product_uuid", columnList = "member_uuid, product_uuid")})
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

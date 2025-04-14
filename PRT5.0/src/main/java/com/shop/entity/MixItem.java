package com.shop.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "mixAlcohol")
@Data
public class MixItem {

    @Id
    @Column(name = "mixAlcohol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mixItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id")
    private Cocktail menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "item_id")
    private Item itemId;

    public void saveMixItem(Item itemId) {
        this.itemId = itemId;
    }

}

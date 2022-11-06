package jpa.shop.domain.item;

import jpa.shop.domain.BaseEntity;
import jpa.shop.domain.Category;
import jpa.shop.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType")
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    private String name;

    private Long price;

    private Long stockQuantity;

    @ManyToMany(fetch = FetchType.LAZY
            , mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}

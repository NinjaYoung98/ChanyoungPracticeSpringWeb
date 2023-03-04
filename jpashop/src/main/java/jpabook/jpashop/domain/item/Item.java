package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "Item_Id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public int addStock(int quantity) {
        return this.stockQuantity += quantity;
    }

    public int removeStock(int quantity) {
        if (stockQuantity - quantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        return this.stockQuantity -= quantity;
    }

}

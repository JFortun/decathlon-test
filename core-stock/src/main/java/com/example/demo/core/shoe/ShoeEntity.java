package com.example.demo.core.shoe;

import com.example.demo.core.stock.StockEntity;

import javax.persistence.*;

@Entity
@Table(name = "shoe")
public class ShoeEntity {

    @Id
    @GeneratedValue
    @Column(name = "shoe_id")
    private Integer id;

    @Column(name = "shoe_color", nullable = false)
    private String color;

    @Column(name = "shoe_size", nullable = false)
    private Integer size;

    @Column(name = "shoe_quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stock;

    public ShoeEntity() {
    }

    public ShoeEntity(String color, Integer size, Integer quantity, StockEntity stock) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }

}

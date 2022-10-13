package com.example.demo.core.stock;

import com.example.demo.core.shoe.ShoeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue
    @Column(name = "stock_id")
    private Integer id;

    @Column(name = "stock_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockEntityState state;

    @OneToMany(mappedBy = "stock")
    private List<ShoeEntity> shoes;

    public StockEntity() {
    }

    public StockEntity(StockEntityState state, List<ShoeEntity> shoes) {
        this.state = state;
        this.shoes = shoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StockEntityState getState() {
        return state;
    }

    public void setState(StockEntityState state) {
        this.state = state;
    }

    public List<ShoeEntity> getShoes() {
        return shoes;
    }

    public void setShoes(List<ShoeEntity> shoes) {
        this.shoes = shoes;
    }

}

package com.example.domains.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Table(name = "orders")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 300002228479017363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    @OneToMany(targetEntity = Product.class,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    @OneToOne
    private TrackingInfo trackingInfo;

    private LocalDate orderDate;

    public Order() {
    }

   public LocalDate getDate() {
        return orderDate;
    }

    public void setDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public TrackingInfo getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(products, order.products) && Objects.equals(trackingInfo, order.trackingInfo) && Objects.equals(orderDate, order.orderDate);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, products, trackingInfo, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", products=" + products +
                ", trackingInfo=" + trackingInfo +
                ", orderDate=" + orderDate +
                '}';
    }
}


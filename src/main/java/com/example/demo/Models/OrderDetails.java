package com.example.demo.Models;



//import com.example.demo.validation.EmailValid;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetails")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DetailID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailid;

    @ManyToOne
    @JoinColumn(name = "DetailOrderID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order Orderrr;

    @ManyToOne
    @JoinColumn(name = "DetailProductID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product Producttt;

    @Column(name = "DetailName", nullable = false)
    private String detailname;

    @Column(name = "DetailPrice", nullable = false)
    private float detailprice;

    @Column(name = "DetailQuantity", nullable = false)
    private Integer detailquantity;




}


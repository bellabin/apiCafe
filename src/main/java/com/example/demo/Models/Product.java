package com.example.demo.Models;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ProductID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;

    @Column(name = "ProductName", nullable = false)
    @NotBlank(message = "Name is not blank")
    private String productname;

    //private Integer prodcate;

    @Column(name = "ProductPrice", nullable = false)
    private String productprice;

    @Column(name = "ProductThumb", nullable = false)
    private String productthumb;

    @Column(name = "ProductImage", nullable = false)
    private String productimg;


//    @Column(name = "ProductImage", nullable = false)
//    private String productimage;

    @ManyToOne
    @JoinColumn(name = "ProductCategoryID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductCategory Categoryyy;


    //private int productcategory = Categoryyy.getCateid();

    @Column(name = "ProductStock", nullable = false)
    private int productstock;


    @OneToMany(mappedBy = "Producttt", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetails> details;

}


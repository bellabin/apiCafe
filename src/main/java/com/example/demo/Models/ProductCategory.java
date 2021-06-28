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
@Table(name = "productcategories")
@EntityListeners(AuditingEntityListener.class)
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CategoryID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cateid;

    @Column(name = "CategoryName", nullable = false)
    @NotBlank(message = "Name is not blank")
    private String catename;

    @OneToMany(mappedBy = "Categoryyy", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}


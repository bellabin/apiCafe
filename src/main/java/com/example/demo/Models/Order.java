package com.example.demo.Models;



//import com.example.demo.validation.EmailValid;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "OrderID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;

    @ManyToOne
    @JoinColumn(name = "OrderUserID", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User Userrr;

    @Column(name = "OrderAmount", nullable = false)
    private Float ordamount;

    @Column(name = "OrderShipAddress", nullable = false)
    private String ordaddress;

    @Column(name = "OrderPhone", nullable = false)
    private String ordphone;

    @Column(name = "OrderEmail", nullable = false)
    private String ordemail;

    @Column(name = "OrderShipped")
    private Integer ordship;

    @OneToMany(mappedBy = "Orderrr", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetails> orderDetails;


}


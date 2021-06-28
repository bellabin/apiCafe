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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UserID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @Column(name = "UserName", nullable = false)
    @NotBlank(message = "Name is not blank")
    private String username;

    //@EmailCheck
    @Column(name = "UserEmail", nullable = false)
    private String email;

    @Column(name = "UserPhone", nullable = false)
    private String userphone;

    @Column(name = "UserAddress", nullable = false)
    private String useraddress;

    @Column(name = "UserPassword", nullable = false)
    private String userpass;

    @OneToMany(mappedBy = "Userrr", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;


}


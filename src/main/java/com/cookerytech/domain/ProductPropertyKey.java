package com.cookerytech.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_productPropertyKey")
public class ProductPropertyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)      //min=2 max=80
    private String name;

    private Integer seq;

    private Boolean builtIn=false;

    //(cascade = CascadeType.REMOVE)
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

//    @Column(nullable = false)
//    private LocalDateTime createAt;
//
//    @Column(nullable = false)
//    private LocalDateTime updateAt;
}

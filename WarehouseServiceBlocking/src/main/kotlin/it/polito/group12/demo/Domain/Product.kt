package it.polito.group12.demo.Domain

import java.math.BigDecimal
import javax.persistence.*


@Entity
class Product {

    @Id
    @GeneratedValue
    var product_id: Long? = null

    @Column(length = 100)
    var name: String? = null


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId" , referencedColumnName = "categoryId")
    var category_id: Category? = null

    @Column
    var price: BigDecimal? = null

    @Column
    var quantity: Long? = null



}
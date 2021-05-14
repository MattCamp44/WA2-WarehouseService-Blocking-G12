package it.polito.group12.demo.Domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category {

    @Id
    @GeneratedValue
    var categoryId: Long? = null

    @Column(length = 100)
    var name : String? = null

}
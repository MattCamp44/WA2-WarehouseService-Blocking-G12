package it.polito.group12.demo.Dtos

import java.math.BigDecimal

class ProductDTO(
    val productId:Long? = null,
    val name :String? = null,
    val categoryId: Long? = null,
    val price : BigDecimal? = null,
    val quantity: Long?= null
)
package it.polito.group12.demo.Services

import it.polito.group12.demo.Domain.Product

interface ProductService {

    fun updateProductQuantity(productId: Long, newQuantity: Long): Product?


}
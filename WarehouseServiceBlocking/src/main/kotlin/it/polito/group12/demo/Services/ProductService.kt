package it.polito.group12.demo.Services

import it.polito.group12.demo.Domain.Category
import it.polito.group12.demo.Domain.Product
import it.polito.group12.demo.Dtos.ProductDTO

interface ProductService {

    fun updateProductQuantity(productId: Long, newQuantity: Long): Product?

    fun getProductById( productId: Long ) : Product?

    fun getAllProducts(): List<Product>

    fun getProductsByCategory(categoryName: String?): List<Product>

    fun getProductBYCategoryId(categoryId : Long?) : Category?

    fun addProduct(product: Product) : Product?

}
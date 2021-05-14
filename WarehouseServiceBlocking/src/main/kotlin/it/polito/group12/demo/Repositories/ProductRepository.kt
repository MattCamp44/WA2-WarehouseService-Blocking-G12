package it.polito.group12.demo.Repositories

import it.polito.group12.demo.Domain.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, Long> {

    fun findProductByProductId(productId: Long) : Product?

}
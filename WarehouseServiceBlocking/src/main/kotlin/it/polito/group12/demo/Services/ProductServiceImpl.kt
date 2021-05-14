package it.polito.group12.demo.Services

import it.polito.group12.demo.Domain.Product
import it.polito.group12.demo.Repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ProductServiceImpl: ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    override fun updateProductQuantity(productId: Long,  newQuantity: Long): Product? {

        var product: Product? = productRepository.findProductByProductId(productId) ?: throw Exception("Product not found")

        var quantity : Long = newQuantity

        if(newQuantity < 0 )
            if(Math.abs(newQuantity) > product?.quantity!!)
                throw Exception("Not enough quantity for this product")
            else
                quantity = product.quantity!! - Math.abs(newQuantity)

        product?.quantity = quantity



        return productRepository.save(product!!)




    }
}
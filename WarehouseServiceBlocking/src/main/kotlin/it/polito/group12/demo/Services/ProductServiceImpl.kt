package it.polito.group12.demo.Services

import it.polito.group12.demo.Domain.Product
import it.polito.group12.demo.Repositories.Categoryrepository
import it.polito.group12.demo.Repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class ProductServiceImpl: ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryrepository: Categoryrepository

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

    override fun getProductById(productId: Long): Product? {

        var product = productRepository.findProductByProductId(productId)

        if(product == null) throw Exception("Product not found")

        return product

    }

    override fun getAllProducts(): List<Product> {

        return productRepository.findAll()


    }

    override fun getProductsByCategory(categoryName: String?): List<Product> {

        val categoryId =  categoryrepository.findCategoryByName(categoryName!!)

        if (categoryId == null) throw Exception("Category not found")

        return productRepository.findProductByCategoryId(categoryId!!)


    }
}
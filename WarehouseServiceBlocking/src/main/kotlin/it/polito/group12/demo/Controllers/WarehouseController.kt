package it.polito.group12.demo.Controllers

import it.polito.group12.demo.Domain.Category
import it.polito.group12.demo.Domain.Product
import it.polito.group12.demo.Dtos.ProductDTO
import it.polito.group12.demo.Repositories.Categoryrepository
import it.polito.group12.demo.Repositories.ProductRepository
import it.polito.group12.demo.Utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/warehouse")
class WarehouseController {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryrepository: Categoryrepository

    @PostMapping(Constants.ADD_PRODUCT)
    fun addNewProduct(@RequestBody productDTO: ProductDTO): ResponseEntity<Any> {
        return try {
            val product = Product()

            product.product_id = null
            product.name =  productDTO.name!!
            product.category_id = Category()
            product.category_id = categoryrepository.findCategoryByCategoryId(productDTO.categoryId!!)
            product.price = productDTO.price!!
            product.quantity = productDTO.quantity!!


            ResponseEntity(productRepository.save(product), HttpStatus.CREATED)

        } catch (ex: Exception) {
            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
    }



}
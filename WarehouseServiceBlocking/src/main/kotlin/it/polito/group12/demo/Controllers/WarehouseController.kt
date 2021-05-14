package it.polito.group12.demo.Controllers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sun.istack.NotNull
//import com.google.gson.*
import it.polito.group12.demo.Domain.Category
import it.polito.group12.demo.Domain.Product
import it.polito.group12.demo.Dtos.ProductDTO
import it.polito.group12.demo.Repositories.Categoryrepository
import it.polito.group12.demo.Repositories.ProductRepository
import it.polito.group12.demo.Services.ProductService
import it.polito.group12.demo.Utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jackson.JsonObjectDeserializer
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/warehouse")
class WarehouseController {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryrepository: Categoryrepository

    @Autowired
    lateinit var productService: ProductService

    @PostMapping(Constants.ADD_PRODUCT)
    fun addNewProduct(@RequestBody productDTO: ProductDTO): ResponseEntity<Any> {
        return try {
            val product = Product()

            product.productId = null
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

    @PatchMapping(Constants.UPDATE_QUANTITY, MediaType.APPLICATION_JSON_VALUE)
    fun updateQuantity(@PathVariable product_Id: Long,
                                 @RequestBody requestBody: String?  ):
            ResponseEntity<Any>? {
        println("Here")

        return try {


            val body: JsonObject = Gson().fromJson(requestBody, JsonObject::class.java)

            var newQuantity: Long = body.get("newQuantity").asLong

            println(newQuantity)

            val product = productService.updateProductQuantity(product_Id, newQuantity!!)
            ResponseEntity(product, HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
    }


    @GetMapping(Constants.GET_PRODUCT_BY_ID)
    fun getProductById(
        @NotNull @PathVariable productID: Long
    ): ResponseEntity<Any>?  {


        println("Received $productID ")

        //return ResponseEntity<Any>("Done",HttpStatus.OK)
        return try{

            val product = productService.getProductById(productID)

            println(product)



            ResponseEntity(product, HttpStatus.OK)

        } catch(ex: Exception){
            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)

        }


    }



    @GetMapping(Constants.GET_ALL_PRODUCTS)
    fun getAllProducts() : ResponseEntity<Any>?{

        return try{
            var allProductList : List<Product> = productService.getAllProducts()

             ResponseEntity(allProductList,HttpStatus.OK)


        } catch(ex: Exception){

            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)

        }






    }




}
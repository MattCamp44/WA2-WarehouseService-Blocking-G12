package it.polito.group12.demo.Repositories

import it.polito.group12.demo.Domain.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Categoryrepository : CrudRepository<Category, Long> {

    //fun findCategoryByCategory_id() : Category?

    fun findCategoryByCategoryId(categoryId: Long) : Category?


}
package it.polito.group12.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WarehouseServiceBlockingApplication

fun main(args: Array<String>) {
    runApplication<WarehouseServiceBlockingApplication>(*args)
}

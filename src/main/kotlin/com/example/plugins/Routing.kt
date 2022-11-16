package com.example.plugins

import com.example.routes.customerRouting
import com.example.routes.getOrder
import com.example.routes.getOrderTotal
import com.example.routes.listOrders
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRouting()
        listOrders()
        getOrder()
        getOrderTotal()
    }
}

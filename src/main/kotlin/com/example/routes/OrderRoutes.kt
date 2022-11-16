package com.example.routes

import com.example.model.orderStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrders() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrder() {
    get("/order/{number?}") {
        val number = call.parameters["number"] ?: return@get call.respondText(
            "Missing order number",
            status = HttpStatusCode.BadRequest
        )
        val order = orderStorage.find { it.number == number } ?: return@get call.respondText(
            "Not found",
            status = HttpStatusCode.NotFound
        )
        call.respond(order)
    }
}

fun Route.getOrderTotal() {
    get("/order/{number?}/total") {
        val number = call.parameters["number"] ?: return@get call.respondText(
            "Missing order number",
            status = HttpStatusCode.BadRequest
        )
        val order = orderStorage.find { it.number == number } ?: return@get call.respondText(
            "Not found",
            status = HttpStatusCode.NotFound
        )
        val total = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
    }
}
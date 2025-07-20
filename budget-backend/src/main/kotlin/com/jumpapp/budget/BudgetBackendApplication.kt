package com.jumpapp.budget

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BudgetBackendApplication

fun main(args: Array<String>) {
	runApplication<BudgetBackendApplication>(*args)
}

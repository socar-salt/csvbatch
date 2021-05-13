package com.salt.csvbatch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CsvbatchApplication

fun main(args: Array<String>) {
    runApplication<CsvbatchApplication>(*args)
}

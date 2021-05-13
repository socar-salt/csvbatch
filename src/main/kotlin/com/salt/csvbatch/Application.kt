package com.salt.csvbatch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class CsvbatchApplication

fun main(args: Array<String>) {
    runApplication<CsvbatchApplication>(*args)
}

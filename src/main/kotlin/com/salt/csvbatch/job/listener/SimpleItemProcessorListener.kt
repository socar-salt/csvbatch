package com.salt.csvbatch.job.listener

import com.salt.csvbatch.job.Person
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ItemProcessListener
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class SimpleItemProcessorListener: ItemProcessListener<Person, Person> {
    val log = LoggerFactory.getLogger(javaClass)
    override fun beforeProcess(item: Person) {
        log.info("===== beforeProcess : $item")
    }

    override fun afterProcess(item: Person, result: Person?) {
        log.info("===== afterProcess : $item")
    }

    override fun onProcessError(item: Person, e: Exception) {
        TODO("Not yet implemented")
    }
}

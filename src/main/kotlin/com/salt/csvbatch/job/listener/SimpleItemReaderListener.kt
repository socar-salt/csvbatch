package com.salt.csvbatch.job.listener

import com.salt.csvbatch.job.Person
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ItemReadListener
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class SimpleItemReaderListener: ItemReadListener<Person> {
    val log = LoggerFactory.getLogger(javaClass)
    override fun beforeRead() {
        log.info("===== beforeRead")
    }

    override fun afterRead(item: Person) {
        log.info("===== afterRead item : $item")
    }

    override fun onReadError(ex: Exception) {
        log.info("===== onReadError")
    }

}

package com.salt.csvbatch.job.listener

import com.salt.csvbatch.job.Person
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ItemWriteListener
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class SimpleItemWriterListener: ItemWriteListener<Person> {
    val log = LoggerFactory.getLogger(javaClass)
    override fun beforeWrite(items: MutableList<out Person>) {
        log.info("===== beforeWrite")
        items.map { item ->
            log.info("item : $item")
        }
    }

    override fun afterWrite(items: MutableList<out Person>) {
        log.info("===== afterWrite")
        items.map { item ->
            log.info("item : $item")
        }
    }

    override fun onWriteError(exception: Exception, items: MutableList<out Person>) {
        TODO("Not yet implemented")
    }
}

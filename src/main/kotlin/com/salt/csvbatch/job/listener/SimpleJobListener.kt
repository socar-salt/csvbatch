package com.salt.csvbatch.job.listener

import org.slf4j.LoggerFactory
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class SimpleJobListener: JobExecutionListener {
    val log = LoggerFactory.getLogger(javaClass)

    override fun beforeJob(jobExecution: JobExecution) {
        log.info("===== beforeJob")
    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info("===== afterJob")
    }
}

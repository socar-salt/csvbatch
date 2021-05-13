package com.salt.csvbatch.job.listener

import org.slf4j.LoggerFactory
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class SimpleStepListener: StepExecutionListener {
    val log = LoggerFactory.getLogger(javaClass)

    override fun beforeStep(stepExecution: StepExecution) {
        log.info("===== beforeStep")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {
        log.info("===== afterStep : " + stepExecution.summary)
        return stepExecution.exitStatus
    }
}

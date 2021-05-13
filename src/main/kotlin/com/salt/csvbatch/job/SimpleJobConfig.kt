package com.salt.csvbatch.job

import com.salt.csvbatch.job.listener.SimpleItemReaderListener
import com.salt.csvbatch.job.listener.SimpleJobListener
import com.salt.csvbatch.job.listener.SimpleStepListener
import org.springframework.batch.core.ItemProcessListener
import org.springframework.batch.core.ItemWriteListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.sql.DataSource

@Configuration
class SimpleJobConfig(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val dataSource: DataSource,
    val simpleJobListener: SimpleJobListener,
    val simpleStepListener: SimpleStepListener,
    val simpleItemReaderListener: SimpleItemReaderListener,
    val simpleItemProcessorListener: ItemProcessListener<Person, Person>,
    val simpleItemWriterListener: ItemWriteListener<Person>
) {

    @Bean
    fun simpleJob(): Job {
        return jobBuilderFactory["simpleJob"]
            .start(simpleStep())
            .listener(simpleJobListener)
            .build()
    }

    @Bean
    fun simpleStep(): Step {
        return stepBuilderFactory["simpleStep"]
            .chunk<Person, Person>(10)
            .reader(simpleItemReader())
            .processor(simpleItemProcessor())
            .writer(simpleItemWriter(dataSource))
            .listener(simpleItemReaderListener)
            .listener(simpleItemProcessorListener)
            .listener(simpleItemWriterListener)
            .listener(simpleStepListener)
            .build()
    }

    @Bean
    fun simpleItemReader(): FlatFileItemReader<Person> {
        return FlatFileItemReaderBuilder<Person>()
            .name("simpleItemReader")
            .resource(ClassPathResource("sample-data.csv"))
            .delimited()
            .names("name", "nick", "state")
            .targetType(Person::class.java)
            .recordSeparatorPolicy(object : SimpleRecordSeparatorPolicy() {
                override fun postProcess(record: String): String {
                    return record.toUpperCase()
                }
            })
            .build()
    }

    @Bean
    fun simpleItemProcessor(): ItemProcessor<Person, Person> {
        return ItemProcessor { item ->
            Person(
                name = item.name,
                nick = item.nick,
                state = 1
            )
        }
    }

    @Bean
    fun simpleItemWriter(ds: DataSource): JdbcBatchItemWriter<Person> {
        return JdbcBatchItemWriterBuilder<Person>()
            .itemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
            .sql("INSERT INTO person (name, nick, state) VALUES (:name, :nick, :state)")
            .dataSource(ds)
            .build()
    }
}
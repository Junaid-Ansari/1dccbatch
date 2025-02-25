package com.example.dccbatch.job;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import com.example.domain.MyData;

import javax.sql.DataSource;

@Configuration
//@EnableBatchProcessing
public class JobConfig {

    @Bean
    public Job myJob(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("myJob", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, @Qualifier("businessTransactionManager") PlatformTransactionManager transactionManager, JdbcCursorItemReader<MyData> reader) {
        return new StepBuilder("step1", jobRepository)
                .<MyData, MyData>chunk(10, transactionManager)
                .reader(reader)
                .writer(items -> items.forEach(System.out::println))
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<MyData> reader(@Qualifier("businessDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<MyData>()
                .dataSource(dataSource)
                .name("myDataReader")
                .sql("SELECT id, name FROM users")
                .rowMapper(new BeanPropertyRowMapper<>(MyData.class))
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, @Qualifier("businessTransactionManager") PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Executing step 2");
                    return null;
                }, transactionManager)
                .build();
    }
}
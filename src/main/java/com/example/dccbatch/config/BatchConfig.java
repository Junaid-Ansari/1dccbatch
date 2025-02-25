//package com.example.dccbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
////springframework.batch.core.configuration.annotation.JobBuilderFactory;
////import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;import org.
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//@EnableTransactionManagement
//public class BatchConfig extends DefaultBatchConfiguration {
//
//    private final DataSource batchDataSource;
//
//    public BatchConfig(@Qualifier("batchDataSource") DataSource batchDataSource) {
//        this.batchDataSource = batchDataSource;
//    }
//
//    @Override
//    protected DataSource getDataSource() {
//        return batchDataSource;
//    }
//
//    @Override
//    protected PlatformTransactionManager getTransactionManager() {
//        return new DataSourceTransactionManager(batchDataSource);
//    }
//
//}
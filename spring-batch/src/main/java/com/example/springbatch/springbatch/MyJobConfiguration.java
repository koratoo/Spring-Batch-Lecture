package com.example.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
//@Configuration
public class MyJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //1. 잡을 생성한다.
    @Bean
    public Job MyJob() {
        return jobBuilderFactory.get("MyJobBuilder")
                .start(MyStep1()) //출발 스텝 지정
                .next(MyStep2()) //다음 스텝
                .build();
    }


    //2. 스텝을 생성한다.
    @Bean
    public Step MyStep1() {
        return stepBuilderFactory.get("MyStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        log.info(" ===================================== ");
                        log.info(" My name is Joo Young Kim");
                        log.info(" ===================================== ");

                        return RepeatStatus.FINISHED; //step의 무한 반복을 막아주는 메서드
                    }
                })
                .build();
    }
    @Bean
    public Step MyStep2() {
        return stepBuilderFactory.get("MyStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        log.info(" ===================================== ");
                        log.info(" My name is Joo Young Kim's Brother!");
                        log.info(" ===================================== ");

                        return RepeatStatus.FINISHED; //step의 무한 반복을 막아주는 메서드
                    }
                })
                .build();
    }
}

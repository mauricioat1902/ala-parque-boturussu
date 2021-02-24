package br.com.alaboturussu.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Slf4j
public class JobListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("************************************************************");
        log.info("INICIO DA EXECUCAO DO JOB " + jobExecution.getJobInstance().getJobName());
        log.info("************************************************************");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info(String.format("Batch job %s finalizado com sucesso", jobExecution.getJobInstance().getJobName()));
        }
        log.info("************************************************************");
        log.info("FIM DA EXECUCAO DO JOB " + jobExecution.getJobInstance().getJobName());
        log.info("************************************************************");
    }
}

package br.com.alaboturussu.config;

import br.com.alaboturussu.entity.Membro;
import br.com.alaboturussu.listener.JobListener;
import br.com.alaboturussu.model.Doacao;
import br.com.alaboturussu.steps.doacoes.DoacoesProcessor;
import br.com.alaboturussu.steps.doacoes.DoacoesReader;
import br.com.alaboturussu.steps.doacoes.DoacoesWriter;
import br.com.alaboturussu.steps.listamembros.ListaMembrosProcessor;
import br.com.alaboturussu.steps.listamembros.ListaMembrosReader;
import br.com.alaboturussu.steps.listamembros.ListaMembrosWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Bean
    public Job sroResseguroJob(Step listaMembrosStep, Step doacoesStep) {
        return jobBuilderFactory.get("ldsArquivosJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(listaMembrosStep)
                .next(doacoesStep)
                .end()
                .build();
    }

    @Bean
    public Step listaMembrosStep(ListaMembrosReader listaMembrosReader, ListaMembrosProcessor listaMembrosProcessor, ListaMembrosWriter listaMembrosWriter) {
        return stepBuilderFactory.get("listaMembrosStep")
                .transactionManager(transactionManager)
                .<BufferedReader, List<Membro>> chunk(1)
                .reader(listaMembrosReader)
                .processor(listaMembrosProcessor)
                .writer(listaMembrosWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step doacoesStep(DoacoesReader doacoesReader, DoacoesProcessor doacoesProcessor, DoacoesWriter doacoesWriter) {
        return stepBuilderFactory.get("doacoesStep")
                .transactionManager(transactionManager)
                .<List<String>, List<Doacao>> chunk(1)
                .reader(doacoesReader)
                .processor(doacoesProcessor)
                .writer(doacoesWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public JobListener listener() {
        return new JobListener();
    }

    /**
     * In-Memory Repository - Não utiliza persistencia em banco de dados para salvar o status do job
     * @param dataSource
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        // Não define um datasource, mesmo se houver.
        // A inicialização usará um JobRepository baseado em um Map ao invés do banco de dados
    }

}

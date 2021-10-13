package lk.icoder.excelreader.config;

import lk.icoder.excelreader.model.AdjustmentDto;
import lk.icoder.excelreader.model.StudentDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Project excel-reader
 * @Author DILAN on 10/7/2021
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

   @Autowired
   private JobBuilderFactory jobBuilderFactory;

   @Autowired
   private StepBuilderFactory stepBuilderFactory;

//   @Bean
//   ItemReader<StudentDto> excelStudentReader() {
//      PoiItemReader<StudentDto> reader = new PoiItemReader<>();
//      FileSystemResource fileSystemResource = new FileSystemResource("F:\\CBC\\uploads\\student.xlsx");
//      reader.setResource(fileSystemResource);
//      reader.setLinesToSkip(1);
//      reader.setRowMapper(excelRowMapper());
//      return reader;
//   }

    @Bean
    @StepScope
    ItemReader<AdjustmentDto> adjustmentDtoItemReader() {
        PoiItemReader<AdjustmentDto> reader = new PoiItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource("F:\\CBC\\uploads\\adjustment\\test.xlsx");
        reader.setResource(fileSystemResource);
        reader.setLinesToSkip(1);
        reader.setRowMapper(adjustmentDtoRowMapper());
        return reader;
    }

   private RowMapper<StudentDto> excelRowMapper() {
      return new StudentExcelRowMapper();
   }

    private RowMapper<AdjustmentDto> adjustmentDtoRowMapper() {
        return new AdjustmentExcelRowMapper();
    }

//   @Bean
//   @StepScope
//   public JdbcBatchItemWriter<StudentDto> studentDtoJdbcBatchItemWriter(DataSource dataSource) {
//      return new JdbcBatchItemWriterBuilder<StudentDto>()
//              .dataSource(dataSource)
//              .beanMapped()
//              .sql("INSERT INTO STUDENT (name, email_address, age) VALUES (:name, :email_address, :age)")
//              .build();
//   }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<AdjustmentDto> adjustmentDtoJdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AdjustmentDto>()
                .dataSource(dataSource)
                .beanMapped()
                .sql("INSERT INTO ADJUSTMENT (current_case_stage, case_no, stage_exp_date," +
                        "pan, case_updated, tran_currency, tran_amount, " +
                        "reason_code, adjustment_currency, adjustment_amount, iss_recon_inst_id," +
                        "tran_local_date_time, acq_recon_inst_id, retrieval_ref_no, proc_ntwk," +
                        "act_to_iss, card_acpt_term_id, card_acpt_name, merch_rpting_level)" +
                        " VALUES (:current_case_stage, :case_no, :stage_exp_date," +
                        ":pan, :case_updated, :tran_currency, :tran_amount, " +
                        ":reason_code, :adjustment_currency, :adjustment_amount, :iss_recon_inst_id," +
                        ":tran_local_date_time, :acq_recon_inst_id, :retrieval_ref_no, :proc_ntwk," +
                        ":act_to_iss, :card_acpt_term_id, :card_acpt_name, :merch_rpting_level)")
                .build();
    }

//   @Bean
//   public Step studentStep(ItemReader<StudentDto> studentDtoItemReader,
//                           JdbcBatchItemWriter<StudentDto> studentDtoJdbcBatchItemWriter) {
//      ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//      taskExecutor.setCorePoolSize(5);
//      taskExecutor.setMaxPoolSize(5);
//      taskExecutor.afterPropertiesSet();
//
//      return this.stepBuilderFactory
//              .get("student-step1")
//              .<StudentDto, StudentDto>chunk(100)
//              .reader(studentDtoItemReader)
//              .writer(studentDtoJdbcBatchItemWriter)
//              .faultTolerant()
//              .skip(FlatFileParseException.class)
//              .skip(IllegalArgumentException.class)
//              .build();
//   }

    @Bean
    public Step adjustmentStep(ItemReader<AdjustmentDto> studentDtoItemReader,
                            JdbcBatchItemWriter<AdjustmentDto> adjustmentDtoJdbcBatchItemWriter) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.afterPropertiesSet();

        return this.stepBuilderFactory
                .get("adjustment-step1")
                .<AdjustmentDto, AdjustmentDto>chunk(100)
                .reader(studentDtoItemReader)
                .writer(adjustmentDtoJdbcBatchItemWriter)
                .faultTolerant()
                .skip(FlatFileParseException.class)
                .skip(IllegalArgumentException.class)
                .build();
    }

//   @Bean
//   public Job studentUploadJob(Step studentStep) {
//      return this.jobBuilderFactory
//              .get("studentJob")
//              .incrementer(new RunIdIncrementer())
//              .start(studentStep)
//              .build();
//   }

    @Bean
    public Job studentUploadJob(Step adjustmentStep) {
        return this.jobBuilderFactory
                .get("adjustmentJob")
                .incrementer(new RunIdIncrementer())
                .start(adjustmentStep)
                .build();
    }
}

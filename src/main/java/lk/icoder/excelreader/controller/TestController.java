package lk.icoder.excelreader.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project excel-reader
 * @Author DILAN on 10/8/2021
 */
@RestController
public class TestController {

    private JobLauncher jobLauncher;
    private Job studentJob;

    public TestController(JobLauncher jobLauncher, Job studentJob) {
        this.jobLauncher = jobLauncher;
        this.studentJob = studentJob;
    }

    @GetMapping
    public String upload() {

        try {
            JobParametersBuilder builder = new JobParametersBuilder();
            JobParameters parameters = builder
                    .addLong("recon", System.nanoTime()).toJobParameters();
            JobExecution execution = jobLauncher.run(studentJob, parameters);
            return "OK";
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException |
                JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }

        return null;
    }

}

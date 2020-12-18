package com.example.dockerdemo.config;

import com.example.dockerdemo.domain.TaskThreadPoolConfig;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;


/**
 *  @author: lmwu
 *  @Date: 2020/12/18 14:00
 *  @Description:
 */

@Configuration
@Scope  //单例
public class TaskExecutePoolConfig {
	private static final Logger logger = LoggerFactory.getLogger(TaskExecutePoolConfig.class);

    @Resource
    private TaskThreadPoolConfig config;
 
    @Bean
    public Executor RubyTaskAsyncPool() {
    	logger.info("getCorePoolSize:"+config.getCorePoolSize());
    	logger.info("getMaxPoolSize:"+config.getMaxPoolSize());
    	logger.info("getQueueCapacity:"+config.getQueueCapacity());
    	logger.info("getKeepAliveSeconds:"+config.getKeepAliveSeconds());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("ThreadExecutor-");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        executor.initialize();
        logger.info(executor.getThreadNamePrefix());
        return executor;
    }
}

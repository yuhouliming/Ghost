package com.example.dockerdemo.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 *  @author: lmwu
 *  @Date: 2020/12/18 14:00
 *  @Description:
 */
@Component
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig {
    /**
     * 核心线程池大小
     */
    private int corePoolSize;
    /**
     * 最大线程数
     */
    private int maxPoolSize;
    /**
     * 活跃时间
     */
    private int keepAliveSeconds;
    /**
     * 队列容量
     */
    private int queueCapacity;


	public TaskThreadPoolConfig() {
		super();
	}

	public TaskThreadPoolConfig(int corePoolSize, int maxPoolSize, int keepAliveSeconds, int queueCapacity) {
		super();
		this.corePoolSize = corePoolSize;
		this.maxPoolSize = maxPoolSize;
		this.keepAliveSeconds = keepAliveSeconds;
		this.queueCapacity = queueCapacity;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getKeepAliveSeconds() {
		return keepAliveSeconds;
	}

	public void setKeepAliveSeconds(int keepAliveSeconds) {
		this.keepAliveSeconds = keepAliveSeconds;
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
	@Override
	public String toString() {
		return "TaskThreadPoolConfig [corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize
				+ ", keepAliveSeconds=" + keepAliveSeconds + ", queueCapacity=" + queueCapacity + ", getCorePoolSize()="
				+ getCorePoolSize() + ", getMaxPoolSize()=" + getMaxPoolSize() + ", getKeepAliveSeconds()="
				+ getKeepAliveSeconds() + ", getQueueCapacity()=" + getQueueCapacity() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

    
}
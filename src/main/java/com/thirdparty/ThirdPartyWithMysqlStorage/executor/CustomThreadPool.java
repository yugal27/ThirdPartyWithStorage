package com.thirdparty.ThirdPartyWithMysqlStorage.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CustomThreadPool extends ThreadPoolExecutor {

	private static Logger WORKER_LOGGER = Logger.getLogger("workerLogger");
	private BlockingQueue<Runnable> workerQueue;

	public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workerQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workerQueue);
		super.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r,
                    ThreadPoolExecutor executor) {
            	WORKER_LOGGER.info("Task Rejected : "+ r);
            }
        });
		this.workerQueue = workerQueue;
	}
	
	private void logQueueStatistics() {
		WORKER_LOGGER.info("EnqueuedJobs"+this.workerQueue.size());
	}
	
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		WORKER_LOGGER.info("Inititating Worker"+t.getName() + ":"+r);
		logQueueStatistics();
		super.beforeExecute(t, r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		super.afterExecute(r, t);
		logQueueStatistics();
		WORKER_LOGGER.info("Finished Worker"+r);

	}
		
}

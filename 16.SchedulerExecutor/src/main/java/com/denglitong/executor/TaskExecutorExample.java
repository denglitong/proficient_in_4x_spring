package com.denglitong.executor;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.Executors;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/26
 */
public class TaskExecutorExample {

    private TaskExecutor executor;

    public void setExecutor(TaskExecutor executor) {
        this.executor = executor;
    }

    public void executeTasks() {
        for (int i = 0; i < 6; i++) {
            executor.execute(new SimpleTask("task_" + i));
        }
    }

    public static void main(String[] args) {
        TaskExecutorExample ee = new TaskExecutorExample();

        ee.setExecutor(new SimpleAsyncTaskExecutor());
        ee.executeTasks();
    }
}

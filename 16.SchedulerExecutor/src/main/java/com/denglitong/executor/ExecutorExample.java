package com.denglitong.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/26
 */
public class ExecutorExample {

    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void executeTasks() {
        for (int i = 0; i < 6; i++) {
            executor.execute(new SimpleTask("task" + i));
        }
    }

    public static void main(String[] args) {
        ExecutorExample ee = new ExecutorExample();

        ee.setExecutor(Executors.newFixedThreadPool(3));
        ee.executeTasks();
    }
}

class SimpleTask implements Runnable {

    private String taskName;

    public SimpleTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("do " + taskName + " in Thread:" + Thread.currentThread().getId());
    }
}
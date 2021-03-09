package sample.java.multithread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;

import sample.java.multithread.task.CallableTask;

import java.lang.InterruptedException;

public class MultiThread {
  public static final int TASK_COUNT = 10;
  public static final int GENARATE_THREAD_COUNT = 3;

  public static void main(String[] args){
    ExecutorService exec = Executors.newFixedThreadPool(GENARATE_THREAD_COUNT);
    List<Future<Long>> finishedThreadIDList = new ArrayList<Future<Long>>();
    for(int i=0; i<TASK_COUNT; i++){
      finishedThreadIDList.add(exec.submit(new CallableTask()));
    }

    exec.shutdown();
    
    try{
      if( !exec.awaitTermination(60, TimeUnit.SECONDS) ){
        exec.shutdownNow();
        if(!exec.awaitTermination(60, TimeUnit.SECONDS)){
          System.err.println("cannot shutdown");
        }
      }
    } catch (InterruptedException e){
      exec.shutdownNow();
      Thread.currentThread().interrupt();
    }

    System.out.println("Finish ID Order");
    finishedThreadIDList.stream().forEach(future -> { 
      try {
        System.out.println(future.get());
      } catch (InterruptedException | ExecutionException e){
        e.printStackTrace();
      }
    });
  }
}

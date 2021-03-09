package sample.java.multithread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import sample.java.multithread.task.RunnableTask;

import java.lang.InterruptedException;

/**
 * Executors.newSingleThreadExecutor()を使う。
 */
public class SingleThread {

  public static void main( String[] args ) {
    ExecutorService exec = Executors.newSingleThreadExecutor();
    exec.submit(new RunnableTask());
    new RunnableTask().run();

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

  }
}

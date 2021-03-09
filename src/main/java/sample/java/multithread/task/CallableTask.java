package sample.java.multithread.task;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Long> {
  private static final int ADD_COUNT = 5;

  @Override
  public Long call(){
    long threadID = Util.command(ADD_COUNT);
    System.out.println(threadID + " is finish.");
    return threadID;
  }
}

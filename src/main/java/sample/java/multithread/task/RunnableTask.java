package sample.java.multithread.task;

public class RunnableTask implements Runnable {
  public static int ADD_COUNT = 5;

  @Override
  public void run() {
    Util.command(ADD_COUNT);
  }
}

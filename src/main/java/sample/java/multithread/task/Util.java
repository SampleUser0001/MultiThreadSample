package sample.java.multithread.task;

public class Util {
  public static int index = 0;
  public static long command(int loopCount) {
    for(int i=0;i<loopCount;i++){  
      System.out.println(
        String.format(
          "ThreadID : %d , index : %d " ,
          Thread.currentThread().getId(),
          ++index));
//      Thread.sleep(0);
    }
    return Thread.currentThread().getId();
  }
}

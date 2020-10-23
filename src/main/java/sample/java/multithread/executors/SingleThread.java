package sample.java.multithread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import java.lang.Runnable;
import java.lang.InterruptedException;

/**
 * Executors.newSingleThreadExecutor()を使う。
 */
public class SingleThread {

    public static final int ADD_COUNT = 5;

    private static int index = 0;

    public static void main( String[] args ) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit(new Runnable(){
            @Override
            public void run() {
                command();
            }
        });

        command();

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

    public static void command() {
        try {
            for(int i=0;i<ADD_COUNT;i++){  
                System.out.println(
                    String.format(
                        "ThreadID : %d , index : %d " ,
                        Thread.currentThread().getId(),
                        ++index));
                Thread.sleep(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}

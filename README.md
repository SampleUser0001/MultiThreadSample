# MultiThreadSample
Javaのマルチスレッドの実装例

# 目次

- [MultiThreadSample](#multithreadsample)
- [目次](#目次)
  - [インスタンス生成](#インスタンス生成)
  - [別スレッド処理実装例](#別スレッド処理実装例)
    - [戻り値不要](#戻り値不要)
    - [戻り値ほしい](#戻り値ほしい)
  - [マルチスレッドの終了](#マルチスレッドの終了)
    - [実装例](#実装例)
  - [atomic](#atomic)

## インスタンス生成

1. ExecutorService exec = Executors.newSingleThread();
   - 別スレッドを1つだけ作る。
   - sample.java.multithread.executors.SingleThread参考。
2. ExecutorService exec = Executors.newFixedThreadPool(int);
   - 引数で指定した数、別スレッドを作る。
3. ExecutorService exec = Executors.newCachedThreadPool();
   - 必要に応じて、別スレッドを作る。

## 別スレッド処理実装例

### 戻り値不要

Runnableインスタンスを渡す。

```java
ExecutorService exec = Executors.newFixedThreadPool(5);
exec.submit(new Runnable(){
    @Override
    public void run(){
    // 何らかの処理
    }
});
```

### 戻り値ほしい

Callableインスタンスを渡す。

```java
    ExecutorService exec = Executors.newFixedThreadPool(5);
    List<Future<String>> list = new ArrayList<Future<String>>();
    
    list.add(
      exec.submit(new Callable<String>(){
        @Override
        public String call(){
          String returnValue = "";
          // 何らかの処理
          return returnValue;
        }
      })
    );
```


## マルチスレッドの終了

ExecutorServiceのメソッドを使用する。

- void shutdown()
    - 新規タスクの受け入れを終了する。
    - 受け入れ済みのタスクは継続実行する。
- List<Runnable> shutdownNow()
    - 新規タスクの受け入れを終了する。
    - 受け入れ済みタスクは戻ってくる。
    - 実行中タスクはInterruptedExceptionで止まる。
- boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException
    - shutdownメソッド呼び出し後、指定した時間だけ待つ。
    - 終了できた場合true、そうでない場合falseを返す。

### 実装例

```java    
    exec.shutdown();
    
    try{
      if( !exec.awaitTermination(60, TimeUnit.SECONDS) ){
        exec.shutdownNow();
        if(!exec.awaitTermination(60, TimeUnit.SECONDS)){
          // エラー出力を行う。
        }
      }
    } catch (InterruptedException e){
      exec.shutdownNow();
      Thread.currentThread().interrupt();
    }
```

## atomic


# MultiThreadSample
Javaのマルチスレッドの実装例

# 目次

- [MultiThreadSample](#multithreadsample)
- [目次](#目次)
- [概要](#概要)
  - [インスタンス生成](#インスタンス生成)
  - [別スレッド処理実装例](#別スレッド処理実装例)
    - [戻り値不要](#戻り値不要)
    - [戻り値ほしい](#戻り値ほしい)
  - [マルチスレッドの終了](#マルチスレッドの終了)
- [実装例](#実装例)
  - [SingleThread](#singlethread)
    - [ソース](#ソース)
    - [実行結果](#実行結果)
    - [実行コマンド](#実行コマンド)
  - [MultiThread](#multithread)
    - [ソース](#ソース-1)
    - [実行結果](#実行結果-1)
    - [実行コマンド](#実行コマンド-1)

# 概要

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

# 実装例

## SingleThread

``` java
Executors.newSingleThreadExecutor();
```

``` txt
戻り値なし。
```

### ソース

[./src/main/java/sample/java/multithread/executors/SingleThread.java](./src/main/java/sample/java/multithread/executors/SingleThread.java)

### 実行結果

mainThreadとexecutorで生成したThreadがある。

``` txt
ThreadID : 14 , index : 2 
ThreadID : 13 , index : 1 
ThreadID : 14 , index : 3 
ThreadID : 14 , index : 5 
ThreadID : 13 , index : 4 
ThreadID : 14 , index : 6 
ThreadID : 13 , index : 7 
ThreadID : 14 , index : 8 
ThreadID : 13 , index : 9 
ThreadID : 13 , index : 10 
```

### 実行コマンド

``` sh
mvn clean compile exec:java -Dexec.mainClass="sample.java.multithread.executors.SingleThread"
```

## MultiThread

``` java
public static final int GENARATE_THREAD_COUNT = 3;
Executors.newFixedThreadPool(GENARATE_THREAD_COUNT);
```

``` txt
long 実行していたThreadID。
```

### ソース

[./src/main/java/sample/java/multithread/executors/MultiThread.java](./src/main/java/sample/java/multithread/executors/MultiThread.java)

### 実行結果

mainThreadとexecutorで生成したThreadがある。

``` txt
長いので省略。
```

### 実行コマンド

``` sh
mvn clean compile exec:java -Dexec.mainClass="sample.java.multithread.executors.MultiThread"
```

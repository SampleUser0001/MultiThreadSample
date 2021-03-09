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

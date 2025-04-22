package com.jachs.logback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * 一条线程生成一个文件
 * @author zhanchaohan
 */
public class D1  {
    
   public static void main ( String[] args ) {
       ExecutorService executorService = Executors.newFixedThreadPool(5);
       for (int i = 0; i < 10; ++i) {
           executorService.execute(new RunnableTest("test"+String.valueOf(i)));
       }
       executorService.shutdown();
   }
}

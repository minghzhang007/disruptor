package com.lewis.guava.cache;

import com.google.common.cache.*;
import com.lewis.disruptor.demo1.RequestEvent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class CacheDemo1 {
    public static void main(String[] args) {
        LoadingCache<Integer, RequestEvent> requestEventCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .recordStats()
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed,the cause is " + notification.getCause());
                    }
                })
                .build(new CacheLoader<Integer, RequestEvent>() {
                    @Override
                    public RequestEvent load(Integer key) throws Exception {
                        System.out.println("load requestEvent From DB: "+key);
                        RequestEvent requestEvent = new RequestEvent(key,"name_"+key);
                        return requestEvent;
                    }
                });
        test(requestEventCache,10);
        System.out.println("==============");
        System.out.println("==============");
        test(requestEventCache,400);
    }

    private static void test(LoadingCache<Integer, RequestEvent> requestEventCache,long milliSeconds) {
        for (int i = 0; i < 200; i++) {
            try {
                RequestEvent requestEvent = requestEventCache.get(i);
                System.out.println(requestEvent);
                TimeUnit.MILLISECONDS.sleep(milliSeconds);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("cache stats: "+ requestEventCache.stats().toString());
    }
}

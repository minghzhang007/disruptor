package com.lewis.disruptor.demo1;


import com.lmax.disruptor.EventHandler;

/**
 * Created by zhangminghua on 2017/3/1.
 * 事件消费者，即一个事件处理器
 */
public class RequestEventHandler implements EventHandler<RequestEvent> {


    public void onEvent(RequestEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("already consume :"+event.toString());
    }
}

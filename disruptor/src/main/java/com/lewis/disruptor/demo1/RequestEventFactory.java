package com.lewis.disruptor.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class RequestEventFactory implements EventFactory<RequestEvent> {

    public RequestEvent newInstance() {
        return new RequestEvent();
    }
}

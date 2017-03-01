package com.lewis.disruptor.demo1;

/**
 * Created by zhangminghua on 2017/3/1.
 */

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 很明显的是：当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。
 * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。
 * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
 * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
 * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
 */
public class RequestEventProducer {

    private final RingBuffer<RequestEvent> ringBuffer;

    public RequestEventProducer(RingBuffer<RequestEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    private StringBuilder sb = new StringBuilder();
    /**
     * produce用来发布事件，没调用一次就发布一次事件，它的参数会用过事件传递给消费者
     *
     * @param byteBuffer
     */
    public void produce(ByteBuffer byteBuffer) {
        //1.可以把ringbuffer看成一个事件队列，那next()就是得到下一个事件槽
        long sequence = ringBuffer.next();
        try {
            //2.从上面的索引处取出一个空的事件去填充（获取该序号对应的事件对象）
            RequestEvent requestEvent = ringBuffer.get(sequence);
            //3.获取要通过事件传递的业务数据
            requestEvent.setId(byteBuffer.getInt(0));
            sb.append(byteBuffer.getChar(4))
                    .append(byteBuffer.getChar(6))
                    .append(byteBuffer.getChar(8))
                    .append(byteBuffer.getChar(10))
                    .append(byteBuffer.getChar(12));
            requestEvent.setName(sb.toString());
            sb.delete(0, sb.toString().length());
            byteBuffer.clear();
        } finally {
            //4.发布事件
            //注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；
            //如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
            ringBuffer.publish(sequence);
        }
    }
}

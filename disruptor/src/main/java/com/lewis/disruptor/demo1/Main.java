package com.lewis.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        RequestEventFactory requestEventFactory = new RequestEventFactory();
        //RingBuffer大小，必须是2的N次方
        int ringBufferSize = 1024 * 1024;
        YieldingWaitStrategy yieldingWaitStrategy = new YieldingWaitStrategy();
        //创建disruptor
        Disruptor<RequestEvent> disruptor
                = new Disruptor<RequestEvent>(requestEventFactory, ringBufferSize, executorService, ProducerType.SINGLE, yieldingWaitStrategy);

        //连接消费事件方法
        disruptor.handleEventsWith(new RequestEventHandler());

        //启动
        disruptor.start();
        //Disruptor 的事件发布过程是一个两阶段提交的过程：
        //发布事件
        RingBuffer<RequestEvent> ringBuffer = disruptor.getRingBuffer();

        RequestEventProducer requestEventProducer = new RequestEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(14);
        for (int i = 0; i < 10000; i++) {
            byteBuffer.putInt(i);
            byteBuffer.putChar(RandomUtil.getChar());
            byteBuffer.putChar(RandomUtil.getChar());
            byteBuffer.putChar(RandomUtil.getChar());
            byteBuffer.putChar(RandomUtil.getChar());
            byteBuffer.putChar(RandomUtil.getChar());
            byteBuffer.flip();
            requestEventProducer.produce(byteBuffer);
        }

        //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        disruptor.shutdown();
        //关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
        executorService.shutdown();

    }
}

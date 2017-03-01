package com.lewis.disruptor.demo1;

/**
 * Created by zhangminghua on 2017/3/1.
 */
public class RequestEvent {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RequestEvent() {
    }

    @Override
    public String toString() {
        return "RequestEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

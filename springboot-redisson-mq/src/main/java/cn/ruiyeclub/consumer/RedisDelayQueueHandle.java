package cn.ruiyeclub.consumer;

public interface RedisDelayQueueHandle<T> {

    void execute(T t);

    void execute(String data);
}

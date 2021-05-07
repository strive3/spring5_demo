package com.spring.study.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author duxiaopeng
 * @Date 2021/3/21 12:58 上午
 * @Description TODO
 */
public class Sync extends AbstractQueuedSynchronizer {
    //在acquire中被调用
    @Override
    protected boolean tryAcquire(int arg) {
        assert arg == 1;
        if (compareAndSetState(0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());//排他锁
            return true;
        }
        return false;
    }
    //在release中被调用
    @Override
    protected boolean tryRelease(int arg) {
        assert arg == 1;
        if (!isHeldExclusively()) throw new IllegalMonitorStateException();
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
}

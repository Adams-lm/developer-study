package com.hznu.thread;

/**
 * @author LIN
 * @date 2023/10/30 20:22
 */
public class ThreadUnsafeExample {
    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }

}

package org.demo.latte.utils.timer;

import java.util.TimerTask;

/**
 * Created by zhanyi on 2017/10/16.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener iTimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}

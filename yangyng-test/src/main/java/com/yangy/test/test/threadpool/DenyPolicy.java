package com.yangy.test.test.threadpool;

@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * <p>
     * 当前策略会抛弃该任务
     * </P>
     */
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    /**
     * <p>
     * 当前策略会抛出异常
     * </P>
     */
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RuntimeException("当前队列已满,会抛出异常");
        }
    }

    /**
     * <p>
     * 当前策略会使任务在提交者所在的线程中执行任务
     * </P>
     */
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}

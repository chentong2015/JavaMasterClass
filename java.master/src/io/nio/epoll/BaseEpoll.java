package io.nio.epoll;

// EPollSelectorImpl.java
// EPollArrayWrapper.java
public class BaseEpoll {

    // 1. Selector selector = Selector.open(); 底层的实现
    //    最终在linux平台上创建的provider是EPollSelectorProvider
    //    public static SelectorProvider create() {
    //        String osname = AccessController
    //            .doPrivileged(new GetPropertyAction("os.name"));
    //        if (osname.equals("SunOS"))
    //            return createProvider("sun.nio.ch.DevPollSelectorProvider");
    //        if (osname.equals("Linux"))
    //            return createProvider("sun.nio.ch.EPollSelectorProvider");
    //        return new sun.nio.ch.PollSelectorProvider();
    //    }
    //
    //    TODO: 在创建EPollSelectorImpl对象时，调用构造器中会创建一个数组EPollArrayWrapper
    //    EPollSelectorImpl(SelectorProvider sp) throws IOException {
    //       super(sp);
    //       long pipeFds = IOUtil.makePipe(false);
    //       fd0 = (int) (pipeFds >>> 32);
    //       fd1 = (int) pipeFds;
    //       pollWrapper = new EPollArrayWrapper(); >> 放置Channels或者事件
    //       pollWrapper.initInterrupt(fd0, fd1);
    //       fdToKey = new HashMap<>();
    //    }
    //
    //    EPollArrayWrapper() throws IOException {
    //       // creates the epoll file descriptor 文件描述符，通过fd找到创建的实例(结构体)
    //       epfd = epollCreate();
    //           private native int epollCreate(); 本地方法，底层调用Linux的系统函数(内核源代码)
    //           int epdf=epoll_create(26);
    //           // epoll_create() creates an epoll(7) instance 底层使用C语言创建一个结构体(放数据)
    //           // 外层使用Selector进行封装，操作结构体
    //      ...
    //    }

    // 2. serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); 底层实现
    //    protected void implRegister(SelectionKeyImpl ski) {
    //        if (closed)
    //            throw new ClosedSelectorException();
    //        SelChImpl ch = ski.channel;
    //        int fd = Integer.valueOf(ch.getFDVal());
    //        fdToKey.put(fd, ski);
    //        pollWrapper.add(fd); TODO: 添加指定的文件描述符
    //        keys.add(ski);
    //    }

    // 3. selector.select(); 底层实现
    //    protected int doSelect(long timeout) throws IOException {
    //        if (closed)
    //            throw new ClosedSelectorException();
    //        processDeregisterQueue();
    //        try {
    //            begin();
    //            TODO: 对加入到其中的fd进行轮询，调用native void epollCtl() 让监听事件生效
    //                  native int epollWait() 观察事件集合，有事件则不会阻塞，没有则阻塞
    //            pollWrapper.poll(timeout);
    //        } finally {
    //            end();
    //        }
    //        processDeregisterQueue();
    //        int numKeysUpdated = updateSelectedKeys();
    //        if (pollWrapper.interrupted()) {
    //            // Clear the wakeup pipe
    //            pollWrapper.putEventOps(pollWrapper.interruptedIndex(), 0);
    //            synchronized (interruptLock) {
    //                pollWrapper.clearInterrupted();
    //                IOUtil.drain(fd0);
    //                interruptTriggered = false;
    //            }
    //        }
    //        return numKeysUpdated;
    //    }
}

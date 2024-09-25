package concurrent_api.queue.not_blocking_queue;

import java.util.concurrent.ConcurrentLinkedQueue;

// ConcurrentLinkedQueue: 非阻塞高性能队列 ==> TODO: 基于CASl来保证线程安全
// 1. 基于链接节点的"unbounded无界"线程安全队列, 先进先出
// 2. 使用isEmpty()判断队列为空, 而不是使用size()遍历队列中的所有元素
public class BaseConcurrentLinkedQueue {

    // 入队的核心思想
    // 1. 基于自旋方式，找到队列的真正尾部，然后CAS方式添加节点，其中设置tail标志符号，有延迟性
    // 2. 当存在延迟性的时候，就会找真正的尾部，最后把新添加的节点设置为尾部
    // 3. 如果添加节点过程中，有另外一个线程poll节点，会知道p==q
    //    如果尾部节点都发生了变化，把p指向head节点，如果尾部节点没有变化，p还是指向尾部节点
    public void testConcurrentLinkedQueue() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.add("item");      // 调用return offer(e);
        queue.offer("item"); // ITEM.compareAndSet(this, cmp, val)
        String value = queue.poll();
        boolean isEmpty = queue.isEmpty();
    }
}

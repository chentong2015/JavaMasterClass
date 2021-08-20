package jvm_master.concurrency_collections.CopyOnWrite;

// CopyOnWrite思想: Nocas框架的实现
// TODO: 读写分离，解决高并发读写的问题，并不保证数据的实时一致性(感知的时间差) !!
// 为了防止读写并发冲突，在操作的时候将原内存结构Copy一份副本，操作完成之后再真正的替换
// 使用场景: 1. 黑白名单 2. 读多写少
// CopyOnWriteArrayList
// CopyOnWriteArraySet
// CopyOnWrite容器
public class BaseCopyOnWrite {

}

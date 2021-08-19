package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Ordering.MemoryReordering;

// TODO: 指令重排原则
// as-if-serial原则

// Happen-Before原则：先行发生原则
// 1. 用于判断数据是否竞争，线程是否安全的方式
// 2. 描述的是两项操作数之间的偏序关系，即A的操作产生的"影响"被操作B观察到
// 3. 一共8条规则，在不适用同步手段保障就能成立的先行发生的规则 !! 可以以此判断线程是否安全
// 4. 先行发生原则和时间先后顺序之间没有必然的因果关系 !!
//    时间先并不代表操是"先行发生"的
//    时间后的可能被处理器先处理
public class ReorderingPrinciples {
    
}

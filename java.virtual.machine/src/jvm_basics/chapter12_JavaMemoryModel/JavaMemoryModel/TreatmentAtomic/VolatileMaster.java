package jvm_basics.chapter12_JavaMemoryModel.JavaMemoryModel.TreatmentAtomic;

// Volatile底层是如何实现的 ?
// JMM缓存不一致的问题
// 1. 缓存一致性协议(MESI)
//    多个cpu从主内存读取同一个数据到各自的高速缓存，当其中某个cpu修改了其缓存中的数据，该数会里面同步会主内存
//    其他cpu通过"总线嗅探机制"感知到数据的变化从而将自己缓存里的数据失效
// 2. 缓存加锁
//    缓存锁基于缓存一致性协议来实现，一个处理其的缓存回写到内存会导致其他处理器的缓存无效
//    Intel 64处理器使用MESI来实现缓存一致性协议
public class VolatileMaster {
    
}

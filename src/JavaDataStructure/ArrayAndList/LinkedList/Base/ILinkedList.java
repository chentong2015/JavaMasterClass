package JavaDataStructure.ArrayAndList.LinkedList.Base;

// 抽象算法逻辑：支持不同类型的数据结构 !!!
public interface ILinkedList {

    BaseListNode getRoot();

    boolean addNode(BaseListNode newNode);

    boolean removeNode(BaseListNode node);

    // 从根节点开始遍历所有的节点 !!
    void traverse(BaseListNode root);
}

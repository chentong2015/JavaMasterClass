package JavaDataStructure.ArrayAndList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 链表的优缺点：
 * 1. 数据查询比较慢
 * 2. 增删中间节点的数据很快 (地址是非连续的存储，通过存储的地址动态的找到下一个的值) !!!
 */
// 1. 一般有3种：单向链表，双向链表，循环链表
// 2. 每一个元素(节点node)都存储当前的值 + 下一个元素的地址
// 3. 增加的时候, 只需要改变前节点存储的地址，和新节点的地址(指向下一个元素的地址)
// 4. 删除的时候, 将要删除的节点的前后节点链接起来
public class BaseLinkedList {

    public static void main(String[] args) {
        List<String> myLinkedList = new LinkedList<>();
        myLinkedList.add("item 01");
        myLinkedList.add("item 02");
        myLinkedList.add("item 03");
        myLinkedList.add(1, "add item"); // 在指定的位置添加节点数据 !!
        printLinkedList(myLinkedList);
        myLinkedList.remove(1);       // 移动指定位置的值
        String value = myLinkedList.get(1); // 取指定位置的值
    }

    private static void printLinkedList(List<String> linkedList) {
        Iterator<String> item = linkedList.iterator(); // iterator 迭代: 只能往前，无法返回前一个节点 !!!
        // .hasNext() 是否item有指向能读取的element
        while (item.hasNext()) {
            System.out.println("Show the current value: " + item.next()); // 返回当前节点的值，然后移动到下一个节点 !!!
        }
    }

    // 方法问题: 在改变LinkedList中数据的同时，还返回了值 !!
    private static boolean addInOrder(LinkedList<String> linkedList, String newItem) {
        ListIterator<String> stringListIterator = linkedList.listIterator(); // 使用list iterator才能找到前一个节点 !!!
        // .hasNext()
        // 初始的时候，必须使用.next()拿到第一个节点的值 !!
        while (stringListIterator.hasNext()) {
            // next(); 使用next会自动的移到下一个节去，不在当前处理的节点上 !!
            int comparison = stringListIterator.next().compareTo(newItem);
            if (comparison == 0) {
                System.out.println("Find item exist !");
                return false;
            } else if (comparison > 0) {
                System.out.println("New Item should be before the current item (Next)");
                stringListIterator.previous();   // 由于不在当前的节点，所以需要找前一个节点
                stringListIterator.add(newItem); // ... -> previousItem -> newItem -> nextItem -> ...
                return true;
            } else {
                System.out.println("New Item should be after the current item (Next)");
            }
        }
        stringListIterator.add(newItem); // 添加在最后
        return true;
    }

    // 双向链表的遍历效果
    // ... <-> previousItem <-> currentItem <-> nextItem <-> ...
    // 在使用双向查询的时候，需要记录往前或者往后的标识

    /**
     * 算法的设计是为了避免在元素直接形成无限的循环遍历效果 !!!
     * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration,
     * and obtain the iterator current position in the list
     * A ListIterator has no current element; 没有当前的元素 !!!
     * its cursor position always lies between the element that would be returned by a call to previous() and next()
     * An iterator for a list of length n has n+1 possible cursor positions 光标的位置会比存储值的数目多一个 !
     */
    private static void displayLinkedList(LinkedList<String> linkedList) {
        ListIterator<String> stringListIterator = linkedList.listIterator();
        if (linkedList.isEmpty()) {
            System.out.println("No data in the linked list");
        } else {
            boolean goForward = true; // 指示正在实行的遍历顺序，以便在换方向的时候，做遍历的更正(需要有一个节点的移动) !!!
            int moveAction = 1;
            switch (moveAction) {
                case 1:
                    // if 语句块用来恢复当前正在操作的节点位置 !!!
                    if (!goForward) {
                        if (stringListIterator.hasNext()) {
                            stringListIterator.next();
                        }
                        goForward = true;
                    }
                    if (stringListIterator.hasNext()) {
                        System.out.println("display the item: " + stringListIterator.next());
                    } else {
                        System.out.println("Find the end of the linked list");
                        goForward = false; // 只要唯一的一个查询方向
                    }
                case 2:
                    if (goForward) {
                        if (stringListIterator.hasPrevious()) {
                            stringListIterator.previous();
                        }
                        goForward = false;
                    }
                    if (stringListIterator.hasPrevious()) {
                        System.out.println("display the item: " + stringListIterator.previous());
                    } else {
                        System.out.println("Find the start of the linked list");
                        goForward = true;
                    }
                case 3:
                    // 移除当前的一个Item之后，应该自动跳到之后或者之前的Item，确保能够访问到 !!!
                    if (linkedList.size() > 0) {
                        stringListIterator.remove();
                    }
                default:
                    // 重复当前的Item, 确保是可以读取到的 !
                    if (goForward) {
                        System.out.println("Repeat the current item :" + stringListIterator.previous());
                    } else {
                        System.out.println("Repeat the current item :" + stringListIterator.next());
                    }
            }
        }
    }
}

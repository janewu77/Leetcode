package LeetCode;

import java.util.*;

/**
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation,
 * evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 *
 */

/*
* M:(耗时50+min)
*   - java基本概念 LinkedHashMap
*   - 用双头链表实现，一头删除、一头添加。
*
* */


public class N146MLRUCache {

    public static void main(String[] args) {
        /**
         * ["LRUCache","put","put","get","put","get","put","get","get","get"]
         * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
         */
//
//        LRUCache cache = new LRUCache(2);
//        cache.put(1, 21);
//        cache.put(2, 22);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(1));
//        cache.put(3, 23);
//
//        System.out.println(cache.get(2));
//        cache.put(4, 24);
//
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
//
        /**
         ["LRUCache","get","put","get","put","put","get","get"]
         [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
         */

        LRUCache cache = new LRUCache(2);

        System.out.println(cache.get(2));
        cache.put(2, 6);

        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    static class LRUCache {
        class Node{
            int key, value;
            Node prev, next;
            public Node() {}
            public Node(int k, int v){
                key = k;
                value = v;
            }
        }

        private Node headNode;
        private Node tailNode;
        private int capacity;
        Map<Integer, Node> data;

        public LRUCache(int size) {
            capacity = size < 1? 1: size;

            headNode = new Node();
            tailNode = new Node();
            headNode.next = tailNode;
            tailNode.prev = headNode;

            data = new HashMap<>();
        }

        private void addNodeToTail(Node node){
            node.prev = tailNode.prev;
            node.next = tailNode;

            tailNode.prev.next = node;
            tailNode.prev = node;
        }

        private Node removeNodeFromHead(){
            if (headNode.next == tailNode) return null;

            Node tobeRemoved = headNode.next;

            Node prevNode = tobeRemoved.prev;
            Node nextNode = tobeRemoved.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            return tobeRemoved;
        }

        private void moveToTail(Node node){
            if (node.next == tailNode) return;

            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            addNodeToTail(node);
        }

        public int get(int key) {
            if (!data.containsKey(key)) return -1;
            Node tmpNode = data.get(key);
            moveToTail(tmpNode);
            return tmpNode.value;
        }

        public void put(int key, int value) {
            if(data.containsKey(key)) {
                Node tmpNode = data.get(key);
                tmpNode.value = value;
                moveToTail(tmpNode);
                return;
            }

            if (data.size() >= capacity){
                Node tmp = removeNodeFromHead();
                data.remove(tmp.key);
            }

            Node tmpNode = new Node(key, value);
            data.put(key, tmpNode);
            addNodeToTail(tmpNode);
        }

    }

    // LinkedHashMap
    static class LRUCache_LinkedHashMap extends LinkedHashMap<Integer, Integer>{

        private int capacity;

        public LRUCache_LinkedHashMap(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

    }
}

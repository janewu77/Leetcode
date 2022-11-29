package LeetCode;

import java.util.*;

public class N380MInsertDeleteGetRandom {

    //2.Map + Arraylist
    //Runtime: 28 ms, faster than 89.84% of Java online submissions for Insert Delete GetRandom O(1).
    //Memory Usage: 92.3 MB, less than 89.48% of Java online submissions for Insert Delete GetRandom O(1).
    class RandomizedSet {
        Map<Integer, Integer> mapValIdx;
        List<Integer> list;
        Random random = new Random();
        public RandomizedSet() {
            mapValIdx = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (!mapValIdx.containsKey(val)) {
                int id = list.size();
                mapValIdx.put(val, id);
                list.add(val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (mapValIdx.containsKey(val)){
                int lastIdx = list.size() - 1;
                int id = mapValIdx.get(val);

                list.set(id, list.get(lastIdx));
                mapValIdx.put(list.get(lastIdx), id);

                list.remove(lastIdx);
                mapValIdx.remove(val);

                return true;
            }
            return false;
        }

        public int getRandom() {
            //int idx = (int)(mapValIdx.size() * Math.random());
            return list.get(random.nextInt(list.size()) - 1);
        }
    }

    //1.Map
    //Runtime: 71 ms, faster than 31.67% of Java online submissions for Insert Delete GetRandom O(1).
    //Memory Usage: 102.6 MB, less than 76.19% of Java online submissions for Insert Delete GetRandom O(1).
    //Time: O(1); Space: O(N)
    class RandomizedSet_1 {
        Map<Integer, Integer> mapValIdx;
        Map<Integer, Integer> mapIdxVal;
        public RandomizedSet_1() {
            mapValIdx = new HashMap<>();
            mapIdxVal = new HashMap<>();
        }

        public boolean insert(int val) {
            if (!mapValIdx.containsKey(val)) {
                int id = mapValIdx.size();
                mapValIdx.put(val, id);
                mapIdxVal.put(id, val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (mapValIdx.containsKey(val)){
                int id2 = mapValIdx.size() - 1;
                int id = mapValIdx.get(val);
                mapValIdx.remove(val);
                mapIdxVal.remove(id);

                if (id != id2) {
                    int v2 = mapIdxVal.get(id2);
                    mapValIdx.put(v2, id);
                    mapIdxVal.put(id, v2);
                    mapIdxVal.remove(id2);
                }
                return true;
            }
            return false;
        }

        public int getRandom() {
            int idx = (int)(mapValIdx.size() * Math.random());
            return mapIdxVal.get(idx);
        }
    }
}

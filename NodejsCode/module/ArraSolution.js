module.exports = class ArraySolution {
    findShortestSubArray(nums) {
        var hashmap = new Map();
        for (let i = 0; i < nums.length; i++) {
            if (!hashmap.has(nums[i])) {
                hashmap.set(nums[i], {first: i, last: i, count: 1});
            } else {
                let a = hashmap.get(nums[i]);
                a.last = i;
                a.count++;
                hashmap.set(nums[i], a);
            }
        }
        let count = 0;
        let min = 0;
        let values =hashmap.values();
        for (let m of values) {
            if (m.count > count) {
                min = m.last - m.first + 1;
                count = m.count;
            }
            if (m.count === count) min = Math.min(min, m.last - m.first + 1);
        }
        return min;
    }
};

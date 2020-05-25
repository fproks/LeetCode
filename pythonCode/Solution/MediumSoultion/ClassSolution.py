import collections

class ClassSolution:
    # 146. LRU Cache
    class LRUCache:
        def __init__(self, capacity: int):
            self.dict = collections.OrderedDict()
            self.capacity = capacity

        def get(self, key: int):
            if key not in self.dict:
                return -1
            self.dict.move_to_end(key)
            return self.dict[key]

        def put(self, key: int, value: int):
            if key in self.dict:
                self.dict.move_to_end(key)
            self.dict[key] = value
            if len(self.dict) > self.capacity:
                self.dict.popitem(last=False)

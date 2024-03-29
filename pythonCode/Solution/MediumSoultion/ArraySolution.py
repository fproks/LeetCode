# coding=utf-8
import math
from typing import List
import collections
from copy import copy
import heapq
import sys


class Node:
    def __init__(self, val, isLeaf, topLeft, topRight, bottomLeft, bottomRight):
        self.val = val
        self.isLeaf = isLeaf
        self.topLeft = topLeft
        self.topRight = topRight
        self.bottomLeft = bottomLeft
        self.bottomRight = bottomRight


class ArraySolution(object):
    def findPoisonedDuration(self, timeSeries, duration):
        """
        :type timeSeries: List[int]
        :type duration: int
        :rtype: int
        """
        ans = duration * len(timeSeries)
        for i in range(1, len(timeSeries)):
            ans -= max(0, duration - (timeSeries[i] - timeSeries[i - 1]))
        return ans

    def transpose(self, A):
        """
        :type A: List[List[int]]
        :rtype: List[List[int]]
        """
        R = len(A)
        C = len(A[0])
        trans = []
        for c in range(C):
            newRow = []
            for r in range(R):
                newRow.append(A[r][c])
            trans.append(newRow)
        return trans

    # 806. Number of Lines To Write String

    def numberOfLines(self, widths, S):
        pox = 0
        idx = 1
        for s in S:
            len = widths[ord(s) - ord('a')]
            if len + pox <= 100:
                pox = pox + len
            else:
                idx = idx + 1
                pox = len

        return [idx, pox]

    # 811. Subdomain Visit Count
    def subdomainVisits(self, cpdomains):
        """
        :type cpdomains: List[str]
        :rtype: List[str]
        """
        resultDict = {}
        for str in cpdomains:
            count = int(str.split(" ")[0])
            url = str.split(" ")[1]
            resultDict[url] += count
            for i in range(len(url)):
                if url[i] == '.':
                    resultDict[url[i + 1:]] += count
        result = []
        for (key, value) in resultDict.items():
            result.append('{0} {1}'.format(value, key))
        return result

    def leafSimilar(self, root1, root2):
        result1 = []
        result2 = []
        self._getLeftLeafValue(root1, result1)
        self._getLeftLeafValue(root2, result2)
        if result1 == result2:
            return True
        else:
            return False

    def _getLeftLeafValue(self, root, result):
        if root:
            if root.left:
                self._getLeftLeafValue(root.left, result)
            if root.right:
                self._getLeftLeafValue(root.right, result)
            if root.left is None and root.right is None:
                result.append(root.val)

    def sortArrayByParity(self, A):
        j = -1
        for i in range(len(A)):
            if A[i] % 2 == 0:
                j += 1
                A[i], A[j] = A[j], A[i]
        return A

    def sortArrayByPart(self, A):
        A.sort(key=lambda x: x % 2 == 0, reverse=True)
        return A

    def sortArrayByParityII(self, A):
        j = 0
        for i in range(len(A)):
            if A[i] % 2 == 0 and i % 2 != 0:
                while A[j] % 2 == 0:
                    j += 2
                A[i], A[j] = A[j], A[i]
        return A

    def smallestRangeI(self, A, K):
        return max(0, max(A) - min(A) - K * 2)

    def _allSame(self, grid: List[List[int]]) -> bool:
        hasTrue = False
        hasFalse = False
        for i in range(len(grid)):
            if 1 in grid[i]:
                hasTrue = True
            if 0 in grid[i]:
                hasFalse = True
            if hasTrue & hasFalse:
                return False
        return True

    def construct(self, grid: List[List[int]]) -> Node:
        if len(grid) == 1:
            return Node(grid[0][0], True, None, None, None, None)
        if self._allSame(grid):
            return Node(grid[0][0], True, None, None, None, None)
        else:
            node = Node('*', False, None, None, None, None)
            mid = len(grid) // 2
            node.topLeft = self.construct([x[0:mid] for x in grid[0:mid]])
            node.topRight = self.construct([x[mid:] for x in grid[0:mid]])
            node.bottomLeft = self.construct([x[0:mid] for x in grid[mid:]])
            node.bottomRight = self.construct([x[mid:] for x in grid[mid:]])
            return node

    def surfaceArea(self, grid: List[List[int]]) -> int:
        result = 0
        for i in range(len(grid)):
            for j in range(len(grid)):
                if grid[i][j] != 0:
                    result += grid[i][j] * 4 + 2
                    if i != 0:
                        result -= min(grid[i - 1][j], grid[i][j])
                    if i < len(grid) - 1:
                        result -= min(grid[i][j], grid[i + 1][j])
                    if j != 0:
                        result -= min(grid[i][j - 1], grid[i][j])
                    if j < len(grid) - 1:
                        result -= min(grid[i][j], grid[i][j + 1])
        return result

    def deckRevealedIncreasing(self, deck):
        res = []

        for i in sorted(deck, reverse=True):
            if res:
                tmp = res.pop()
                res.insert(0, tmp)
            res.insert(0, i)
        return res

    @staticmethod
    def findAndReplacePattern(words: List[str], pattern: str) -> List[str]:

        res = []
        p = ArraySolution.toDigits(pattern)
        for word in words:
            if len(word) != len(pattern):
                continue
            if ArraySolution.toDigits(word) == p:
                res.append(word)
            # dw = {}
            # dp = {}
            # for i in range(len(word)):
            #     tmp = dw.get(word[i], None)
            #     if tmp is None:
            #         dw[word[i]] = pattern[i]
            #     else:
            #         if tmp is not pattern[i]:
            #             break
            #     tmp = dp.get(pattern[i], None)
            #     if tmp is None:
            #         dp[pattern[i]] = word[i]
            #     else:
            #         if tmp is not word[i]:
            #             break
            #     if i is len(word) - 1:
            #         res.append(word)

        return res

    @staticmethod
    def toDigits(x):
        '''
        字典长度+1
        p ={}
        p[x] =len(p)+1
        :param x:
        :return:
        '''
        p = collections.defaultdict(lambda: len(p) + 1)
        return ''.join(str(p[i]) for i in x)

    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        result = []

        def DFS(graph: List[List[int]], idx: int, road: List[int], result: List[List[int]]):
            if idx == len(graph) - 1:
                result.append([] + road)
                return

            for i in graph[idx]:
                road.append(i)
                DFS(graph, i, road, result)
                road.remove(i)

        DFS(graph, 0, [0], result)
        return result

    def allPathsSourceTarget1(self, graph: List[List[int]], s=0) -> List[List[int]]:
        return [[len(graph) - 1]] if s == len(graph) - 1 else [[s] + path for i in graph[s] for path in
                                                               self.allPathsSourceTarget1(graph, s=i)]

    # 861. Score After Flipping Matrix
    # 这个题，主要是说，按行或者按列互换01，然后求和最大 数组位置不能移动，但是同一行或者同一列数字可以反转
    def matrixScore(self, A: List[List[int]]) -> int:
        res = 0
        for x in range(len(A)):
            if A[x][0] != 1:
                for y in range(len(A[x])):
                    A[x][y] = A[x][y] ^ 1
        A = list(map(list, zip(*A)))  # 矩阵转置

        for x in range(len(A)):
            if A[x].count(1) < A[x].count(0):
                for y in range(len(A[x])):
                    A[x][y] = A[x][y] ^ 1

        A = list(map(list, zip(*A)))
        for i in A:
            res += int(''.join(map(str, i)), 2)
        return res

    def spiralMatrixIII(self, R: int, C: int, r0: int, c0: int) -> List[List[int]]:
        def appendStep(R, C, x, y, dir, step, result):
            while len(result) < R * C:
                for dict in range(dir, dir + 2):
                    if dict % 4 == 1:
                        if x >= 0:
                            for y1 in range(y + 1, y + step + 1):
                                if 0 <= y1 < C:
                                    result.append([x, y1])
                        y = y + step
                    if dict % 4 == 2:
                        if y < C:
                            for x1 in range(x + 1, x + step + 1):
                                if 0 <= x1 < R:
                                    result.append([x1, y])
                        x = x + step
                    if dict % 4 == 3:
                        if x < R:
                            for y1 in range(y - 1, y - step - 1, -1):
                                if 0 <= y1 < C:
                                    result.append([x, y1])
                        y = y - step
                    if dict % 4 == 0:
                        if y >= 0:
                            for x1 in range(x - 1, x - step - 1, -1):
                                if 0 <= x1 < R:
                                    result.append([x1, y])
                        x = x - step
                dir += 2
                step += 1

        result = [[r0, c0]]
        appendStep(R, C, r0, c0, 1, 1, result)
        return result

    # 1104. Path In Zigzag Labelled Binary Tree
    # https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
    def pathInZigZagTree(self, label: int):
        def findlast(label):  # 先找到父节点
            c = 0
            while 2 ** c < label:
                c += 1
            if 2 ** c == label:
                return label - 1
            return int(2 ** (c - 1) - (label - (2 ** (c - 1)) + 1) / 2)

        result = []
        last = label
        while True:
            result.insert(0, last)
            if last > 1:
                last = findlast(last)
            else:
                break
        return result

    # 912. Sort an Array
    # qiuck sort
    def sortArray(self, nums: List[int]) -> List[int]:
        def quickSort(nums: List[int], start, end):
            if start >= end:
                return
            i = start
            j = end
            key = nums[i]
            while i < j:
                while i < j and nums[j] > key:
                    j -= 1
                nums[i] = nums[j]
                while i < j and nums[i] <= key:
                    i += 1
                nums[j] = nums[i]
            nums[j] = key
            quickSort(nums, start, i - 1)
            quickSort(nums, j + 1, end)

        quickSort(nums, 0, len(nums) - 1)
        return nums

    def intervalIntersection(self, A: List[List[int]], B: List[List[int]]) -> List[List[int]]:
        i = 0
        result = []

        for a in A:
            while i < len(B):
                if B[i][1] >= a[0] and B[i][0] <= a[1]:
                    result.append([max(a[0], B[i][0]), min(a[1], B[i][1])])
                    if B[i][1] >= a[1]:
                        break
                    else:
                        i += 1
                elif B[i][0] > a[1]:
                    break
                elif B[i][1] < a[1]:
                    i += 1
        return result

    # 1043. Partition Array for Maximum Sum  动态规划
    # https://www.acwing.com/solution/leetcode/content/2000/
    def maxSumAfterPartitioning(self, A: List[int], K: int) -> int:
        dp = [0] * (len(A) + 1)
        for i in range(1, len(A) + 1):
            currMax = A[i - 1]
            for k in range(1, K + 1):
                if k <= i:
                    currMax = max(currMax, A[i - k])
                    dp[i] = max(dp[i], dp[i - k] + currMax * k)
                else:
                    break
        return dp[-1]

    # 969. Pancake Sorting
    # https://leetcode.com/problems/pancake-sorting/
    # 先将最大位置的反转到最开始，然后再反转到最末尾。
    # 删除最末尾的

    def pancakeSort(self, A: List[int]) -> List[int]:
        tmp = A
        result = []
        while len(tmp) > 0:
            tmpMax = max(tmp)
            if tmp[-1] != tmpMax:
                idx = tmp.index(tmpMax) + 1
                if idx > 1:
                    result.append(idx)
                result.append(len(tmp))
                B = tmp[idx:]
                B.reverse()
                tmp = B + tmp[0:idx - 1]
            else:
                tmp = tmp[:-1]
        return result

    # 973. K Closest Points to Origin
    # 寻找最小的k个元素，或者寻找最大K个元素，一般用大顶堆或者小顶堆
    def kClosest(self, points: List[List[int]], K: int):
        class littleHype:
            def __init__(self, k):
                self.k = k
                self.data = []

            def push(self, elem):
                idx = (elem[0] * elem[0] + elem[1] * elem[1]) * -1
                if len(self.data) < self.k:
                    heapq.heappush(self.data, (idx, elem))
                else:
                    if self.data[0][0] < idx:
                        heapq.heapreplace(self.data, (idx, elem))

            def topk(self):
                return list(map(lambda x: x[1], self.data))

        hype = littleHype(K)
        for point in points:
            hype.push(point)
        return hype.topk()

    def stoneGame(self, piles: List[int]) -> bool:
        def f(x):
            if x % 2 == 1:
                return piles[x]
            else:
                return 0

        summms = sum(piles)
        piles = sorted(piles)
        tmp = sum(list(map(f, range(len(piles)))))
        return tmp + tmp > summms

    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        countx = 1
        opened = [False] * len(rooms)
        opened[0] = True
        waitForSet = {x for x in rooms[0]}
        while len(waitForSet) > 0:
            key = waitForSet.pop()
            if not opened[key]:
                opened[key] = True
                countx += 1
                for i in rooms[key]:
                    if not opened[i]:
                        waitForSet.add(i)
        return countx == len(rooms)

    # 1140. Stone Game II
    # a 获取的最大的等于总和减去 b 在所有情况中获取的最大的最小的 minmax。
    # https://blog.csdn.net/zjucor/article/details/97612737
    def stoneGameII(self, piles: List[int]) -> int:
        memo = {}

        def helper(a, m):
            if len(a) == 0:
                return 0
            if (len(a), m) in memo:
                return memo[(len(a), m)]
            res = 0
            for i in range(1, min(2 * m, len(a)) + 1):
                tmp = sum(a) - helper(a[i:], max(m, i))
                res = max(res, tmp)
            memo[(len(a), m)] = res
            return res

        return helper(piles, 1)

    # 946. Validate Stack Sequences
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stack = []
        while pushed or popped:
            if not stack:
                stack.append(pushed.pop(0))
            elif stack and stack[-1] == popped[0]:
                stack.pop()
                popped.pop(0)
            elif stack and pushed and stack[-1] != popped[0]:
                stack.append(pushed.pop(0))
            else:
                break
        if not stack and not pushed and not popped:
            return True
        return False

    # 1072. Flip Columns For Maximum Number of Equal Rows
    def maxEqualRowsAfterFlips(self, matrix: List[List[int]]) -> int:
        hashMap = collections.defaultdict(int)
        for row in matrix:
            hashMap["".join(str(i) for i in row)] += 1
            hashMap["".join(str(i ^ 1) for i in row)] += 1

        res = 0
        for k, v in hashMap.items():
            res = max(res, v)
        return res

    # 1017. Convert to Base -2
    def baseNeg2(self, N: int) -> str:
        bs = bin(N)[2:][::-1]
        res = [int(i) for i in list(bs)]

        def carrier(res, i):
            if i == len(res) - 1:
                res += [1]
                if len(res) % 2 == 0:
                    res += [1]
            else:
                res[i + 1] += 1

        for i in range(1, len(res)):
            if res[i] == 0:
                continue
            elif res[i] == 1 and i % 2 == 1:
                carrier(res, i)
            elif res[i] == 2:
                res[i] = 0
                carrier(res, i)
        return ''.join([str(i) for i in res[::-1]])

    def escapeGhosts(self, ghosts: List[List[int]], target: List[int]) -> bool:
        dist_my = abs(target[0]) + abs(target[1])
        for g in ghosts:
            if abs(g[0] - target[0]) + abs(g[1] - target[1]) <= dist_my:
                return False
        return True

    # 1122. Relative Sort Array
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        res = []
        for i in arr2:
            res = res + [i] * arr1.count(i)
        return res + sorted(filter(lambda x: arr2.count(x) == 0, arr1))

    # 1200. Minimum Absolute Difference
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        result = []
        remin = sys.maxsize
        for i in range(len(arr) - 1):
            a = min(arr[i], arr[i + 1])
            b = max(arr[i], arr[i + 1])
            if b - a < remin:
                result = []
                result.append([a, b])
                remin = b - a
                continue
            if b - a == remin:
                result.append([a, b])
        return result

    def minCostToMoveChips(self, chips: List[int]) -> int:
        even = 0
        for pos in chips:
            if pos % 2 == 0:
                even += 1
        return min(len(chips) - even, even)

    def mctFromLeafValues(self, arr: List[int]) -> int:
        res = 0
        idx = 0
        while len(arr) > 1:
            currMin = sys.maxsize
            for i in range(len(arr) - 1):
                if currMin > arr[i] * arr[i + 1]:
                    currMin = arr[i] * arr[i + 1]
                    idx = arr[i] if arr[i] < arr[i + 1] else arr[i + 1]
            res += currMin
            arr.remove(idx)
        return res

    # 1089. Duplicate Zeros
    def duplicateZeros(self, arr: List[int]) -> None:
        n = len(arr)
        i = 0
        while i < n - 1:
            if arr[i] == 0:
                arr.insert(i, 0)
                i += 2
                arr.pop(len(arr) - 1)
            else:
                i += 1

    def queensAttacktheKing(self, queens: List[List[int]], king: List[int]) -> List[List[int]]:
        d = dict(zip(['L', 'R', 'T', 'B', 'TL', 'TR', 'BL', 'BR'], [None] * 8))
        for queen in queens:
            if queen[0] == king[0] and queen[1] > king[1]:
                if d['R'] == None or d['R'][1] > queen[1]:
                    d['R'] = queen
            elif queen[0] == king[0] and queen[1] < king[1]:
                if d['L'] == None or d['L'][1] < queen[1]:
                    d['L'] = queen
            elif queen[1] == king[1] and queen[0] > king[0]:
                if d['B'] == None or d['B'][0] > queen[0]:
                    d['B'] = queen
            elif queen[1] == king[1] and queen[0] < king[0]:
                if d['T'] == None or d['T'][0] < queen[0]:
                    d['T'] = queen
            elif queen[0] - king[0] == queen[1] - king[1] and queen[0] > king[0]:
                if d['BR'] is None or d['BR'][0] > queen[0]:
                    d['BR'] = queen
            elif queen[0] - king[0] == queen[1] - king[1] and queen[0] < king[0]:
                if d['TL'] == None or d['TL'][0] < queen[0]:
                    d['TL'] = queen
            elif queen[0] - king[0] == -1 * (queen[1] - king[1]) and queen[0] > king[0]:
                if d['BL'] == None or d['BL'][0] > queen[0]:
                    d['BL'] = queen
            elif queen[0] - king[0] == -1 * (queen[1] - king[1]) and queen[0] < king[0]:
                if d['TR'] is None or d['TR'][0] < queen[0]:
                    d['TR'] = queen
            output = [x for x in d.values() if x is not None]
        return output

    def findSolution(self, customfunction: 'CustomFunction', z: int) -> List[List[int]]:
        res = []
        for x in range(1, z + 1):
            y_low, y_high = 1, z
            while (y_low <= y_high):
                y_mid = math.floor((y_low + y_high) / 2)
                curr = customfunction.f(x, y_mid)
                if curr < z:
                    y_low = y_mid + 1
                if curr > z:
                    y_high = y_mid - 1
                if curr == z:
                    res.append([x, int(y_mid)])
                    break
        return res

    def oddCells(self, n: int, m: int, indices: List[List[int]]) -> int:
        arr = [([0] * m) for i in range(n)]
        for ri, ci in indices:
            arr[ri] = [v + 1 for v in arr[ri]]
            for i in range(n):
                arr[i][ci] += 1
        return len(arr[i][j] for i in range(n) for j in range(m) if arr[i][j] % 2 == 1)

    class sole:
        def maxScoreWords(self, words, letters, score):
            self.max_score = 0
            words_score = [sum(score[ord(c) - ord('a')]
                               for c in word) for word in words]
            words_counter = [collections.Counter(word) for word in words]

            def dfs(i, curr_score, counter):
                if curr_score + sum(words_score[i:]) <= self.max_score:
                    return
                self.max_score = max(self.max_score, curr_score)
                for j, wcnt in enumerate(words_counter[i:], i):
                    if all(n <= counter.get(c, 0) for c, n in wcnt.items()):
                        dfs(j + 1, curr_score + words_score[j], {
                            c: n - wcnt.get(c, 0) for c, n in counter.items()})

            dfs(0, 0, collections.Counter(letters))
            return self.max_score

    # [241] 为运算表达式设计优先级
    def diffWaysToCompute(self, input: str) -> List[int]:
        if input.isdigit():
            return [int(input)]
        res = []
        for i, char in enumerate(input):
            if char in ['-', '+', '*']:
                left = self.diffWaysToCompute(input[:i])
                right = self.diffWaysToCompute(input[i + 1:])
                for l in left:
                    for r in right:
                        if char == '+':
                            res.append(l + r)
                        if char == '-':
                            res.append(l - r)
                        if char == '*':
                            res.append(l * r)
        return res

    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []
        if n == 0:
            return res

        def dfs(row, col, master, salve, cur_res, n):
            if row == n:
                res.append(["-" * cur + "Q" + "-" * (n - cur - 1)
                           for cur in cur_res])
                return
            for i in range(n):
                if (i not in col) and (i + row not in salve) and (i - row not in cur_res):
                    dfs(row + 1, col | {i}, master | {i - row},
                        salve | {i + row}, cur_res + [i], n)

        dfs(0, set(), set(), set(), [], n)
        return res

    def kidsWithCandies(self, candies: List[int], extraCandies: int) -> List[bool]:
        max_num = max(candies)
        res = []
        for i in candies:
            if i + extraCandies >= max_num:
                res.append(True)
            else:
                res.append(False)
        return res

    # 1450. Number of Students Doing Homework at a Given Time
    def busyStudent(self, startTime: List[int], endTime: List[int], queryTime: int) -> int:
        count = 0
        for i in range(len(startTime)):
            if startTime[i] <= queryTime <= endTime[i]:
                count += 1
        return count

    # 1313. Decompress Run-Length Encoded List
    def decompressRLElist(self, nums: List[int]) -> List[int]:
        res = list()
        for i in range(0, len(nums), 2):
            res.extend([nums[i + 1]] * nums[i])
        return res

    # 560. Subarray Sum Equals K
    def subarraySum(self, nums: List[int], k: int) -> int:
        _map = collections.defaultdict(int)
        _map[0] = 1
        count = 0
        presum = 0
        for i in nums:
            presum += i
            count += _map[presum - k]
            _map[presum] = _map[presum] + 1
        return count

    # 287. Find the Duplicate Number 二分法
    def findDuplicate(self, nums: List[int]) -> int:
        start = 1
        end = len(nums) - 1
        while start != end:
            mid = (end + start) // 2
            count = 0
            for i in nums:
                if start <= i <= mid:
                    count += 1
            if count > (mid - start + 1):
                end = mid
            else:
                start = mid + 1
        return start

    # 974. Subarray Sums Divisible by K
    def subarraysDivByK(self, A: List[int], K: int) -> int:
        _map = collections.defaultdict(int)
        _map[0] = 1
        count = 0
        preSum = 0
        for i in A:
            preSum = (preSum + i) % K
            count += _map[preSum]
            _map[preSum] = _map[preSum] + 1
        return count

    # 198. House Robber
    def rob(self, nums: List[int]) -> int:
        dp = [nums[0], max(nums[0], nums[1])]
        for i in range(2, len(nums)):
            dp.append(max(dp[i - 1], dp[i - 2] + nums[i]))
        return dp[-1]

    # 837. New 21 Game
    def new21Game(self, N: int, K: int, W: int) -> float:
        dp = [None] * (K + W)
        tmp = 0
        for i in range(K, K + W):
            if i <= N:
                dp[i] = 1
                tmp += 1
            else:
                dp[i] = 0
        for i in range(K - 1, -1, -1):
            dp[i] = tmp / W
            tmp = tmp + dp[i] - dp[i + W]
        return dp[0]

    # 238. Product of Array Except Self
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        length = len(nums)
        result = [1] * length
        for i in range(1, length):
            result[i] = result[i - 1] * nums[i - 1]
        tmp = 1
        for i in range(length - 2, -1, -1):
            tmp = tmp * nums[i + 1]
            result[i] = result[i] * tmp
        return result

    def searchInsert(self, nums: List[int], target: int) -> int:
        n = len(nums)
        if n == 0:
            return 0
        if nums[n - 1] < target:
            return n
        left = 0
        end = n - 1
        while left < end:
            mid = (left + end) // 2
            if nums[mid] < target:
                left = mid + 1
            else:
                end = mid
        return left

    def findNumberIn2DArray(self, matrix: List[List[int]], target: int) -> bool:
        row = len(matrix)
        if row == 0:
            return False
        col = len(matrix[0])
        if col == 0:
            return False
        r = row - 1
        c = 0
        while r >= 0 and c < col:
            if matrix[r][c] > target:
                r -= 1
                continue
            if matrix[r][c] == target:
                return True
            if matrix[r][c] < target:
                c += 1
                continue
        return False

    def printNumbers(self, n: int) -> List[int]:
        return list(range(1, 10 ** n))

    def sumOfUnique(self, nums: List[int]) -> int:
        return sum(num for num, cnt in collections.Counter(nums).items() if cnt == 1)

    def frequencySort(self, nums: List[int]) -> List[int]:
        return sorted(nums, key=lambda x: (nums.count(x), -x))

    def getFolderNames(self, names: List[str]) -> List[str]:
        result, map = [], {}
        for name in names:
            tmp = name
            if tmp in map:
                tmp = f"{name}({map[name]})"
                map[name] += 1
            map[name] = 1
            result.append(tmp)
        return result

    def findReplaceString(self, s: str, indices: List[int], sources: List[str], targets: List[str]) -> str:
        indicesMap = []
        for i in range(len(indices)):
            indicesMap.append((indices[i], i))
        indicesMap = sorted(indicesMap)
        res = s
        zlen = 0
        for k, v in indicesMap:
            slen = len(sources[v])
            if s[k:].startswith(sources[v]):
                res = res[0:k + zlen] + targets[v] + res[k + slen + zlen:]
                zlen = zlen + len(targets[v]) - len(sources[v])
        return res

    def maxSizeSlices(self, slices: List[int]) -> int:
        n = len(slices)
        k = n // 3

        def maxSumSlices(slices: List[int]) -> int:
            m = len(slices)
            dp = [[0] * (k + 1) for _ in range(m + 1)]
            for i in range(1, m + 1):
                for j in range(1, k + 1):
                    dp[i][j] = max(dp[i - 1][j], dp[i - 2]
                                   [j - 1] + slices[i - 1])
            return dp[m][k]

        max1 = maxSumSlices(slices[:-1])
        max2 = maxSumSlices(slices[1:])
        return max(max1, max2)

    def countServers(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        row, col = [0] * m, [0] * n
        dic = []
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    row[i] += 1
                    col[j] += 1
                    dic.append((i, j))
        result = 0
        for i, j in dic:
            if row[i] > 1 or col[j] > 1:
                result += 1
        return result

    def numFactoredBinaryTrees(self, arr: List[int]) -> int:
        s = set(arr)
        mod = 10**9+7

        def dfs(u):
            res = 1
            for x in s:
                r = u/x
                if r in s:
                    res += dfs(r)*dfs(x)
                    res %= mod
            return res
        ans = 0
        for x in arr:
            ans += dfs(x)
            ans %= mod
        return ans

    def minTrioDegree(self, n: int, edges: List[List[int]]) -> int:
        g = [[0]*(n+1) for _ in range(n+1)]
        ans = 8000
        deg = [0]*(n+1)
        for u, v in edges:
            g[u][v] = 1
            g[v][u] = 1
            deg[u] += 1
            deg[v] += 1
        for i in range(n+1):
            for j in range(i+1, n+1):
                for k in range(j+1, n+1):
                    if g[i][j] == g[j][k] == g[i][k] == 1:
                        ans = min(ans, deg[i]+deg[j]+deg[k]-6)
        if ans == 800:
            return -1
        return ans

    def waysToBuyPensPencils(self, total: int, cost1: int, cost2: int) -> int:
        result = 0
        total1 = total//cost1
        for i in range(total1+1):
            r = total-i*cost1
            result += r//cost2+1
        return result

    def repairCars(self, ranks: List[int], cars: int) -> int:
        left = 0
        right = ranks[0]*cars*cars
        while left <= right:
            mid = (left+right)//2
            count = 0
            for i in ranks:
                count += int(sqrt(mid/i))
            if count >= cars:
                right = mid
            else:
                left = mid+1
        return left


if __name__ == '__main__':
    # print(ArraySolution.findAndReplacePattern(["abc", "deq", "mee", "aqq", "dkd", "ccc"], "abb"))
    # print(ArraySolution.toDigits("abcddddddddd"))
    print(ArraySolution.findReplaceString(
        "abcd", [0, 2], ['a', 'cd'], ['eee', 'fff']))
    print(ArraySolution.findReplaceString(
        "abcd", [0, 2], ['ab', 'ec'], ['eee', 'ffff']))

# coding=utf-8
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
            if len(word) != len(pattern): continue
            if ArraySolution.toDigits(word) == p: res.append(word)
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
                                if 0 <= y1 < C: result.append([x, y1])
                        y = y + step
                    if dict % 4 == 2:
                        if y < C:
                            for x1 in range(x + 1, x + step + 1):
                                if 0 <= x1 < R: result.append([x1, y])
                        x = x + step
                    if dict % 4 == 3:
                        if x < R:
                            for y1 in range(y - 1, y - step - 1, -1):
                                if 0 <= y1 < C: result.append([x, y1])
                        y = y - step
                    if dict % 4 == 0:
                        if y >= 0:
                            for x1 in range(x - 1, x - step - 1, -1):
                                if 0 <= x1 < R: result.append([x1, y])
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
            while 2 ** c < label: c += 1
            if 2 ** c == label: return label - 1
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
            if start >= end: return
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
            if len(a) == 0: return 0
            if (len(a), m) in memo: return memo[(len(a), m)]
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
        even=0
        for pos in chips:
            if pos %2==0:
                even+=1
        return  min(len(chips)-even,even)


if __name__ == '__main__':
    print(ArraySolution.findAndReplacePattern(["abc", "deq", "mee", "aqq", "dkd", "ccc"], "abb"))
    print(ArraySolution.toDigits("abcddddddddd"))

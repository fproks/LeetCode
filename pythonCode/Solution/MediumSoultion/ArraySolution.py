# coding=utf-8
from typing import List
import collections
from copy import copy


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


if __name__ == '__main__':
    print(ArraySolution.findAndReplacePattern(["abc", "deq", "mee", "aqq", "dkd", "ccc"], "abb"))
    print(ArraySolution.toDigits("abcddddddddd"))

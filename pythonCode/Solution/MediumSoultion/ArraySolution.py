# coding=utf-8
from typing import List


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

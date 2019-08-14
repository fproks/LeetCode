class StringSolution:

    def repeatedStringMatch(self, A: str, B: str) -> int:
        tmp = A, start = 0
        for i in range(100):
            tmp += A
            while len(tmp) > len(B):
                if tmp.index(B, max(start, 0)) > 0:
                    return i
                else:
                    start = tmp.index(B[0], start)
        return -1

    def minAddToMakeValid(self, S: str) -> int:
        f = 0
        res = 0
        for c in S:
            if c == '(':
                f += 1
            else:
                if f == 0:
                    res += 1
                else:
                    f -= 1
        return res + f

    # 1016. Binary String With Substrings Representing 1 To N
    def queryString(self, S: str, N: int) -> bool:
        for x in range(1, N + 1):
            x_str = bin(x).replace('0b', '')
            if S.find(x_str) == -1:
                return False
        return True

    # https://www.cnblogs.com/wkfvawl/p/9362287.html
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        if len(text1) == 0 or len(text2) == 0: return 0
        if len(text1) == 1 and text2.find(text1): return 1
        if len(text2) == 1 and text1.find(text2): return 1
        if text1[-1] == text2[-1]: return self.longestCommonSubsequence(text1[:-1], text2[:-1]) + 1
        return max(self.longestCommonSubsequence(text1, text2[:-1]), self.longestCommonSubsequence(text1[:-1], text2))

    def longestCommonSubsequenceDP(self, text1: str, text2: str) -> int:
        if len(text1) == 0 or len(text2) == 0: return 0
        dp = [[0] * (len(text2) + 1) for i in range(len(text1) + 1)]
        for i in range(1, len(text1) + 1):
            for j in range(1, len(text2) + 1):
                if text1[i - 1] == text2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1  # 相等时，当前结果等于text1[:-1] 与text2[:-1]的结果+1
                else:
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]) # 不等时，等于(text1[:-1],text2) 和(text1,text2[:-1])最大的那个
        return dp[-1][-1]

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

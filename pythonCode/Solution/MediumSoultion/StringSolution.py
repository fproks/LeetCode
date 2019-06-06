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

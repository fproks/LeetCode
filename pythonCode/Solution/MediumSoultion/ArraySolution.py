# coding=utf-8


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
        R =len(A)
        C =len(A[0])
        trans =[]
        for c in range(C):
            newRow =[]
            for r in range(R):
                newRow.append(A[r][c])
            trans.append(newRow)
        return trans



#806. Number of Lines To Write String

    def numberOfLines(self, widths, S):
        pox=0
        idx=1
        for s in S:
            len =widths[ord(s)-ord('a')]
            if len +pox <=100:
                pox=pox+len
            else:
                idx=idx+1
                pox=len

        return  [idx,pox]


#811. Subdomain Visit Count
    def subdomainVisits(self, cpdomains):
        """
        :type cpdomains: List[str]
        :rtype: List[str]
        """
        resultDict ={}
        for str in cpdomains:
            count =int(str.split(" ")[0])
            url =str.split(" ")[1]
            resultDict[url]+=count
            for i in range(len(url)):
                if url[i]=='.':
                    resultDict[url[i+1:]]+=count
        result=[]
        for (key,value) in resultDict.items():
            result.append('{0} {1}'.format(value,key))
        return  result

    def leafSimilar(self, root1, root2):
        result1=[]
        result2=[]
        self._getLeftLeafValue(root1,result1)
        self._getLeftLeafValue(root2,result2)
        if result1==result2: return  True
        else: return  False



    def _getLeftLeafValue(self,root,result):
        if root:
            if root.left:
                self._getLeftLeafValue(root.left,result)
            if root.right:
                self._getLeftLeafValue(root.right,result)
            if root.left==None and root.right==None:
                result.append(root.val)
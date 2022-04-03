class Solution {
   static imageSmoother(img: number[][]): number[][] {
       const  res =Array.from({length:img.length},()=>Array.from({length:img[0].length},()=>0))
        for (let i =0;i<img.length;i++) {
            for (let j=0;j<img[i].length;j++){
                let cnt=0
            }
        }
       return  res
    }

}


function trailingZeroes(n: number): number {
    let n3 =Math.floor(n/1000)
    let n2=Math.floor(n/100)-n3
    let n1=Math.floor(n/10)-n2-n3
    let n5=Math.floor(n/5)-Math.floor(n/10)
    return  n3*3+n2*2+n1+n5

}
//console.log(Solution.imageSmoother([[100,200,100],[200,50,200],[100,200,100]]))
console.log(trailingZeroes(5))




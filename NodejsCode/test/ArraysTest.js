const ArraySolution =require("../module/ArraSolution")
const  assert =require("assert")
let arraySolution;
describe("Array solution",async ()=>{
   before(async ()=>{
       arraySolution =new ArraySolution();
   });

   it("findShortestSubArray",()=>{
       assert.equal(2,arraySolution.findShortestSubArray([1, 2, 2, 3, 1]));
   })

});
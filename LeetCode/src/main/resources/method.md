#**一些零散的方法**
* int[] to List:

`IntStream.of(nums).boxed().collect(Collectors.toList())`

* List to int[]:

`list.stream().mapToInt(j->j).toArray()`

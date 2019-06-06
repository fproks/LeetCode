package LeetCode.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @user: linhos
 * @Time: Create in 11:48 2017/9/21
 */
public class ArraysSolutionTest {
    private static final ArraysSolution arraysolutin = new ArraysSolution();

    @Test
    public void findLHS() throws Exception {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(5, solution.findLHS(nums));
    }

    @Test
    public void canPlaceFlowersTest() throws Exception {
        int[] flowered = {1, 0, 0, 0, 1};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertTrue(solution.canPlaceFlowers(flowered, 1));
    }

    @Test
    public void findMaxAverageTest() throws Exception {
        int[] nums = {1, 12, -5, -6, 50, 3};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(12.75, solution.findMaxAverage(nums, 4), 0.01);
    }

    @Test
    public void findErrorNumsTest() throws Exception {
        int[] nums = {3, 2, 4, 3};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertArrayEquals(new int[]{3, 1}, solution.findErrorNums(nums));
    }

    @Test
    public void imageSmootherTest() throws Exception {
        // int[][]M={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] M = {{6, 9, 7}};
        ArraysSolution solution = new ArraysSolution();
        int[][] m = solution.imageSmoother(M);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void maxAreaOfIsland() {
        int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        LeetCode.easy.Kotlin.ArraysSolution kotlinSolution = new LeetCode.easy.Kotlin.ArraysSolution();
        Assert.assertEquals(4, kotlinSolution.maxAreaOfIsland(grid));
    }

    @Test
    public void countBinarySubstrings() {
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(6, solution.countBinarySubstrings("00110011"));
        Assert.assertEquals(4, solution.countBinarySubstrings("10101"));
    }

    @Test
    public void nextGreatestLetter() {
        char[] letter = {'c', 'f', 'j'};
        char target = 'j';
        ArraysSolution solution = new ArraysSolution();
        char c = solution.nextGreatestLetter(letter, target);
        System.out.println(c);
    }

    @Test
    public void projectionArea() {
        int[][] input = {{2, 2, 2}, {2, 1, 2}, {2, 2, 2}};
        int[][] inut1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        ArraysSolution solution = new ArraysSolution();
        Assert.assertEquals(21, solution.projectionArea(input));
        Assert.assertEquals(14, solution.projectionArea(inut1));
    }

    @Test
    public void Compress() {
        char[] s = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        char[] a1 = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        char[] a2 = {'a', 'b', 'c'};
        Assert.assertEquals(6, arraysolutin.Compress(s));
        Assert.assertEquals(6, arraysolutin.Compress(a1));
        Assert.assertEquals(3, arraysolutin.Compress(a2));
    }

    @Test
    public void largestPerimeter() {
        int[] a = {2, 1, 2};
        Assert.assertEquals(5, arraysolutin.largestPerimeter(a));
        Assert.assertEquals(0, arraysolutin.largestPerimeter(new int[]{1, 2, 1}));
        Assert.assertEquals(10, arraysolutin.largestPerimeter(new int[]{3, 2, 3, 4}));
    }

    @Test
    public void fairCandySwap() {
        Assert.assertArrayEquals(new int[]{1, 2}, arraysolutin.fairCandySwap(new int[]{1, 1}, new int[]{2, 2}));
        Assert.assertArrayEquals(new int[]{1, 2}, arraysolutin.fairCandySwap(new int[]{1, 2}, new int[]{2, 3}));
        Assert.assertArrayEquals(new int[]{2, 3}, arraysolutin.fairCandySwap(new int[]{2}, new int[]{1, 3}));
        Assert.assertArrayEquals(new int[]{5, 4}, arraysolutin.fairCandySwap(new int[]{1, 2, 5}, new int[]{2, 4}));
    }

    @Test
    public void flatMap() {
        String[] str1 = new String[]{"test1", "test2", "test3"};
        String[] str2 = new String[]{"test3", "test4", "test5"};
        Set<String> set = Stream.of(str1, str2).flatMap(u -> Arrays.stream(u)).collect(Collectors.toSet());
        set.forEach(System.out::println);
    }

    @Test
    public void sumEvenAfterQueries() {
        int[] a = {1, 2, 3, 4};
        int[][] q = {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};
        int[] res = arraysolutin.sumEvenAfterQueries(a, q);
        System.out.println(Arrays.toString(res));
        Assert.assertArrayEquals(new int[]{8, 6, 2, 4}, res);
    }

    @Test
    public void sortedSquares() {
        int[] a = {-4, -1, 0, 3, 10};
        int[] res = {0, 1, 9, 16, 100};
        Assert.assertArrayEquals(new int[]{0, 1, 9, 16, 100}, arraysolutin.sortedSquares(a));
    }

    @Test
    public void allCellsDistOrder() {
        Arrays.stream(arraysolutin.allCellsDistOrder(2, 3, 1, 2)).forEach(val -> {
            System.out.println(Arrays.toString(val));
        });
    }

    @Test
    public void commonChars() {
        String[] a = {"cool", "lock", "cook"};
        List<String> b = arraysolutin.commonChars(a);
        System.out.println(b.size());
        for (String s : b) {
            System.out.println(s);
        }
    }

    @Test
    public void findJudge() {
        int[][] trust = {{1, 3}, {2, 3}};
        Assert.assertEquals(3, arraysolutin.findJudge(3, trust));
        Assert.assertEquals(-1, arraysolutin.findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
        Assert.assertEquals(-1, arraysolutin.findJudge(3, new int[][]{{1, 2}, {2, 3}}));
        Assert.assertEquals(3, arraysolutin.findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
    }

    @Test
    public void twoCitySchedCost() {
        int[][] a = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        int[][] b = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
        Assert.assertEquals(110, arraysolutin.twoCitySchedCost(a));
    }

    @Test
    public void lemonadeChange() {
        Assert.assertTrue(arraysolutin.lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        Assert.assertTrue(arraysolutin.lemonadeChange(new int[]{5, 5, 10}));
        Assert.assertFalse(arraysolutin.lemonadeChange(new int[]{10, 10}));
        Assert.assertFalse(arraysolutin.lemonadeChange(new int[]{5, 5, 10, 10, 20}));

    }

    @Test
    public void addToArrayForm() {
        int[] tmp = new int[0];
        Assert.assertArrayEquals(
                arraysolutin.addToArrayForm(new int[]{1, 2, 0, 0}, 34).stream().mapToInt(Integer::intValue).toArray(),
                new int[]{1, 2, 3, 4});
        Assert.assertArrayEquals(
                arraysolutin.addToArrayForm(new int[]{2, 7, 4}, 181).stream().mapToInt(Integer::intValue).toArray(),
                new int[]{4, 5, 5});
        Assert.assertArrayEquals(
                arraysolutin.addToArrayForm(new int[]{2, 1, 5}, 806).stream().mapToInt(Integer::intValue).toArray(),
                new int[]{1, 0, 2, 1});
        Assert.assertArrayEquals(
                arraysolutin.addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1).stream().mapToInt(Integer::intValue).toArray(),
                new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        Assert.assertArrayEquals(
                arraysolutin.addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 2).stream().mapToInt(Integer::intValue).toArray(),
                new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
    }

    @Test
    public void robotSim() {
        int[][] obs = new int[][]{};
        Assert.assertEquals(25, arraysolutin.robotSim(new int[]{4, -1, 3}, obs));
        Assert.assertEquals(65, arraysolutin.robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}));
        obs = new int[][]{{-1, 3}, {0, 1}, {-1, 5}, {-2, -4}, {5, 4}, {-2, -3}, {5, -1}, {1, -1}, {5, 5}, {5, 2}};
        Assert.assertEquals(0, arraysolutin.robotSim(new int[]{-2, -1, 8, 9, 6}, obs));
        obs = new int[][]{{-4, -1}, {1, -1}, {1, 4}, {5, 0}, {4, 5}, {-2, -1}, {2, -5}, {5, 1}, {-3, -1}, {5, -3}};
        Assert.assertEquals(324, arraysolutin.robotSim(new int[]{-2, 8, 3, 7, -1}, obs));
    }

}
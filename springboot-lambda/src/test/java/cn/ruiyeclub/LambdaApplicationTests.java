package cn.ruiyeclub;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Ray。
 * @Date: 2022/5/16
 */
@Slf4j
@SpringBootTest
public class LambdaApplicationTests {

    /**
     * 集合的遍历forEach方法
     */
    @Test
    public void testForEach() {
        List<String> list = Arrays.asList("1", "2", "3");

        // 遍历这个集合
        list.forEach(s -> System.out.println(s));
    }

    /**
     * 只要满足 Filter 表达式的数据就可以留下来，不满足的数据被过滤掉
     */
    @Test
    public void testFilter() {
        List<String> list = Arrays.asList("1", "2", "3");

        // 过滤掉我们希望留下来的值
        // 表示我们希望字符串是 1 能留下来
        // 其他的过滤掉
        list.stream()
                .filter(str -> "1".equals(str))
                .collect(Collectors.toList());
    }

    /**
     * 将操作后的对象转化为新的对象（转成long类型的list）
     */
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("1", "2", "3");

        // 将操作后的对象转化为新的对象
        List<Long> idList = list.stream()
                .map(str -> Long.valueOf(str))
                .collect(Collectors.toList());
        idList.forEach(s -> System.out.println(s));

        // DoubleStream/IntStream 有许多 sum（求和）、min（求最小值）、max（求最大值）、average（求平均值）等方法
        list.stream()
                .mapToDouble(s -> Double.valueOf(s))
                .sum();
    }

    /**
     * distinct 方法有去重的功能
     */
    @Test
    public void testDistinct() {
        List<String> list = Arrays.asList("1", "2", "2");

        // 去重
        List<String> collect = list.stream()
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(s -> System.out.println(s));
    }

    /**
     * 排序功能
     */
    @Test
    public void testSorted() {
        List<Integer> list = Arrays.asList(4, 1, 2, 2, 3, null);

        // Comparator.naturalOrder() 升序
        // Comparator.reverseOrder() 降序
        List<Integer> collect = list.stream()
                .filter(num -> num != null)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        collect.forEach(s -> System.out.println(s));
    }

    /**
     * 分组
     */
    @Test
    public void testGroupingBy() {
        List<String> list = Arrays.asList("1", "2", "2");

        // groupingBy 是能够根据字段进行分组
        Map<String, List<String>> collect = list.stream().collect(Collectors.groupingBy(s -> {
            if (s.equals("1")) {
                return "key1";
            } else {
                return "key2";
            }
        }));

        System.out.println(collect.get("key2"));
    }

    /**
     * findFirst 表示匹配到第一个满足条件的值就返回
     */
    @Test
    public void testFindFirst() {
        List<String> list = Arrays.asList("1", "2", "2");

        String s1 = list.stream()
                .filter(s -> s.equals("2"))
                .findFirst()
                .get();

        System.out.println(s1);
    }

    /**
     * limit 方法会限制输出值个数，入参是限制的个数大小
     */
    @Test
    public void testLimit() {
        List<String> list = Arrays.asList("1", "2", "2", "33");

        List<String> collect = list.stream()
                .limit(2)
                .collect(Collectors.toList());

        collect.forEach(s -> System.out.println(s));
    }

    /**
     * count 统计数量
     */
    @Test
    public void testCount() {
        List<String> list = Arrays.asList("1", "2", "2", "33");

        long count = list.stream()
                .filter(s -> s.equals("2"))
                .count();
        System.out.println(count);
    }

    /**
     * 求最大值和最小值
     */
    @Test
    public void testMaxAndMin() {
        List<String> list = Arrays.asList("1", "2", "2", "33");

        String s1 = list.stream().max(Comparator.comparing(s -> Integer.parseInt(s))).get();
        System.out.println(s1);

        String s2 = list.stream().min(Comparator.comparing(s -> Integer.parseInt(s))).get();
        System.out.println(s2);
    }
}

#每写一个，就添加一个数据库，成功之后删除数据库
#595. Big Countries
SELECT
  t.name,
  t.area,
  t.population
FROM world AS t
WHERE t.population > 25000000 OR t.area > 3000000;

#596. Classes More Than 5 Students
SELECT t.class
FROM courses t
GROUP BY class
HAVING count(DISTINCT t.student) > 4;

#620. Not Boring Movies
SELECT *
FROM cinema
WHERE id % 2 = 1 AND description != 'boring'
ORDER BY rating DESC;

#627. Swap Salary
UPDATE salary
SET sex =
CASE sex
WHEN 'f'
  THEN 'm'
ELSE 'f' END;
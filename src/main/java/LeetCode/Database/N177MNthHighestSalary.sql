Write a SQL query to get the nth highest salary from the Employee table.

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
For example, given the above Employee table, the nth highest salary where n = 2 is 200. If there is no nth highest salary, then the query should return null.

+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+



#注：
#1.变量的申明与使用
#Runtime: 284 ms, faster than 81.19% of MySQL online submissions for Nth Highest Salary.
#Memory Usage: 0B, less than 100.00% of MySQL online submissions for Nth Highest Salary.

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE M INT;
SET M = N-1;

  RETURN (
      # Write your MySQL query statement below.
      select distinct Salary as getNthHighestSalary
      from Employee order by Salary desc limit 1 offset M

  );
END
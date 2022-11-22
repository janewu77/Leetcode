Write a SQL query to get the second highest salary from the Employee table.

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
For example, given the above Employee table, the query should return 200 as the second highest salary. If there is no second highest salary, then the query should return null.

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+

###################################################################
### limit IFNULL 尽量尽早缩小范围
#1.
#Runtime: 165 ms, faster than 92.45% of MySQL online submissions for Second Highest Salary.
#Memory Usage: 0B, less than 100.00% of MySQL online submissions for Second Highest Salary.
select IFNULL(
(select distinct Salary from Employee order by Salary desc LIMIT 1 offset 1), NULL) as SecondHighestSalary

###################################################################
### rank limit IFNULL 尽量尽早缩小范围
#2.
#Runtime: 164 ms, faster than 93.51% of MySQL online submissions for Second Highest Salary.
#Memory Usage: 0B, less than 100.00% of MySQL online submissions for Second Highest Salary.

# select IFNULL((
# select SalaryRankList.Salary
# from (
# select  distinct Salary, rank() over (order by Salary desc ) as SalaryRank from Employee Limit 1 offset 1
# ) as SalaryRankList
# ),NULL) as SecondHighestSalary
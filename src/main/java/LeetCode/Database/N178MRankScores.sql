Write a SQL query to rank scores. If there is a tie between two scores,
both should have the same ranking. Note that after a tie,
the next ranking number should be the next consecutive integer value. In other words, there should be no "holes" between ranks.

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
For example, given the above Scores table, your query should
generate the following report (order by highest score):

+-------+---------+
| score | Rank    |
+-------+---------+
| 4.00  | 1       |
| 4.00  | 1       |
| 3.85  | 2       |
| 3.65  | 3       |
| 3.65  | 3       |
| 3.50  | 4       |
+-------+---------+
Important Note: For MySQL solutions, to escape reserved words used as column names,
you can use an apostrophe before and after the keyword. For example `Rank`.



# Write your MySQL query statement below


#Runtime: 214 ms, faster than 95.91% of MySQL online submissions for Rank Scores.
#Memory Usage: 0B, less than 100.00% of MySQL online submissions for Rank Scores.
select Score as score, dense_rank() over(order by Score desc) as "Rank"
from Scores
Table: Stadium

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| visit_date    | date    |
| people        | int     |
+---------------+---------+
visit_date is the primary key for this table.
Each row of this table contains the visit date and visit id to the stadium with the number of people during the visit.
No two rows will have the same visit_date, and as the id increases, the dates increase as well.


Write an SQL query to display the records with three or more rows with consecutive id's, and the number of people is greater than or equal to 100 for each.

Return the result table ordered by visit_date in ascending order.

The query result format is in the following example.



Stadium table:
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 1    | 2017-01-01 | 10        |
| 2    | 2017-01-02 | 109       |
| 3    | 2017-01-03 | 150       |
| 4    | 2017-01-04 | 99        |
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |
+------+------------+-----------+

Result table:
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |
+------+------------+-----------+
The four rows with ids 5, 6, 7, and 8 have consecutive ids and each of them has >= 100 people attended. Note that row 8 was included even though the visit_date was not the next day after row 7.
The rows with ids 2 and 3 are not included because we need at least three consecutive ids.


# Write your MySQL query statement below

#### 3.
# #Runtime: 346 ms, faster than 31.03% of MySQL online submissions for Human Traffic of Stadium.
# #Memory Usage: 0B, less than 100.00% of MySQL online submissions for Human Traffic of Stadium.
# select * from stadium
# where id = any
# (select s1.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100)

# or id = any
# (select sr.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100)

# or id = ANY

# (select sl.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100)


#### 2.
# Runtime: 627 ms, faster than 5.03% of MySQL online submissions for Human Traffic of Stadium.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Human Traffic of Stadium.
# select * from stadium
# where id in
# (select s1.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100

# UNION

# select sr.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100


# UNION

# select sl.id
# from stadium s1
# right join stadium sr on sr.id+1=s1.id
# left join stadium sl on s1.id+1=sl.id
# where s1.people >= 100 and sr.people >=100 and sl.people>=100)



#### 1.
# #Runtime: 293 ms, faster than 63.19% of MySQL online submissions for Human Traffic of Stadium.
# #Memory Usage: 0B, less than 100.00% of MySQL online submissions for Human Traffic of Stadium.

# select distinct s1.*
# from stadium s1, stadium s2, stadium s3
# where s1.people >= 100 and s2.people >= 100 and s3.people >= 100
# and(
#     (s1.id-s2.id=1 and s1.id-s3.id=2 and s2.id-s3.id=1)  #1,2,3
#     or
#     (s2.id-s1.id=1 and s2.id-s3.id=2 and s1.id-s3.id=1)  #2,1,3
#     or
#     (s3.id-s2.id=1 and s3.id-s1.id=2 and s2.id-s1.id=1)   #3,2,1
# )
# order by s1.id asc
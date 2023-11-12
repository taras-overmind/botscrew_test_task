# botscrew_test_task
https://www.linkedin.com/in/taras-korolchuk-a87393216/

Database: PostgreSQL

Spring Data JPA seemed an overkill for this task, so I reduced it's usage to minimum.

Models were creared to simplify database creation.

All queries were written in native SQL.

Database diagram:

![Записати](https://github.com/taras-overmind/botscrew_test_task/assets/72138768/51fb9288-5331-48d6-b6b1-88eff549c485)

As you can see Department has ManyToMany relation to Lector, since Lector can work at multiple departments and department has multiple employees.

Example of work

1.
User input: Who is head of department TTP

Answer: Head of TTP department is Nikitchenko

2.
User input: Show TK statistics

Answer:

assistants - 2

associate professors - 0

professor - 0

3.
User input: Show the average salary for the department MI

Answer: The average salary of MI is 14000.0

4.
User input: Show count of employee for TTP

Answer: 3

5.
User input: Global search by enko

Answer:

Lectors: Tkachenko, Bedenko A. M.

Heads of department: Nikitchenko

Departments: 

6.
User input: Some unknown command

Anser: Wrong command

7.
User input: Show the average salary for the department KTV (a department with no employees)

Answer: No employees

8.
User input: Who is head of department TT (no such department)

Answer: No such department


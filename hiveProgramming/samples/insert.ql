INSERT OVERWRITE TABLE employees
PARTITION (country = 'US', state = 'OR')
SELECT * FROM employ se
WHERE address.state = 'IL'; 

INSERT OVERWRITE TABLE employees_with_partition
PARTITION (country='UNKNOWN', state)
SELECT ...,se.address.state
FROM staged_employees se;

CREATE TABLE ca_employees
AS SELECT name, salary, address
FROM employees_with_partition se
WHERE se.country = 'CA';

INSERT OVERWRITE LOCAL DIRECTORY '/tmp/ca_employees'
SELECT name, salary, address
FROM employees_with_partition se
WHERE se.country = 'CA';

SELECT upper(name), salary, deductions["Federal Taxes"],
round(salary * (1 - deductions["Federal Taxes"])) FROM employees
LIMIT 2;

FROM (
SELECT upper(name) as name, salary, deductions["Federal Taxes"] as fed_taxes,
round(salary * (1 - deductions["Federal Taxes"])) as salary_minus_fed_taxes
FROM employees
) e
SELECT e.name, e.salary_minus_fed_taxes
WHERE e.salary_minus_fed_taxes > 70000;


SELECT name, salary,
CASE 
WHEN salary < 50000.0 THEN 'low'
WHEN salary >= 50000.0 AND salary < 70000.0 THEN 'middle'
WHEN salary >= 70000.0 AND salary < 100000.0 THEN 'high'
ELSE 'very high'
END AS bracket FROM employees;

create INDEX employees_index on table employees_with_partition ( country ) 
AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'
with DEFERRED REBUILD
in table employees_index_table 
partitioned by ( country, name )
comment 'Employees indexed by country and name.'
IDXPROPERTIES('creator' = 'me', 'created_at' = 'some_time')

CREATE INDEX employees_index
ON TABLE employees_with_partition (country)
AS 'BITMAP'
WITH DEFERRED REBUILD
IDXPROPERTIES ('creator' = 'me','created_at' = 'some_time')
IN TABLE employees_index_table
PARTITIONED BY (country, name)
COMMENT 'Employees indexed by country and name.';


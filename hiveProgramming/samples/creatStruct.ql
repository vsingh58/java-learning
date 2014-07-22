
hive> describe function struct;
OK
struct(col1, col2, col3, ...) - Creates a struct with the given field values

hive> create table funTBL AS                                                               
    > select name, struct(deductions,address.city,subordinates) AS ALLINONE from employees;
hive> select name, allinone.col1['State Taxes'], allinone.col2, allinone.col3[0] from funtbl;

John Doe	0.05	Chicago	Mary Smith
Mary Smith	0.5	Chicago	Bill King
Todd Jones	0.03	Oak Park	NULL
Bill King	0.03	Obscuria	NULL

ERROR
hive> create table funTBL2 ( name STRING, allInOne STRUCT<deductions:map<string,float>, city:STRING, subordinates:array<string>>) AS
    > select name, struct(deductions,address.city,subordinates) from employees            
    > ;
FAILED: SemanticException [Error 10065]: CREATE TABLE AS SELECT command cannot specify the list of columns for the target table

hive> create table funTBL2  AS
    > select name AS name, struct(deductions, address.city,subordinates, address) AS allInOne from employees;
hive> select name, allinone.col1['State Taxes'], allinone.col2, allinone.col3[0], allinone.col4.street,allinone.col4.city from funtbl2;
OK
John Doe	0.05	Chicago	Mary Smith	1 Michigan Ave.	Chicago
Mary Smith	0.5	Chicago	Bill King	100 Ontario St.	Chicago
Todd Jones	0.03	Oak Park	NULL	200 Chicago Ave.	Oak Park
Bill King	0.03	Obscuria	NULL	300 Obscure Dr.	Obscuria

select name, concat('Tax: ', allinone.col1['State Taxes'],'city: ', allinone.col2, 'user: ', allinone.col3[0], 'streat: ', allinone.col4.street,'city: ', allinone.col4.city) from funtbl2

Create namedStruct
hive> describe function extended named_struct;
OK
named_struct(name1, val1, name2, val2, ...) - Creates a struct with the given field names and values
hive> create table namedStruct  AS
    > select name, named_struct('deductions',deductions,'city', address.city,'subordinates',subordinates, 'address',address) AS allInOne from employees;


### Test Arabic on sql server using varchar and collation arabic_ci_as.

#### steps to run the demo:

1. To run the demo, you need sql server up and running, I use Docker to start sql server (you can run it against a real sql server):

```shell script
docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=yourStrong(!)Password' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-latest
```

2. Next step, you need to create the database:
```sql
create database mydb collate arabic_ci_as
go
```

3. Then run the app and check the logs, you will find the company name inserted into the table company and then retrieved in arabic.
(note the file src/main/resources/schema.sql it the explicit definition of the `company` table)

4. After running the app, you can use this query to see the sql server parameter binding for latest statements:

```sql
SELECT s2.text
FROM sys.dm_exec_query_stats AS S1
         CROSS APPLY sys.Dm_exec_sql_text(s1.sql_handle) AS S2
where lower(s2.text) like '%company%'
ORDER BY last_execution_time DESC;
```

it query will show that, the parameters is bound as `varchar(8000)` instead of `nvarchar(8000)`
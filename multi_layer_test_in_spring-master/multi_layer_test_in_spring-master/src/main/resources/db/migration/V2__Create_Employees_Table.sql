CREATE TABLE Employees(
`id` BIGINT ,
`name` VARCHAR(20),
`companies_id`  BIGINT,
`created_date` TIMESTAMP DEFAULT NOW()
)
CREATE TABLE `Companies` (
  `id`                 BIGINT       NOT NULL PRIMARY KEY,
  `name`         VARCHAR(20)  NOT NULL,
  `created_date` TIMESTAMP DEFAULT NOW()
);

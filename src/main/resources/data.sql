DROP TABLE IF EXISTS BANK_ACCOUNT;
DROP TABLE IF EXISTS CLIENT;

CREATE TABLE CLIENT (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    first_name VARCHAR(250) NOT NULL
);

CREATE TABLE BANK_ACCOUNT (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    bank_name VARCHAR(250) NOT NULL,
    bank_id VARCHAR(250) NOT NULL,
    balance VARCHAR(250),
    client_id INT,
    CONSTRAINT FK_BANK_ACCOUNT_CLIENT FOREIGN KEY (client_id) REFERENCES CLIENT(id)
);
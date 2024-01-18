CREATE TABLE Account (
                         id VARCHAR(9) PRIMARY KEY,
                         account_type VARCHAR(8) NOT NULL,
                         client_id VARCHAR(10) NOT NULL,
                         balance DOUBLE NOT NULL,
                         withdraw_allowed BOOLEAN NOT NULL
);

CREATE TABLE Transaction (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             account_id VARCHAR(10) NOT NULL,
                             transaction_type VARCHAR(10) NOT NULL,
                             amount DOUBLE NOT NULL,
                             transaction_date TIMESTAMP NOT NULL,
                             FOREIGN KEY (account_id) REFERENCES Account(id),
                             transaction_status BOOLEAN NOT NULL
);

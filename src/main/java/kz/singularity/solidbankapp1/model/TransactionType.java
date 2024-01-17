package kz.singularity.solidbankapp1.model;


public enum TransactionType {
    DEPOSIT ("DEPOSIT"), WITHDRAWAL ("WITHDRAWAL"), TRANSFER ("TRANSFER");

    TransactionType(String transactionType) {
    }
}

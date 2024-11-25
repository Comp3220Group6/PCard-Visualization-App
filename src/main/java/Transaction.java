public class Transaction {
    private String division;
    private String date;
    private String merchantName;
    private String transactionAmount;
    private String transactionPurpose;
    private String transactionID;
    private String glAccount;
    private String glAccountDesc;
    private String merchantType;



    public Transaction(String division, String date, String merchantName, String transactionAmount, String transactionPurpose, String transactionID, String glAccount, String glAccountDesc, String merchantType) {
        this.division = division;
        this.date = date;
        this.merchantName = merchantName;
        this.transactionAmount = transactionAmount;
        this.transactionPurpose = transactionPurpose;
        this.transactionID = transactionID;
        this.glAccount = glAccount;
        this.glAccountDesc = glAccountDesc;
        this.merchantType = merchantType;
    }


    public String getDate(){
        return date;
    }

    public String getDivision(){
        return division;
    }

    public String getMerchantName(){
        return merchantName;

    }
    public String getTransactionAmount(){
        return transactionAmount;
    }
    public String getTransactionPurpose(){
        return transactionPurpose;
    }
    public String getTransactionID(){
        return transactionID;
    }
    public String getGlAccount(){
        return glAccount;
    }
    public String getGlAccountDesc(){
        return glAccountDesc;
    }
    public String getMerchantType(){
        return merchantType;
    }

}

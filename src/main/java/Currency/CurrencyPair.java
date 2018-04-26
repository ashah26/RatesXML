package Currency;

/*** This class stores target rate and currancy name entered by user ***/

public class CurrencyPair {

    private String currencyPairName;
    private float targetRate;

    public String getCurrencyPairName() {
        return currencyPairName;
    }

    public float getTargetRate() {
        return targetRate;
    }

    public void setCurrencyPairName(String currencyPairName) {
        this.currencyPairName = currencyPairName;
    }

    public void setTargetRate(float targetRate) {
        this.targetRate = targetRate;
    }
}

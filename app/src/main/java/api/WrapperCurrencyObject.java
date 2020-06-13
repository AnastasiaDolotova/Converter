package api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WrapperCurrencyObject {

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("rates")
    @Expose
    private CurrencyObject currencyObject;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public CurrencyObject getCurrencyObject() {
        return currencyObject;
    }
}

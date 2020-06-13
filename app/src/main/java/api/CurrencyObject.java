package api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyObject {
    @SerializedName("RUB")
    @Expose
    private float rub;

    @SerializedName("USD")
    @Expose
    private float usd;

    @SerializedName("EUR")
    @Expose
    private float eur;

    @SerializedName("GBP")
    @Expose
    private float gbp;

    @SerializedName("CNY")
    @Expose
    private float cny;

    @SerializedName("SEK")
    @Expose
    private float sek;

    public float getRateFor(String cur){
        switch (cur){
            case "RUB":
                return rub;
            case "USD":
                return usd;
            case "EUR":
                return eur;
            case "GBP":
                return gbp;
            case "CNY":
                return cny;
            case "SEK":
                return sek;
        }
        return 1;
    }
}

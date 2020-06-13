package api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {
    @GET("/api/latest?base=RUB")
    Call<WrapperCurrencyObject> getRates(@Query("symbols") String symbols);
}

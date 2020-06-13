package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    public static Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
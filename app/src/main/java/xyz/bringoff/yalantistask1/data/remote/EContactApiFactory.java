package xyz.bringoff.yalantistask1.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EContactApiFactory {

    public static EContactApiService getEContactApiService() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EContactApiService.class);
    }
}

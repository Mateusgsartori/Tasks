package com.example.tasks.service.repository.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/";

    private static Retrofit retrofit;

    private RetrofitClient() {

    }

    private static Retrofit getRetroFitInstance(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
        }

        return retrofit;
    }


    public static <S> S createService(Class<S> sClass){
        return getRetroFitInstance().create(sClass);
    }




}

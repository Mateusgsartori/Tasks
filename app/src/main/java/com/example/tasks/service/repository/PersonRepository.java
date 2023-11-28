package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.local.SecurityPreferences;
import com.example.tasks.service.repository.remote.PersonService;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    private PersonService mPersonService;
    private SecurityPreferences mSecurityPreferences;
    private Context mContext;

    public PersonRepository(Context context) {
        this.mPersonService = RetrofitClient.createService(PersonService.class);
        this.mSecurityPreferences = new SecurityPreferences(context);
        this.mContext = context;

    }

    public void create(String name, String email, String password) {
        Call<PersonModel> call = this.mPersonService.create(name, email, password);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                Response<PersonModel> personModelResponse = response;
                PersonModel personModel =  personModelResponse.body();
                int codigo = personModelResponse.code();

                String s="";

            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                String s="";

            }
        });

    }


    public void login(String email, String password, APIListener<PersonModel> listener) {
        Call<PersonModel> call = this.mPersonService.login(email, password);
        call.enqueue(new Callback<PersonModel>() {


            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                Response<PersonModel> personModelResponse = response;
                PersonModel personModel =  personModelResponse.body();

                if (personModelResponse.code() == TaskConstants.HTTP.SUCCESS){
                    listener.onSuccess(personModel);
                } else {
                    try{
                        String json = personModelResponse.errorBody().string();
                        String str = new Gson().fromJson(json, String.class);
                        listener.onFailure(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }



            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });

    }

    public void saveUserData(PersonModel model) {

    }

}

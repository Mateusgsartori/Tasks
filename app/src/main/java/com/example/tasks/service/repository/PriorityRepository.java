package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.model.PriorityModel;
import com.example.tasks.service.repository.local.PriorityDAO;
import com.example.tasks.service.repository.local.TaskDatabase;
import com.example.tasks.service.repository.remote.PriorityService;
import com.example.tasks.service.repository.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PriorityRepository extends BaseRepository {

    private PriorityService mPriorityService;
    private PriorityDAO mPriorityDAO;


    public PriorityRepository(Context context) {
        super(context);
        this.mPriorityService = RetrofitClient.createService(PriorityService.class);
        this.mPriorityDAO = TaskDatabase.getDataBase(context).priorityDAO();
    }


    public void all(APIListener<List<PriorityModel>> listener) {
        Call<List<PriorityModel>> call = this.mPriorityService.all();
        call.enqueue(new Callback<List<PriorityModel>>() {
            @Override
            public void onResponse(Call<List<PriorityModel>> call, Response<List<PriorityModel>> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());

                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<PriorityModel>> call, Throwable t) {
                listener.onFailure(String.valueOf(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void save(List<PriorityModel> result) {
        this.mPriorityDAO.clear();
        this.mPriorityDAO.save(result);
    }
}

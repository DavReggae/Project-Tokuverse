package com.example.tokuverseproject.ServerAPI;

import com.example.tokuverseproject.Model.User;

import java.util.List;

import io.reactivex.rxjava3.internal.operators.observable.ObservableSerialized;
import retrofit2.Call;
import retrofit2.http.GET;
public interface API {
    @GET("getUser.php")
    Call<List<User>> getUser();

}

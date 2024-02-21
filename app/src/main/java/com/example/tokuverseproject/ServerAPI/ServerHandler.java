package com.example.tokuverseproject.ServerAPI;

import static android.app.ProgressDialog.show;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.example.tokuverseproject.Model.Hero;
import com.example.tokuverseproject.Model.HeroDetails;
import com.example.tokuverseproject.Model.JSON_MESSAGE;
import com.example.tokuverseproject.Model.NewFeeds;
import com.example.tokuverseproject.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerHandler {

    String Ip = //"10.40.171.72";
    "192.168.1.23";
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://tokuserver-online.preview-domain.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    API Api = retrofit.create(API.class);

    public class Message
    {
        @SerializedName("message")
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public interface CallBack
    {
        void getHero_ByID_Success(Hero hero);
        void onFailed(String message);
    }

    public void LoadImageFromURL(String url, ImageView imageView)
    {
        try
        {
            Picasso.get().load(url).into(imageView);
        }
        catch (Exception e)
        {
            Log.d("Failed To Load Image From URL", e.getMessage());
        }
    }

    public void getUser()
    {
        Call<List<User>> call = Api.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                for(int i = 0; i < userList.size(); i++)
                {
                    Log.d("sucess", userList.get(i).getId());
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });

    }

    public interface getHero_CallBack
    {
        void onSuccess(List<Hero> heroList);
        void onFail(String message);
    }
    public void getHero(getHero_CallBack callBack)
    {
        Call<List<Hero>> call = Api.getHero();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();
                try
                {
                    callBack.onSuccess(heroList);
                }
                catch (Exception e)
                {
                    callBack.onFail(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }

    public interface LoginCallback {
        void onSuccess(String userId);
        void onFail(String message);
    }

    public void LogIn(String username, String password, LoginCallback callback)
    {
        Call<List<User>> call = Api.logIn(username, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                List<User> userList = response.body();
                try{

                    callback.onSuccess(userList.get(0).getId());
                }
                catch(Exception e) {
                    callback.onFail("Log in Failed");
                    Log.d("failed", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
                callback.onFail(t.getMessage());
            }
        });
    }

    public interface GetUserByID_CallBack {
        void onSuccess(User user);
        void onFail(String message);
    }

    public void GetUserByID(String id, GetUserByID_CallBack callBack)
    {
        Log.d("Curent user id", id);
        Call<List<User>> call = Api.getUser_ByID(id);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                try
                {
                    callBack.onSuccess(userList.get(0));
                }
                catch(Exception e)
                {
                    Log.d("Get User by Id Failed", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }

    public interface getHeroDetails_ByID_callBack
    {
        void onSuccess(HeroDetails heroDetails);
    }
    public void getHeroDetails_ByID(String id, getHeroDetails_ByID_callBack callBack)
    {
        Call<List<HeroDetails>> call = Api.getHeroDetails_ByID(id);
        call.enqueue(new Callback<List<HeroDetails>>() {
            @Override
            public void onResponse(Call<List<HeroDetails>> call, Response<List<HeroDetails>> response) {
                List<HeroDetails> heroDetails = response.body();
                try
                {
                    callBack.onSuccess(heroDetails.get(0));
                }
                catch (Exception e)
                {
                    Log.d("Failed at getHeroDetails_ByID", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<HeroDetails>> call, Throwable t) {

            }
        });
    }


    public void getHero_ByID(String id, CallBack callBack)
    {
        Call<List<Hero>> call = Api.getHero_ByID(id);
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> hero = response.body();
                try
                {
                    callBack.getHero_ByID_Success(hero.get(0));
                }
                catch(Exception e)
                {
                    Log.d("Failed at getHero_ByID", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("Failed at getHero_ByID", t.getMessage());
            }
        });
    }

    public void getHeroDetails_ByUserID(String user_id)
    {
        Call<List<HeroDetails>> call = Api.getHero_ByUserID(user_id);
        call.enqueue(new Callback<List<HeroDetails>>() {
            @Override
            public void onResponse(Call<List<HeroDetails>> call, Response<List<HeroDetails>> response)
            {
                List<HeroDetails> heroDetails = response.body();
                String hero_details_id = heroDetails.get(0).getId();
                Log.d("user_ID", hero_details_id);
                addHeroToUser(user_id, hero_details_id);
            }

            @Override
            public void onFailure(Call<List<HeroDetails>> call, Throwable t) {

            }
        });
    }
    public void addHeroToUser(String id, String hero_details_id)
    {
        Call<Void> call = Api.addHeroToUser(id, hero_details_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failed at addHeroToUser", t.getMessage());
            }
        });
    }

    public interface signUp_CallBack
    {
        void onSuccess();
        void onFailed(String message);
    }
    public void signUp(User user, signUp_CallBack callBack)
    {
        Call<Void> call = Api.signUpAction(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone_number());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callBack.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("failed", t.getMessage());
            }
        });
    }

    public interface selectHero_CallBack{
        void onSuccess();
        void onFail(String message);
    }
    public void selectHero(String user_id, String hero_id, selectHero_CallBack callBack)
    {
        Call<Void> call = Api.selecHero(user_id, hero_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getHeroDetails_ByUserID(user_id);
                callBack.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    public interface createPost_CallBack
    {
        void onSuccess();
        void onFailed(String message);
    }

    public void createPost(String user_id, String content, String current_time, createPost_CallBack callBack)
    {
        Call<Void> call = Api.createPostAction(user_id, content, current_time);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                callBack.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callBack.onFailed(t.getMessage());
            }
        });
    }


    public interface GetNewFeeds_CallBack
    {
        void onSuccess(List<NewFeeds> newFeedsList);
        void onFailed(String message);
    }
    public void getNewFeedAction(GetNewFeeds_CallBack callBack)
    {
        Call<List<NewFeeds>> call = Api.getNewFeeds();
        call.enqueue(new Callback<List<NewFeeds>>() {
            @Override
            public void onResponse(Call<List<NewFeeds>> call, Response<List<NewFeeds>> response) {
                List<NewFeeds> newFeedsList = response.body();
                try
                {
                    for(int i = 0; i < newFeedsList.size(); i++)
                    {
                        Log.d("Get New Feed Success", newFeedsList.get(i).getUser_name());
                    }
                    callBack.onSuccess(newFeedsList);
                }
                catch (Exception e)
                {
                    Log.d("Get New Feed Error", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<NewFeeds>> call, Throwable t) {
                callBack.onFailed(t.getMessage());
            }
        });
    }
    public interface ChangeAttribute_CallBack
    {
        void onSuccess(JSON_MESSAGE jsonMessage);
        void onFailed(String message);
    }
    public void changeAttribute_Action(String id, String attack_point, String defense_point, String health_point, String attribute_point, ChangeAttribute_CallBack callBack)
    {
        Call<JSON_MESSAGE> call = Api.changeAttrribute(id, attack_point, defense_point, health_point, attribute_point);
        call.enqueue(new Callback<JSON_MESSAGE>() {
            @Override
            public void onResponse(Call<JSON_MESSAGE> call, Response<JSON_MESSAGE> response) {
                JSON_MESSAGE jsonMessage = response.body();
                try
                {
                    callBack.onSuccess(jsonMessage);
                }
                catch(Exception e)
                {
                    Log.d("Change Attribute Error", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JSON_MESSAGE> call, Throwable t)
            {

            }
        });
    }
}

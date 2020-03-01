package com.example.newsapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.newsapp.model.LoginResponse;
import com.example.newsapp.view.NewsActivity;
import com.example.newsapp.remote.RestClientLogin;
import com.example.newsapp.interfaces.RestMethods;
import com.example.newsapp.model.User;
import com.example.newsapp.view.NewsListActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> welcome = new MutableLiveData<>();
    RestMethods restMethods;

    private MutableLiveData<Integer> busy;

    private User user;
    private Context context;


    /**
     * Constructor for UserViewModel
     * @param context
     * @param user
     */
    public UserViewModel(Context context, User user)
    {
        this.user = user;
        this.context = context;

        this.welcome.setValue(user.getWelcomeMessage());

        //Builds HTTP Client for API Calls
        restMethods = RestClientLogin.buildHTTPClient();

    }


    /**
     * Get Mutable Data instance for progress bar
     * @return
     */
    public MutableLiveData<Integer> getBusy() {

        if (busy == null)
        {
            busy = new MutableLiveData<>();
            busy.setValue(View.GONE);
        }

        return busy;
    }


    /**
     * Event generated for login button
     * @return
     */
    public void onLoginClick()
    {
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());

        if(!user.isValidEmail())
        {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show();
        }

        else if(!user.isValidPassword())
        {
            Toast.makeText(context, "Password Should be Minimum 6 Characters", Toast.LENGTH_SHORT).show();
        }

        else if(user.isValidCredential())
        {
            getBusy().setValue(View.VISIBLE);

            doLogin(user.getEmail(),user.getPassword());
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run()
                {
                    getBusy().setValue(View.GONE);

                   // Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                 /*   Intent intent = new Intent(context, NewsActivity.class);
                    intent.putExtra("USER_OBJ", user);
                    context.startActivity(intent);
                  */


                }
            }, 500);
        }

        else
        {
            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }


    private void doLogin(String email, String password) {
        restMethods.login(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                Toast.makeText(context, "Response Successful", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()) {
                    Intent intent = new Intent(context, NewsListActivity.class);
                    context.startActivity(intent);
                    //Response was successfull
                    Log.i(TAG, "Response: " + response.body());
                    ((Activity) context).finish();
                }else {
                    Log.d(TAG, "onResponse:code "+response.code());
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                t.printStackTrace();

                Toast.makeText(context, "Responce fail", Toast.LENGTH_SHORT).show();

                //Response failed
                Log.e(TAG, "Response: " + t.getMessage());


            }
        });
    }








}
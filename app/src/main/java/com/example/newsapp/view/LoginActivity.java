package com.example.newsapp.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityLoginBinding;
import com.example.newsapp.model.User;
import com.example.newsapp.viewmodel.UserViewModel;
import com.example.newsapp.viewmodel.factory.UserViewModelFactory;


public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /**
         * Create instance for data binding auto generated class file
         */
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        /**
         * Create instance for ViewModel class
         */

        UserViewModel userViewModel = ViewModelProviders.of(this, new UserViewModelFactory(this, new User())).get(UserViewModel.class);

        /**
         * Set ViewModel instance to binding class
         */
        binding.setUserViewModel(userViewModel);
    }
}
package com.example.newsapp.viewmodel.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.model.User;
import com.example.newsapp.viewmodel.UserViewModel;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory
{
    private User user;
    private Context context;


    public UserViewModelFactory(Context context, User user)
    {
        this.context = context;
        this.user = user;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new UserViewModel(context, user);
    }
}
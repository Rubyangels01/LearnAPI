package com.example.learnapi.controller.base.basecontroller;






import com.example.learnapi.repository.Irepository.IUserRepository;
import com.example.learnapi.repository.Repository;
import com.example.learnapi.controller.base.baseview.View;

public class baseControllerUser<V extends View,R extends IUserRepository> implements Controller {
    protected V view;
    protected R repository;
}


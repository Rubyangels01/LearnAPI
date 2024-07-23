package com.example.learnapi.controller.base.basecontroller;




import com.example.learnapi.repository.Repository;
import com.example.learnapi.controller.base.baseview.View;

public class baseController<V extends View,R extends Repository> implements Controller {
    protected V view;
    protected R repository;
}

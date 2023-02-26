package com.example.core.singleton;

public class SingletonService {

    private final static SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
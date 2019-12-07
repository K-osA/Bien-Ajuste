package com.examp.bienajuste;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DB_Handler extends StringRequest {
    final static private String loginURL = "http://54.180.25.126/Login.php";
    final static private String registerURL = "http://54.180.25.126/Register.php";
    final static private String validateURL = "http://54.180.25.126/Validate.php";
    final static private String makecartURL = "http://54.180.25.126/Makecart.php";
    private Map<String, String> parameters;

    public DB_Handler(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, loginURL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
    }

    public DB_Handler(String userID, String userPassword, String userName, int userAge, String userEmail, String userAddress, double userFootsize, int userGender, int cartID, Response.Listener<String> listener){
        super(Method.POST, registerURL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+"");
        parameters.put("userEmail",userEmail);
        parameters.put("userAddress",userAddress);
        parameters.put("userFootsize",userFootsize+"");
        parameters.put("userGender",userGender+"");
        parameters.put("cartID",cartID+"");
    }

    public DB_Handler(String userID, Response.Listener<String> listener){
        super(Method.POST, validateURL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
    }

    public DB_Handler(int cartID, int one, int two, int three, Response.Listener<String> listener){
        super(Method.POST, makecartURL, listener, null);
        parameters = new HashMap<>();
        parameters.put("cartID",cartID+"");
        parameters.put("one",one+"");
        parameters.put("two",two+"");
        parameters.put("three",three+"");
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
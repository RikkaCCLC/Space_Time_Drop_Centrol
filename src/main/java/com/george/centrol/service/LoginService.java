package com.george.centrol.service;

import java.util.Map;

public interface LoginService {
    public Map<String, String> getToken(String username, String password);
}

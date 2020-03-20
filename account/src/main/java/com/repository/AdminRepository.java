package com.repository;

import com.entity.Admin;

public interface AdminRepository {
    public Admin login(String user, String pwd);

}

package com.wplab.service;

public class AdminDO {
    private String admin_id;
    private String password;
    private String email;

    public AdminDO() {
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AdminDO [admin_id=" + admin_id + ", password=" + password + ", email=" + email + "]";
    }
}

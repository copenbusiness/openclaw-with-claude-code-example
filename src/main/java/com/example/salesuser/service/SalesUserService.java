package com.example.salesuser.service;

import com.example.salesuser.dao.SalesUserDao;
import com.example.salesuser.entity.SalesUser;
import com.example.salesuser.util.PasswordUtil;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
public class SalesUserService {

    @EJB
    private SalesUserDao salesUserDao;

    public SalesUser createUser(SalesUser user) throws Exception {
        // Check if username already exists
        SalesUser existing = salesUserDao.findByUserName(user.getUserName());
        if (existing != null) {
            throw new Exception("User name already exists");
        }
        // Hash password before storing
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return salesUserDao.create(user);
    }

    public SalesUser updateUser(SalesUser user) throws Exception {
        SalesUser existing = salesUserDao.findById(user.getUserId());
        if (existing == null) {
            throw new Exception("User not found");
        }
        // Check if username changed and conflicts
        if (!existing.getUserName().equals(user.getUserName())) {
            SalesUser conflict = salesUserDao.findByUserName(user.getUserName());
            if (conflict != null) {
                throw new Exception("User name already exists");
            }
        }
        // Handle password update: if password is not empty, hash and update
        // If password is empty, keep existing password
        String newPassword = user.getPassword();
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            // Check if the password is already hashed (unlikely)
            // Simple approach: hash the new password
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            user.setPassword(hashedPassword);
        } else {
            // Keep existing password
            user.setPassword(existing.getPassword());
        }
        return salesUserDao.update(user);
    }

    public void deleteUser(Long userId) throws Exception {
        SalesUser user = salesUserDao.findById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }
        salesUserDao.delete(userId);
    }

    public SalesUser getUser(Long userId) {
        return salesUserDao.findById(userId);
    }

    public List<SalesUser> getAllUsers() {
        return salesUserDao.findAll();
    }

    public SalesUser getUserByUserName(String userName) {
        return salesUserDao.findByUserName(userName);
    }
}
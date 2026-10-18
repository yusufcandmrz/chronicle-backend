package com.yusufcandmrz.chronicle.service.impl;

import com.yusufcandmrz.chronicle.dto.ReadCurrentUserResponse;
import com.yusufcandmrz.chronicle.entity.AppUser;
import com.yusufcandmrz.chronicle.security.SecurityUtils;
import com.yusufcandmrz.chronicle.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ReadCurrentUserResponse readCurrentUser() {
        AppUser currentUser = SecurityUtils.getCurrentUser();
        return mapToReadCurrentUserResponse(currentUser);
    }

    private ReadCurrentUserResponse mapToReadCurrentUserResponse(AppUser appUser) {
        return new ReadCurrentUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRole(),
                appUser.getCreatedAt(),
                appUser.getUpdatedAt()
        );
    }
}

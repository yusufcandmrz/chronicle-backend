package com.yusufcandmrz.chronicle.notification.event;

public record UserRegisteredEvent(
        Long userId,
        String username,
        String email
) {
}

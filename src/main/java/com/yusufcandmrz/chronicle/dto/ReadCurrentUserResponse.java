package com.yusufcandmrz.chronicle.dto;

import com.yusufcandmrz.chronicle.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadCurrentUserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
}

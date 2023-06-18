package com.zero.model;

import com.zero.model.dto.SignUpForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public static Member from(SignUpForm form) {
        return Member.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .phoneNumber(form.getPhoneNumber())
                .build();
    }
}

package com.zero.model;

import com.zero.model.dto.CreateGroupForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
    private boolean isPublic;

    @Column(columnDefinition = "varchar (20)")
    private String password;

    @OneToMany(mappedBy = "group")
    private List<Member> members = new ArrayList<>();

    public static Group from(CreateGroupForm form) {
        return Group.builder()
                .groupName(form.getGroupName())
                .isPublic(form.isPublic())
                .password(form.getPassword())
                .build();
    }
}

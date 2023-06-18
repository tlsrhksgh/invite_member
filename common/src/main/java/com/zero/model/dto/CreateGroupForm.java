package com.zero.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateGroupForm {

    @NotNull(message = "그룹 이름은 필수로 입력되어야 합니다.")
    private String groupName;

    @NotBlank(message = "그룹의 공유 유무는 필수로 입력되어야 합니다.")
    private boolean isPublic;

    private String password;
}

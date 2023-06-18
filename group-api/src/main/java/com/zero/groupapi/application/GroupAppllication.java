package com.zero.groupapi.application;

import com.zero.groupapi.service.GroupService;
import com.zero.model.Group;
import com.zero.model.dto.CreateGroupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupAppllication {
    private final GroupService groupService;

    public String createGroup(CreateGroupForm form, String token) {
        Group result = groupService.saveGroup(form, token);

        if(result == null) {
            throw new RuntimeException("그룹 생성이 실패되었습니다.");
        }

        return "그룹 생성에 성공하였습니다.";
    }
}

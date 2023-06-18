package com.zero.groupapi.service;

import com.zero.groupapi.domain.repository.GroupRepository;
import com.zero.model.Group;
import com.zero.model.dto.CreateGroupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group saveGroup(CreateGroupForm form, String userEmail) {
        return null;
    }
}

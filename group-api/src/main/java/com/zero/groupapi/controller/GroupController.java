package com.zero.groupapi.controller;

import com.zero.groupapi.application.GroupAppllication;
import com.zero.model.dto.CreateGroupForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/group")
@RestController
public class GroupController {
    private final GroupAppllication groupAppllication;

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestHeader("Authentication") String token,
                                      @Valid @RequestBody CreateGroupForm form) {
        String responseMessage = "";

        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/delete/groupId")
    public ResponseEntity<Void> deleteGroup(@RequestHeader("Authentication") String token,
                                            @PathVariable String groupId) {

        return ResponseEntity.ok().build();
    }
}

package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @PostMapping
    public ResponseDto<ProfileDto> addNewProfile(@RequestBody ProfileDto profileDto){
        return profileService.addNewProfile(profileDto);
    }
    @GetMapping
    public ResponseDto<ProfileDto> getById(@RequestParam Long id){
        return profileService.getById(id);
    }
    @DeleteMapping("delete-by-id")
    public ResponseDto<ProfileDto> deleteById(@RequestParam Long id){
        return profileService.deleteById(id);
    }
    @PutMapping("edit")
    public ResponseDto<ProfileDto> edit(@RequestBody ProfileDto profileDto){
        return profileService.edit(profileDto);
    }
    @GetMapping("get-all-profile")
    public ResponseDto<List<ProfileDto>> getAllProfile(){
        return profileService.getAllProfile();
    }
}

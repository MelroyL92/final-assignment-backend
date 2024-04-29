package nl.novi.finalAssignmentBackend.mappers.UserMappers;

import nl.novi.finalAssignmentBackend.dtos.user.UserDto;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDto;
import nl.novi.finalAssignmentBackend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoMapper {



    public UserResponseDto toUserDTO(UserModel model){
        return toUserDTO(model, new UserResponseDto());
    }


    public <D extends UserResponseDto> D toUserDTO(UserModel model, D target) {
        target.setUsername(model.getUsername());
        target.setEmail(model.getEmail());
        target.setAuthorities(model.getAuthorities());
        target.setUploadOrder(model.getUploadOrder());
        return target;
    }


    public List<UserResponseDto> toUserDTOs(List<UserModel> userModels) {
        List<UserResponseDto>result = new ArrayList<>();
        for (UserModel userModel : userModels) {
            result.add(toUserDTO(userModel));
        }
        return result;
    }


    public UserModel createUserModel(UserDto dto){
        var user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAuthorities(dto.getAuthorities());
//        user.setUploadOrder(dto.getUploadOrder());
        return user;
    }
}

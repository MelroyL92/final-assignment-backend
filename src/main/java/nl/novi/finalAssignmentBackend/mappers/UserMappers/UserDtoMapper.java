package nl.novi.finalAssignmentBackend.mappers.UserMappers;

import nl.novi.finalAssignmentBackend.dtos.user.UserDTO;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDTO;
import nl.novi.finalAssignmentBackend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoMapper {



    public UserResponseDTO toUserDTO(UserModel model){
        return toUserDTO(model, new UserResponseDTO());
    }


    public <D extends UserResponseDTO> D toUserDTO(UserModel model, D target) {
        target.setUsername(model.getUsername());
        target.setEmail(model.getEmail());
        target.setAuthorities(model.getAuthorities());
        target.setUploadOrder(model.getUploadOrder());
        return target;
    }


    public List<UserResponseDTO> toUserDTOs(List<UserModel> userModels) {
        List<UserResponseDTO>result = new ArrayList<>();
        for (UserModel userModel : userModels) {
            result.add(toUserDTO(userModel));
        }
        return result;
    }


    public UserModel createUserModel(UserDTO dto){
        var user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAuthorities(dto.getAuthorities());
//        user.setUploadOrder(dto.getUploadOrder());
        return user;
    }
}

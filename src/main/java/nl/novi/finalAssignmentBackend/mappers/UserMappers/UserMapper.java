package nl.novi.finalAssignmentBackend.mappers.UserMappers;

import nl.novi.finalAssignmentBackend.entities.User;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements EntityMapper<UserModel, User> {

    public UserModel fromEntity(User user) {
        if(user == null){
            return null;
        }
        UserModel model = new UserModel();
        model.setEmail(user.getEmail());
        model.setUsername(user.getUsername());
        model.setPassword(user.getPassword());
        model.setAuthorities(user.getAuthorities());
        model.setUploadOrder(user.getUploadOrder());
        return model;
    }

    public List<UserModel>fromEntity(List<User>users){
        List<UserModel> userModelList = new ArrayList<>();
        for(User user: users){
            userModelList.add(fromEntity(user));
        }
        return userModelList;
    }

    public List<User>toEntity(List<UserModel>userModels){
        List<User>userList = new ArrayList<>();
        for (UserModel model: userModels){
            userList.add(toEntity(model));
        }
        return userList;
    }

    public User toEntity(UserModel model) {
        if(model == null){
            return null;
        }
        User user = new User();
        user.setPassword(model.getPassword());
        user.setUsername(model.getUsername());
        user.setEmail(model.getEmail());
        user.setApikey(model.getApikey());
        user.setAuthorities(model.getAuthorities());
        user.setUploadOrder(model.getUploadOrder());
        return user;

    }
}

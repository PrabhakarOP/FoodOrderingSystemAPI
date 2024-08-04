package prabhakar.myprojects.food_ordering_system.model.request.userRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class UserRegisterRequest {
    private String userName;
    private String password;
    private String email;
    private String role;
}

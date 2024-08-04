package prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CreateRestaurantRequest {
    private Integer ownerId;
    private String name;
    private String address;
    private String phoneNumber;
}

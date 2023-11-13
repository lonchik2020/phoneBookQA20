package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class NewContactDTO {
    String address;
    String description;
    String email;
    String id; // always 0 need for AP[
    String lastName;
    String name;
    String phone;

}

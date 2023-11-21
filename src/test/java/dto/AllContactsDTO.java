package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AllContactsDTO {
    NewContactDTO[] contacts;//variable name must be the same as in api request response body
}



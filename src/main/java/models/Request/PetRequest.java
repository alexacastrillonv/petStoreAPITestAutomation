package models.Request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetRequest {
    private Integer id;
    private String name;
    private Category category;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;
}

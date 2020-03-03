package bg.softuni.errorhandling.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CloudFileViewModel {
    private String name;
    private String publicId;
    private String downloadURL;
}

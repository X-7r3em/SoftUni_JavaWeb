package bg.softuni.errorhandling.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloudFile {
    private String name;
    private String publicId;
    private String downloadURL;
}


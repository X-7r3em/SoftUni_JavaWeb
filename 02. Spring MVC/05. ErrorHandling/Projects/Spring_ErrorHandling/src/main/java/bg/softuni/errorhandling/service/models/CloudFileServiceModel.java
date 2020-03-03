package bg.softuni.errorhandling.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CloudFileServiceModel {
    private MultipartFile file;
}

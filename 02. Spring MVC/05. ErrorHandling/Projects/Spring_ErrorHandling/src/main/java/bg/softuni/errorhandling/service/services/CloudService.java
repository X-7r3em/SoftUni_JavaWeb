package bg.softuni.errorhandling.service.services;

import bg.softuni.errorhandling.service.models.CloudFile;
import bg.softuni.errorhandling.service.models.CloudFileServiceModel;

import java.io.IOException;
import java.util.List;

public interface CloudService {
    boolean upload(CloudFileServiceModel model) throws IOException;

    List<CloudFile> getAll();

    void delete(String publicId) throws IOException;
}

package bg.softuni.errorhandling.service.services;

import bg.softuni.errorhandling.service.models.CloudFile;
import bg.softuni.errorhandling.service.models.CloudFileServiceModel;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudServiceImpl implements CloudService {
    private static final List<CloudFile> CLOUD_DB = new ArrayList<>();

    private final Cloudinary cloudinary;

    @Autowired
    public CloudServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public boolean upload(CloudFileServiceModel model) throws IOException {
        MultipartFile multipartFile = model.getFile();
        File file = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        if (file.length() <= 0 || file.length() >= 1048576) {
            //Check if file is empty or too Large for Runtime Exception.
            return false;
        }

        Map<String, String> options = new HashMap<>();
        options.put("resource_type", "auto");
        Map<String, String> fileInfo = this.cloudinary.uploader().upload(file, options);
        String publicId = fileInfo.get("public_id");
        StringBuilder downloadURL = new StringBuilder(fileInfo.get("url").toString());
        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        downloadURL.insert(downloadURL.indexOf("/upload/") + 8, "fl_attachment:" + fileName + "/");

        CloudFile cloudFile = new CloudFile(originalFileName, publicId, downloadURL.toString());
        CLOUD_DB.add(cloudFile);
        return true;
    }

    @Override
    public List<CloudFile> getAll() {
        return CLOUD_DB;
    }

    @Override
    public void delete(String publicId) throws IOException {
        Map<String, String> options = new HashMap<>();
        options.put("resource_type", "raw");
        Map<String, String> result = this.cloudinary.uploader().destroy(publicId, options);
        CLOUD_DB.removeIf(f -> f.getPublicId().equals(publicId));
    }
}

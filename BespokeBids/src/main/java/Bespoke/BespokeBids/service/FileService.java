package Bespoke.BespokeBids.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

        private static final String UPLOAD_DIRECTORY = "/upload/file";

        public String saveProfilePicture(MultipartFile profilePicture) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = StringUtils.cleanPath(profilePicture.getOriginalFilename() + UUID.randomUUID().toString());
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return UPLOAD_DIRECTORY + "/" + fileName; // 프로필 사진 URL 생성
            } catch (IOException e) {
                // 예외 처리 로직
                e.printStackTrace();
                return null;
            }
        }
    }


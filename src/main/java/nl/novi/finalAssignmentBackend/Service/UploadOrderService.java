package nl.novi.finalAssignmentBackend.Service;

import nl.novi.finalAssignmentBackend.Repository.UploadOrderRepository;
import nl.novi.finalAssignmentBackend.entities.UploadOrder;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.LoggedInCheck;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadOrderService {

    private final UploadOrderRepository uploadOrderRepository;
    private final LoggedInCheck loggedInCheck;

    public UploadOrderService(UploadOrderRepository uploadOrderRepository, LoggedInCheck loggedInCheck) {
        this.uploadOrderRepository = uploadOrderRepository;
        this.loggedInCheck = loggedInCheck;
    }


        public UploadOrder storeFile(MultipartFile file, String url) throws IOException{

            String originalFileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] bytes = file.getBytes();

            UploadOrder uploadOrder = new UploadOrder(originalFileName, contentType, url, bytes);

            return uploadOrderRepository.save(uploadOrder);
        }

        public UploadOrder getOrderById(Long id, String username){
            loggedInCheck.verifyLoggedInUser(username);
        return uploadOrderRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("the order with id " + id + " does not excist"));
    }

}

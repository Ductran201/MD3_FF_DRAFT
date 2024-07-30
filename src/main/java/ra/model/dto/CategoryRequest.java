package ra.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
public class CategoryRequest {
    private Long id;
    private String name;
    private MultipartFile multipartFile;
    private Boolean status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
}

package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.ICategoryDao;
import ra.model.dto.CategoryRequest;
import ra.model.entity.Category;
import ra.service.ICategoryService;
import ra.service.UploadService;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {
@Autowired
private ICategoryDao categoryDao;
@Autowired
public UploadService uploadService;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .status(categoryRequest.getStatus())
                .createdDate(categoryRequest.getCreatedDate())
                .build();
        if(categoryRequest.getId() == null) {
            category.setUrl(uploadService.uploadFileToServer(categoryRequest.getMultipartFile()));
        } else {
            if(categoryRequest.getMultipartFile() != null && categoryRequest.getMultipartFile().getSize() > 0) {
                category.setUrl(uploadService.uploadFileToServer(categoryRequest.getMultipartFile()));
            } else {
                category.setUrl(categoryDao.getImageByProductId(categoryRequest.getId()));
            }
        }
        categoryDao.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }


}

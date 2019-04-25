package controller.category;

import dao.ProductCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pojo.ProductCategory;
import pojo.User;
import service.ProductCategoryService;
import service.UserService;
import util.ErrorMessage;
import vo.ProductCategoryVo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/product")
    public List<ProductCategoryVo> getAllProductCategories(){
        List<ProductCategory> rootCategories = productCategoryService.selectAllByParentId(0);
        List<ProductCategoryVo> vos = new LinkedList<ProductCategoryVo>();
        for (ProductCategory category : rootCategories){
            vos.add(productCategoryService.selectAllByParentIdAndRecursive(category.getId()));
        }
        return vos;
    }

    @GetMapping("/product/{categoryId}")
    public Object getSingleProductCategory(@PathVariable Integer categoryId){
        ProductCategoryVo vo = productCategoryService.selectAllByParentIdAndRecursive(categoryId);
        if (vo == null) {
            return ErrorMessage.getErrorResponse(404, "categoryId 不存在");
        } else {
            return vo;
        }
    }
}

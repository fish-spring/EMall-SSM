package controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.ProductCategory;
import service.ProductCategoryService;
import util.ErrorMessage;
import vo.ProductCategoryVo;

import java.util.LinkedList;
import java.util.List;

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

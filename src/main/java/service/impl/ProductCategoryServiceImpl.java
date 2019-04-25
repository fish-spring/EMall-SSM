package service.impl;

import dao.ProductCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.ProductCategory;
import service.ProductCategoryService;
import vo.ProductCategoryVo;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao categoryDao;

    public ProductCategory getInfoById(Integer id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    public List<ProductCategory> selectAllByParentId(Integer parentId) {
        return categoryDao.selectAllByParentId(parentId);
    }

    public ProductCategoryVo selectAllByParentIdAndRecursive(Integer parentId) {

        ProductCategoryVo vo = new ProductCategoryVo();
        ProductCategory currentCategory = categoryDao.selectByPrimaryKey(parentId);
        List<ProductCategoryVo> childrenVo = new LinkedList<ProductCategoryVo>();
        vo.setCurrentCategory(currentCategory);
        vo.setChildren(childrenVo);

        List<ProductCategory> childrenCategories = categoryDao.selectAllByParentId(parentId);

        if (childrenCategories.size() == 0){
            return vo;
        }

        for (ProductCategory childProductCategory : childrenCategories){
            childrenVo.add(selectAllByParentIdAndRecursive(childProductCategory.getId()));
        }
        return vo;
    }
}

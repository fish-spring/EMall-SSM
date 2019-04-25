package service;

import pojo.ProductCategory;
import vo.ProductCategoryVo;

import java.util.List;

public interface ProductCategoryService {
    public ProductCategory getInfoById(Integer id);

    public List<ProductCategory> selectAllByParentId(Integer parentId);

    public ProductCategoryVo selectAllByParentIdAndRecursive(Integer parentId);
}

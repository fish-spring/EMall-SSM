package dao;

import pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    public ProductCategory selectByPrimaryKey(Integer id);

    // 查询一个分类下的所有子分类
    public List<ProductCategory> selectAllByParentId(Integer parentId);
}

package dao;

import pojo.Product;
import pojo.page.ProductPage;

import java.util.List;

public interface ProductDao {
    public Product selectByPrimaryKey(Integer id);
    public int insertSelective(Product product);

    public List<Product> selectListPageable(ProductPage page);
}

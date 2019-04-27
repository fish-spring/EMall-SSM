package dao;

import pojo.Product;
import pojo.page.ProductPage;
import vo.ProductInfo;
import vo.ProductVo;

import java.util.List;

public interface ProductDao {
    public Product selectByPrimaryKey(Integer id);

    // 如果一个上面无人评价，那么avgScore的值就是null，注意
    public ProductInfo selectProductInfoByPrimaryKey(Integer id);
    public int insertSelective(Product product);

    public List<Product> selectListPageable(ProductPage page);
}

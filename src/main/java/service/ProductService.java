package service;

import pojo.Product;
import pojo.page.ProductPage;
import vo.ProductInfo;
import vo.ProductVo;

import java.util.Date;
import java.util.List;

public interface ProductService {
    public Product publishProduct(Product product);
    public ProductVo getProductVoById(Integer id);
    public List<ProductVo> getProductVosPageable(ProductPage page);
}

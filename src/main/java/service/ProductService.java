package service;

import pojo.Product;
import pojo.page.ProductPage;

import java.util.Date;
import java.util.List;

public interface ProductService {
    public Product publishProduct(Product product);
    public Product getProductById(Integer id);
    public List<Product> getProductsPageable(ProductPage page);
}

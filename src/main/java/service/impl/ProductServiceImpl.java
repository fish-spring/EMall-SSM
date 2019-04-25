package service.impl;

import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public Product publishProduct(Product product) {
        productDao.insertSelective(product);
        return product;
    }

    public Product getProductById(Integer id) {
        return productDao.selectByPrimaryKey(id);
    }

    public List<Product> getProductsPageable(ProductPage page) {
        List<Product> products = productDao.selectListPageable(page);
        return products;
    }
}

package service.impl;

import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;
import vo.ProductInfo;
import vo.ProductVo;

import java.util.ArrayList;
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

    public ProductVo getProductVoById(Integer id) {
        Product product = productDao.selectByPrimaryKey(id);
        ProductInfo productInfo = productDao.selectProductInfoByPrimaryKey(id);

        ProductVo vo = ProductVo.getInstance(product);
        vo.getInfoFromProductInfo(productInfo);
        return vo;
    }

    public List<ProductVo> getProductVosPageable(ProductPage page) {
        List<Product> products = productDao.selectListPageable(page);
        List<ProductVo> productVos = new ArrayList<>();

        for (Product product : products) {
            ProductInfo productInfo = productDao.selectProductInfoByPrimaryKey(product.getId());

            ProductVo productVo = ProductVo.getInstance(product);
            productVo.getInfoFromProductInfo(productInfo);

            productVos.add(productVo);
        }

        return productVos;
    }
}

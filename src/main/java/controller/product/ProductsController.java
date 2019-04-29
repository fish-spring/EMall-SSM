package controller.product;

import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;
import service.UserService;
import vo.ProductVo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;

    @GetMapping
    public List<ProductVo> getProducts(
            // 这些参数都是可选的
            @RequestParam(required = false) Integer shopId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date beforeDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date afterDate,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber){
        ProductPage page = new ProductPage();
        page.setShopId(shopId);
        page.setCategoryId(categoryId);
        page.setMinPrice(minPrice);
        page.setMaxPrice(maxPrice);
        page.setAfterDate(afterDate);
        page.setBeforeDate(beforeDate);
        page.setLimit(pageSize);
        int offset;
        if (pageNumber == null || pageNumber <= 0){
            offset = 0;
        } else {
            // 如果是第一页，就没有offset
            offset = (pageNumber - 1) *  pageSize;
        }
        page.setOffset(offset);
        List<ProductVo> productVos =
                productService.getProductVosPageable(page);
        return productVos;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer productId){
        return productDao.selectByPrimaryKey(productId);
    }

    @PostMapping
    private Object publishProduct(@RequestBody Product product){
        return null;
    }
}

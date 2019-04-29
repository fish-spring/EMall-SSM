package controller.order;

import dao.OrderInfoDao;
import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.OrderInfo;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;
import util.payjs.NativeResponse;
import util.payjs.PayJS;
import vo.ProductVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PayJS payJS;

    @Autowired
    private OrderInfoDao orderInfoDao;

    // List of order detail
    // 服务端计算出总价，生成对应订单

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer productId){
        return productDao.selectByPrimaryKey(productId);
    }

    @PostMapping
    private Object publishProduct(@RequestBody List<Product> productsInfo){
        List<Integer> ids = new ArrayList<>();

        for (Product product : productsInfo){
            ids.add(product.getId());
        }
        List<Product> products = productDao.selectByIds(ids);

        int totalPrice = 0;
        for (Product product : products){
            totalPrice += product.getPrice();
        }

        // 在数据库中创建订单,这样可以拿到id作为用户订单号
        OrderInfo orderInfo = new OrderInfo();
        // 设置用户id
        orderInfo.setUserId(23);
        // 默认就是payJS
        orderInfo.setPaymentMethod(4);
        // 字符金额
        orderInfo.setPaymentMoney(totalPrice);

        orderInfoDao.insertSelective(orderInfo);

        // todo 以及订单详情

        NativeResponse response = payJS.apiNative(
                String.valueOf(orderInfo.getId()), totalPrice);

        // todo 异常处理

        // 向数据库中补充相关数据
        orderInfo.setQrcode(response.getQrcode());
        orderInfo.setPaymentOrderId(response.getPayjs_order_id());
        orderInfoDao.insertSelective(orderInfo);

        return response;
    }
}

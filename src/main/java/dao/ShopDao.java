package dao;

import pojo.ProductCategory;
import pojo.Shop;

public interface ShopDao {
    public ProductCategory selectByPrimaryKey(Integer id);

    public int insertSelective(Shop record);
}

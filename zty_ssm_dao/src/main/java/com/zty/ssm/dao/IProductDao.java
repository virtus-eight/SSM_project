package com.zty.ssm.dao;
import com.zty.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    //查询所有商品信息
    @Select("SELECT * FROM product")
    public List<Product> findAll() throws Exception;

    @Insert("INSERT INTO product (id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(REPLACE(UUID(),\"-\",\"\"),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);

    //根据id查询产品
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;
}

package cn.edu.xmu.javaee.productdemoaop.repository;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPoRepository extends JpaRepository<ProductPo, Long> {

    // 方法一：使用Spring Data JPA的方法命名约定来实现查询
    List<ProductPo> findByNameContaining(String name);

    // 方法二：使用自定义的JPQL查询语句来实现查询（通过@Query注解）
    @Query("SELECT p FROM ProductPo p WHERE p.name LIKE %?1%")
    List<ProductPo> findProductsByName(String name);
}
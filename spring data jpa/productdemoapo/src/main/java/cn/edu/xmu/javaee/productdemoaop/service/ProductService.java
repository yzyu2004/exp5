package cn.edu.xmu.javaee.productdemoaop.service;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.productdemoaop.dao.ProductDao;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.Product;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);


    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id 商品id
     * @return 商品对象
     */
    @Transactional(rollbackFor = {BusinessException.class})
    public Product retrieveProductByID(Long id, boolean all) throws BusinessException {
        logger.debug("findProductById: id = {}, all = {}", id, all);
        return productDao.retrieveProductByID(id, all);
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    @Transactional
    public List<Product> retrieveProductByName(String name, boolean all) throws BusinessException{
        return productDao.retrieveProductByName(name, all);
    }

    /**
     * 新增商品
     * @param product 新商品信息
     * @return 新商品
     */
    @Transactional
    public Product createProduct(Product product, User user) throws BusinessException{
        return productDao.createProduct(product, user);
    }


    /**
     * 修改商品
     * @param product 修改商品信息
     */
    @Transactional
    public void modifyProduct(Product product, User user) throws BusinessException{
        productDao.modiProduct(product, user);
    }

    /** 删除商品
     * @param id 商品id
     * @return 删除是否成功
     */
    @Transactional
    public void deleteProduct(Long id) throws BusinessException {
        productDao.deleteProduct(id);
    }

    @Transactional
    public Product findProductById_manual(Long id) throws BusinessException {
        logger.debug("findProductById_manual: id = {}", id);
        return productDao.findProductByID_manual(id);
    }

    /**
     * 用商品名称搜索商品
     *
     * @param name 商品名称
     * @return 商品对象
     */
    @Transactional
    public List<Product> findProductByName_manual(String name) throws BusinessException{
        return productDao.findProductByName_manual(name);
    }

    /**
     * 根据产品名称查询产品完整信息的业务逻辑方法
     *
     * @param name 产品名称
     * @return 包含完整信息的产品列表
     * @throws BusinessException 如果在查询过程中出现业务相关异常则抛出
     */
    @Transactional
    public List<Product> getProductsByName(String name) throws BusinessException {
        List<Product> productList = productDao.findProductsByName(name);
        logger.debug("getProductsByName: 查询到 {} 个产品", productList.size());
        return productList;
    }

}

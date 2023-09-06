package top.iyuhao.service;

import org.springframework.transaction.annotation.Transactional;
import top.iyuhao.entity.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yuhao
* @description 针对表【blog_category(博客分类表)】的数据库操作Service
* @createDate 2023-08-25 08:46:42
*/
@Transactional(rollbackFor = Exception.class)
public interface BlogCategoryService extends IService<BlogCategory> {
    /**
     * 获取所有分类
     * @return
     */
    List<BlogCategory> getAll();
}

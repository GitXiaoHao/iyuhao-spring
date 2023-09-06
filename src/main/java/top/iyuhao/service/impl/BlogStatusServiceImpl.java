package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.BlogStatus;
import top.iyuhao.service.BlogStatusService;
import top.iyuhao.mapper.BlogStatusMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【blog_status(博客状态表)】的数据库操作Service实现
* @createDate 2023-08-28 18:00:36
*/
@Service
public class BlogStatusServiceImpl extends ServiceImpl<BlogStatusMapper, BlogStatus>
    implements BlogStatusService{

}





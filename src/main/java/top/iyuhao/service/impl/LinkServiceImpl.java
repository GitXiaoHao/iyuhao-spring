package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.Link;
import top.iyuhao.service.LinkService;
import top.iyuhao.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【link(链接表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:31
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
    implements LinkService{

}





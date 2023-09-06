package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.Ad;
import top.iyuhao.service.AdService;
import top.iyuhao.mapper.AdMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【ad(广告表)】的数据库操作Service实现
* @createDate 2023-08-20 15:39:47
*/
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad>
    implements AdService{

}





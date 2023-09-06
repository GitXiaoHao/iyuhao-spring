package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.AdType;
import top.iyuhao.service.AdTypeService;
import top.iyuhao.mapper.AdTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【ad_type(广告类型表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:15
*/
@Service
public class AdTypeServiceImpl extends ServiceImpl<AdTypeMapper, AdType>
    implements AdTypeService{

}





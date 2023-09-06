package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.AdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【ad_type(广告类型表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:15
* @Entity top.iyuhao.entity.AdType
*/
@Mapper
public interface AdTypeMapper extends BaseMapper<AdType> {

}





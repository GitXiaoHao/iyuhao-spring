package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.Ad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【ad(广告表)】的数据库操作Mapper
* @createDate 2023-08-20 15:39:47
* @Entity top.iyuhao.entity.Ad
*/
@Mapper
public interface AdMapper extends BaseMapper<Ad> {

}





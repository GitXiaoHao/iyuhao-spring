package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【link(链接表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:31
* @Entity top.iyuhao.entity.Link
*/@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}





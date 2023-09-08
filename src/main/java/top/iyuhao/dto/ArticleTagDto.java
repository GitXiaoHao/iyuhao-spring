package top.iyuhao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.iyuhao.entity.ArticleTag;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuhao
 * @date 2023/9/8
 **/
@Data
@AllArgsConstructor
public class ArticleTagDto implements Serializable {
    private ArticleTag articleTag;
    private List<ArticleTagDto> children;
}

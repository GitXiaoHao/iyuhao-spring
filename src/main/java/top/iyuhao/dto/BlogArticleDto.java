package top.iyuhao.dto;

import lombok.Data;
import top.iyuhao.entity.BlogArticle;

import java.io.Serializable;

/**
 * @author yuhao
 * @date 2023/9/8
 **/
@Data
public class BlogArticleDto implements Serializable {
    private BlogArticle blogArticle;
    private String[] tags;
}

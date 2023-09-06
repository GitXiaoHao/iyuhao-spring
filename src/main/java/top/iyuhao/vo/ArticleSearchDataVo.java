package top.iyuhao.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuhao
 * @date 2023/9/4
 **/
@Data
public class ArticleSearchDataVo implements Serializable {
    private String titleFuzzy;
    private String searchStatus;
    private String searchCategoryName;
}

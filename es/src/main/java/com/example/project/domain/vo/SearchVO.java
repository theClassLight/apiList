package com.example.project.domain.vo;

import com.example.project.domain.entity.Picture;
import com.example.project.model.vo.UserVO;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 聚合搜索
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class SearchVO implements Serializable {

    private List<UserVO> userList;

    private List<PostVO> postList;

    private List<Picture> pictureList;

    private List<?> dataList;

    private static final long serialVersionUID = 1L;

}

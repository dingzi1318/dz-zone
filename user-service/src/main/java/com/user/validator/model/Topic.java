package com.demo.validator.model;

import com.demo.validator.constraint.NumberValidator;
import com.demo.validator.group.AddGroup;
import com.demo.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * 话题提交参数
 *
 * @date 2020-03-24
 * @author dingzi
 */
@Data
public class Topic {

    /** 话题ID，编辑时必填*/
    @Null(message = "话题ID必须为空", groups = AddGroup.class)
    @NotNull(groups = UpdateGroup.class)
    @NumberValidator(message = "话题ID参数非法", groups = UpdateGroup.class)
    private String id;

    /** 话题标题(名称), 必填*/
    @NotBlank
    @Length(max = 30)
    private String title;

    /** 图标，必填*/
    @NotBlank
    private String iconUrl;

    /** 话题描述, 必填*/
    @NotBlank
    @Length(max = 100)
    private String description;

    /** 浏览数增量 , 非必填*/
    @NumberValidator
    @Max(value = 1000000)
    @Min(value = 1)
    private String browseIncrement;

    /** 关联栏目ID，必填*/
    @NotNull
    @NumberValidator
    private String cateId;

    /** 开启状态: 0-关闭 1-开启, 必填*/
    @NotNull
    @NumberValidator
    @Range(min = 0, max = 1)
    private String switchStatus;

    /** 话题类型：0-普通话题 1-PK话题 2-投票 必填*/
    @NumberValidator(message = "话题类型参数非法")
    private String type;

    /** 选项信息, type=1 或type=2时必填*/
    private List<VoteItemDto> voteItems;

}

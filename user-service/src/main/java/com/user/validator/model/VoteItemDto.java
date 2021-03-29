package com.user.validator.model;

import lombok.Data;

/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/5/22 15:28
 */
@Data
public class VoteItemDto {

    private Integer id;
    private String desc;
    private String content;
    private Long voteCount;
    private boolean voteFlag;
}

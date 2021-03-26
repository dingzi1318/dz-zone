package com.demo.validator.model;

import com.demo.validator.constraint.DateValidator;
import com.demo.validator.group.AccountGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/5/22 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAccount {

    private Long id;

    @NotNull
    @Length(max = 20)
    private String userName;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z][0-9]")
    private String passWord;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTime;

    //  @Pattern(regexp = "[A-Z][a-z][0-9]")
    @Min(2)
    private String alias;

    @Max(value = 10, message = "level 不能大于10")
    @Min(value = 1, message = "level 不能小于1")
    private Integer level;

    private Integer vip;

    @DateValidator(dateFormat = "yyyy-MM-dd", groups = {AccountGroup.class})
    private String birthday;
}

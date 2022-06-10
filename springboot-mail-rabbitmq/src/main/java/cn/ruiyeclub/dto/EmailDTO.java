package cn.ruiyeclub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件
 *
 * @author Cr.
 * @date 2022/06/10
 */
@Data
public class EmailDTO {

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

}

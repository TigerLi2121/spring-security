package com.mm.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lwl
 */
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    public interface SimpleView {
    }

    public interface DetailView extends SimpleView {
    }

    private Long id;

    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    @JsonView(SimpleView.class)
    public Long getId() {
        return id;
    }

    @JsonView(SimpleView.class)
    public String getUsername() {
        return username;
    }

    @JsonView(DetailView.class)
    public String getPassword() {
        return password;
    }

    @JsonView(SimpleView.class)
    public Date getBirthday() {
        return birthday;
    }
}

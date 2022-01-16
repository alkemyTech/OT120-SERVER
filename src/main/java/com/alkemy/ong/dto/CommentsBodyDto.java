
package com.alkemy.ong.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsBodyDto implements Serializable {
    private String body;
}

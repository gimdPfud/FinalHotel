package com.example.finalhotel.dto;


import com.example.finalhotel.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BrandDTO {

    private Long brandNum;

    @NotBlank(message = "제목은 3글자 이상 그리고, 필수로 작성해주세요.")
    @Size(min = 3, max = 50, message = "최소 3글자 최대 50글자로 작성해주세요.")
    private String brandTitle;

    @NotBlank(message = "한글자만 입력하다니 성의 진짜 없네!!")
    @Size(min = 2, max = 2000, message = "한글자만 입력하다니 성의 진짜 없네!! 2~2000까지 작성가능")
    private String brandContent;

    // 도대체 왜이럴까?

    private Member member; // 멤버참조

}

package Bespoke.BespokeBids.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "set") // 생성자 생성에 새로운 이름
public class ResponseDto<D> {

    private boolean result;
    private String message;
    private D data;

    public static <D> ResponseDto<D> setSuccess(String message, D data) {
//        return new ResponseDto(true, message, data);
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }

}

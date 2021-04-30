package kr.ac.jejunu;


import lombok.*;

//@Getter
//@Setter
@Data
@Builder
//기본 생성자 만들어줌
@NoArgsConstructor
//모든 생성자 만들어줌
@AllArgsConstructor
public class User {

//    프로젝트마다 setting->annotation->enable 어쩌구 클릭
    private Integer id;
    private String name;
    private String password;


}

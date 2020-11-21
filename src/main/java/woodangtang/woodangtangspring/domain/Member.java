package woodangtang.woodangtangspring.domain;

public class Member {
    // 시스템이 정의하는 식별을 위한 아이디
    private Long id;
    // 사용자가 입력하는 이름
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

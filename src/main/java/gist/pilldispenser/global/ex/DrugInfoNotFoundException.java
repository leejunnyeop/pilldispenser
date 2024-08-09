package gist.pilldispenser.global.ex;

public class DrugInfoNotFoundException extends RuntimeException {

    public DrugInfoNotFoundException(Long id) {
        super("약 정보를 찾을 수 없습니다. ID: " + id);
    }
}

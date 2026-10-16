package egovframework.nicc.hne.com.model.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Status {
  AAA("AAA", "재직"), //
  AAB("AAB", "재직(파견)"), //
  ABA("ABA", "휴직"), //
  ABB("ABB", "휴직(무급)"), //
  ACA("ACA", "신규임용후보"), //
  BAB("BAB", "파견(전입)"), //
  BAF("BAF", "재직(FIU등)"), //
  BAN("BAN", "재직(국과수)"), //
  BAP("BAP", "재직(급여)"), //
  BAR("BAR", "재직(주재관)"), //
  DAA("DAA", "퇴직"), //
  DAB("DAB", "전출"), //
  DAC("DAC", "파견복귀"), //
  DAF("DAF", "전출(FIU등)"), //
  DAN("DAN", "전출(국과수)"), //
  DAO("DAO", "전출(주재관해제)"), //
  DAR("DAR", "전출(주재관)원소속)");

  private final String code;
  private final String meaning;

  private static final Map<String, Status> CODE_MAP = new HashMap<>();

  static {
    for (Status status : Status.values()) {
      CODE_MAP.put(status.code, status);
    }
  }

  Status(String code, String meaning) {
    this.code = code;
    this.meaning = meaning;
  }

  public String getCode() {
    return code;
  }

  public String getMeaning() {
    return meaning;
  }

  public static Status fromCode(String code) {
    Status status = CODE_MAP.get(code);
    if (status == null) {
      throw new IllegalArgumentException("알 수 없는 재직상태 코드: " + code);
    }
    return status;
  }
}

package egovframework.nicc.hne.layout.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LayoutBackgroundDTO {

    private Integer backgroundCd;
    private Date backgroundNdt;
    private Date backgroundEdt;
    private String backgroundOriginFilename;
    private String backgroundFilename;

    private String orgCd;
    private String orgName;
    private String orgFullcode;
}

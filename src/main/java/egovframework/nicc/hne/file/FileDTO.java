package egovframework.nicc.hne.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
	
	private String originalFilename;
	private String filename;
	private String thumbnail;
	
}
package site.greenwave.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FileController {
	
	
	
	@PostMapping("FileUpload.do")
	public Map<String,Object> FileUploaded (HttpServletRequest request,@RequestParam(value="file",required = false) MultipartFile[] files){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String FileNames = "";
		String filepath = "C:/saveFile/";
		
		for(MultipartFile file : files) {
			String fieldId = ((new Date()).getTime()) + "" + (new Random().ints(1000,9999).findAny().getAsInt());
			String originName = file.getOriginalFilename();
			String fileExtension = originName.substring(originName.lastIndexOf("."));
			originName = originName.substring(0, originName.lastIndexOf("."));
			long fileSize = file.getSize();
			File fileSave = new File(filepath, fieldId+"."+fileExtension);
			if(!fileSave.exists()) {
				fileSave.mkdirs();
			}
			try {
				file.transferTo(fileSave);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		return null;
	}
}

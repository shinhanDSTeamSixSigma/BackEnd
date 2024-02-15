package site.greenwave.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class FileUtil {

	@Autowired
	private FileRepository repo;
	/**
	 * 이미지 하나 이상 가져올 수 있을 때
	 * @param manage_div 파일 div 구분 
	 * @param file_manage_no 해당 관련 번호
	 * @return 존재하면 파일 src 리턴 없으면 null
	 */
	public List<String> getFilesFrom(String manage_div, int file_manage_no){
		List<String> list;
		List<FileEntity> files = repo.findFileByManageDivAndFileManageNo(manage_div, file_manage_no);
		if(files==null || files.isEmpty()) {
			return null;
		}
		list = new ArrayList();
		for(FileEntity file : files) {
			list.add(file.getManageDiv()+"/"+file.getFileSrc()+"."+file.getFileExtension());
		}
		return list;
	}
	/**
	 * 이미지가 하나인 것이 보장 될 때
	 * 프로필/작물 사전 등
	 * @param manage_div
	 * @param file_manage_no
	 * @return 해당 경로 return. 없으면 null
	 */
	public String getFileFrom(String manage_div, int file_manage_no){
		FileEntity file = repo.findTop1ByManageDivAndFileManageNoOrderByUploadDateDesc(manage_div, file_manage_no);
		if(file == null) {
			return null;
		}
		return file.getManageDiv()+"/"+file.getFileSrc()+"."+file.getFileExtension();
	}
	
	public void UploadFile() {
		
	}
	
}

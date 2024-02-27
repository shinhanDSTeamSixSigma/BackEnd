package site.greenwave.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
@Slf4j
@Component
public class FileUtil {
	@Autowired
	private FileRepository repo;
	
	@Value("${filesave.location}")
	private String path;

	Date d = new Date();
	SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMddHHmmssSSS");
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
	/**
	 * 파일들 업로드
	 * @param manage_div 
	 * @param manage_no
	 * @param files 파일들
	 */
	public void UploadFiles(String manage_div, int manage_no, MultipartFile[] files) {
		if(files != null && files.length > 0) {
			for(MultipartFile file : files) {
				if(file == null || file.isEmpty())
					continue;
				this.UploadFile(manage_div, manage_no, file);
			}
		}else {
			return;
		}
	}
	/**
	 * 파일 하나 업로드
	 * @param manage_div
	 * @param manage_no
	 * @param file 실제 파일
	 */
	@Transactional
	public void UploadFile(String manage_div, int manage_no, MultipartFile file) {

		if(file == null || file.isEmpty())
			return;
		//File saveFile = new File();
		//토마토.png
		String fileName = file.getOriginalFilename();
		//토마토.png -> png
		String fileExt = StringUtils.getFilenameExtension(fileName);
		//토마토.png -> 토마토
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		//ㅌㅗㅁㅏㅌㅗ -> 토마토 Mac 에서 저장하는거 대비용
		fileName = Normalizer.normalize(fileName, Normalizer.Form.NFC);
		
		/*try {
			//오늘 베이스로 base64 돌림
			//randVal =  Base64.encodeBase64URLSafeString(sdf.format(d).getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			//인코딩 예외시 그냥 날짜 기준으로
			log.error(e.getMessage());
			randVal = sdf.format(d);
		}*///중복을 피하기 위한 랜덤 생성 이름
		UUID uuid = UUID.randomUUID();
		String changedFileName = uuid.toString();
		//실제 파일 저장
		File rFile = null;
		String fPath = path+manage_div;
		rFile = new File(fPath,changedFileName);
		try {
			file.transferTo(rFile);
			

			//이미지 시 썸네일 저장
			
			if(checkImageType(rFile)) {
				FileOutputStream thumbnail = new FileOutputStream(new File(fPath,"s_"+changedFileName));
				Thumbnailator.createThumbnail(file.getInputStream(),thumbnail,100,100);
				thumbnail.close();
			}
			
		} catch (IllegalStateException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		//DB 저장
		FileEntity fileEntity = FileEntity.builder().manageDiv(manage_div)
				.fileManageNo(Integer.valueOf(manage_no))
				.fileName(fileName)
				.fileSrc(changedFileName)
				.fileExtension(fileExt)
				.build();
		log.info(fileEntity.toString());
		repo.save(fileEntity);
	}
	
	/**
	 * 크롭 파일 업로드
	 * @param manage_no
	 * @param file 실제 파일
	 */
	@Transactional
	public void uploadCropImage(int manage_no, MultipartFile file) {
		String manage_div = "CROP";
		if(file == null || file.isEmpty())
			return;
		//File saveFile = new File();
		//토마토.png
		String fileName = file.getOriginalFilename();
		//System.out.println("==========================[original file name] "+fileName);
		//토마토.png -> png
		String fileExt = StringUtils.getFilenameExtension(fileName);
		//System.out.println("==========================[data file extension] "+fileExt);
		//토마토.png -> 토마토
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		//ㅌㅗㅁㅏㅌㅗ -> 토마토 Mac 에서 저장하는거 대비용
		fileName = Normalizer.normalize(fileName, Normalizer.Form.NFC);
		//System.out.println("==========================[data file name] "+fileName);
		/*try {
			//오늘 베이스로 base64 돌림
			//randVal =  Base64.encodeBase64URLSafeString(sdf.format(d).getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			//인코딩 예외시 그냥 날짜 기준으로
			log.error(e.getMessage());
			randVal = sdf.format(d);
		}*///중복을 피하기 위한 랜덤 생성 이름
		UUID uuid = UUID.randomUUID();
		String changedFileName = uuid.toString();
		changedFileName = changedFileName +"." + fileExt;
		//실제 파일 저장
		File rFile = null;
		String fPath = path+manage_div;
		rFile = new File(fPath,changedFileName);
		try {
			file.transferTo(rFile);
			

			//이미지 시 썸네일 저장
			
		} catch (IllegalStateException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		//DB 저장
		FileEntity fileEntity = FileEntity.builder().manageDiv(manage_div)
				.fileManageNo(Integer.valueOf(manage_no))
				.fileName(fileName)
				.fileSrc(changedFileName)
				.fileExtension(fileExt)
				.build();
		log.info(fileEntity.toString());
		repo.save(fileEntity);
	}
	
	
	
	
	/**
	 * 파일 삭제. 걍 DB에서파일 삭제 할 것
	 * @param manage_div
	 * @param manage_no
	 */
	@Transactional
	public void DeleteFile(String manage_div,int manage_no) {
		List<FileEntity> fileEntitys = repo.findFileByManageDivAndFileManageNo(manage_div, manage_no);
		for(FileEntity file : fileEntitys) {
			repo.delete(file);
		}
	}
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

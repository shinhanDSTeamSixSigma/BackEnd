package site.greenwave.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

	public List<FileEntity> findFileByManageDivAndFileManageNo(String manageDiv, int fileManageNo);

	//DICT, 2
	//
	//DICT/potato + png ( file_src + file_extension)
	//localhost:8090/img/DICT/potato.png
	public FileEntity findTop1ByManageDivAndFileManageNoOrderByUploadDateDesc(String manageDiv, int fileManageNo);


	// fileManageNo로 fileName 찾기

	void deleteByManageDivAndFileManageNoAndFileName(String manageDiv, int fileManageNo, String fileName);
	public List<FileEntity> findByFileManageNoAndManageDiv(Integer fileManageNo, String manageDiv);
}
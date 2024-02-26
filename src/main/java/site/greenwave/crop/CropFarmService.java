package site.greenwave.crop;

import java.util.List;
import java.util.Map;


public interface CropFarmService {
	List<Map> getCropsFromFarmNoAndMemberNo(int farmNo, int memberNo);
	List<Map> getCropsWhatIHave(int memberNo);
	List<Map> getDoneCropsWhatIHave(int memberNo);
	Map getCropNameAndDateFromCropNo(int cropNo);
	List<Map> getCropDictWithImage();
}

package site.greenwave.crop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CropFarmServiceImpl implements CropFarmService {
	@Autowired
	CropMapper cropMapper;
	@Override
	public List<Map> getCropsFromFarmNoAndMemberNo(int farmNo, int memberNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("member_no", memberNo);
		map.put("farm_no", farmNo);
		return cropMapper.getCropsFromMemberNoAndFarmNo(map);
	}
	@Override
	public List<Map> getCropsWhatIHave(int memberNo) {
		return cropMapper.getCropsWhatIHave(memberNo);
	}
	@Override
	public List<Map> getDoneCropsWhatIHave(int memberNo) {
		return cropMapper.getDoneCropsWhatIHave(memberNo);
	}

}

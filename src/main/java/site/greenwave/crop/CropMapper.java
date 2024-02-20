package site.greenwave.crop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CropMapper {
	/**
	 * Farm No랑 Member No 를 바탕으로 Crop 리스트를 리턴한다
	 * @param memberNo member_no 
	 * @param farmNo farm_no
	 * @return Crop List를 리턴한다. 
	 */
	public List<Map> getCropsFromMemberNoAndFarmNo(Map mp);
}

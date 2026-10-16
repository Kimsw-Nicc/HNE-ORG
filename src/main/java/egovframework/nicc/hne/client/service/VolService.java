package egovframework.nicc.hne.client.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.nicc.hne.client.model.dto.ClientVolDTO;
import egovframework.nicc.hne.client.model.entity.ClientVol;
import egovframework.nicc.hne.client.repo.ClientVolRepo;
import egovframework.nicc.hne.client.service.VolService;
import egovframework.nicc.hne.util.ModelConverter;

@Service
public class VolService {

	public static final String TOUR = "VOL_TOUR";
	public static final String BACKGROUND = "VOL_BGIMG";
	public static final String ABOUT = "VOL_ABOUT";
	public static final String ALERT = "VOL_ALERT";
	public static final String BOARD = "VOL_BOARD";
	public static final String BUILDING = "VOL_BUILDING";
	public static final String BUREAU = "VOL_BUREAU";
	public static final String CALENDAR = "VOL_CALENDAR";
	public static final String FOOD = "VOL_FOOD";
	public static final String IMAGE = "VOL_IMAGE";
	public static final String PATCH = "VOL_PATCH";
	public static final String SECTION = "VOL_SECTION";
	public static final String STAFF = "VOL_STAFF";
	public static final String TEAM = "VOL_TEAM";
	public static final String VIDEO = "VOL_VIDEO";
	public static final String BASIC = "VOL_BASIC";
	public static final String CONF = "VOL_CONF";
	public static final String LAYOUT = "VOL_LAYOUT";

	@Autowired
	ClientVolRepo clientVolRepo;
	@Autowired
	ModelConverter modelConverter;

	public ClientVolDTO getClientVol() {
		return modelConverter.map(clientVolRepo.findOne(1), ClientVolDTO.class);
	}

	public void updateClientVol(String column) {
		// 추후 반드시 QueryDSL을 통해 최적화
		ClientVol clientVol = clientVolRepo.findOne(1);
		Date date = new Date();
		switch (column) {
		case TOUR:
			clientVol.setVolTour(date);
			break;
		case BACKGROUND:
			clientVol.setVolBgImg(date);
			break;
		case ABOUT:
			clientVol.setVolAbout(date);
			break;
		case ALERT:
			clientVol.setVolAlert(date);
			break;
		case BOARD:
			clientVol.setVolBoard(date);
			break;
		case BUILDING:
			clientVol.setVolBuilding(date);
			break;
		case BUREAU:
			clientVol.setVolBureau(date);
			break;
		case CALENDAR:
			clientVol.setVolCalendar(date);
			break;
		case FOOD:
			clientVol.setVolFood(date);
			break;
		case IMAGE:
			clientVol.setVolImage(date);
			break;
		case PATCH:
			clientVol.setVolPatch(date);
			break;
		case SECTION:
			clientVol.setVolSection(date);
			break;
		case STAFF:
			clientVol.setVolStaff(date);
			break;
		case TEAM:
			clientVol.setVolTeam(date);
			break;
		case VIDEO:
			clientVol.setVolVideo(date);
			break;
		case BASIC:
			clientVol.setVolBasic(date);
			break;
		case CONF:
			clientVol.setVolConf(date);
			break;
		case LAYOUT:
			clientVol.setVolLayout(date);
			break;
		default:
			break;
		}
		clientVolRepo.save(clientVol);
	}

}

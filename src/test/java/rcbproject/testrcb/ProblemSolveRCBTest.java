package rcbproject.testrcb;


import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ProblemSolveRCBTest {

	public static JSONArray playersList;

	@BeforeMethod
	public void readJson() throws IOException, ParseException {
		JSONParser jsonparse = new JSONParser();
		FileReader reader = new FileReader(".\\testdata\\rcbteamdetails.json");
		Object obj = jsonparse.parse(reader);
		JSONObject teamjsonobj = (JSONObject) obj;
		playersList = (JSONArray) teamjsonobj.get("player");
	}

	@Test
	public void validateForeignPlayer() throws IOException, ParseException {
		int expforginPlayerscount = 4;
		int actlforginPlayerscount = 0;

		for (int i = 0; i < playersList.size(); i++) {
			JSONObject players = (JSONObject) playersList.get(i);
			String playerCountry = (String) players.get("country");
			if (!playerCountry.equals("India")) {
				actlforginPlayerscount++;
			}

		}
		Assert.assertEquals(expforginPlayerscount, actlforginPlayerscount);

	}

	@Test
	public void validateWicketKeeper() throws IOException, ParseException {
		int actwicketkeepercount = 0;
		int wicketkeepercount = 0;

		for (int i = 0; i < playersList.size(); i++) {
			JSONObject players = (JSONObject) playersList.get(i);
			String playerrole = (String) players.get("role");
			if (playerrole.equals("Wicket-keeper")) {
				actwicketkeepercount++;
			}
		}
		Assert.assertNotSame(wicketkeepercount, actwicketkeepercount);
	}

}

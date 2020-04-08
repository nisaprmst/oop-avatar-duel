package com.avatarduel.cards;

import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CardLoader {
    public List<String[]> readFromCSV(String path) throws URISyntaxException, IOException {
        File cardFile = new File(getClass().getResource(path).toURI());
        CSVReader CSVFile = new CSVReader(cardFile, "\t");
        CSVFile.setSkipHeader(true);

        return CSVFile.read();
    }

    public ArrayList<CharacterCard> loadCharacterCardsFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);
        ArrayList<CharacterCard> loadedCharacterCards = new ArrayList<>();
        for (String[] row : fileRows) {
            CharacterCard cc = new CharacterCard(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]), row[4],
                                Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]));
            loadedCharacterCards.add(cc);
        }
        return loadedCharacterCards;
    }

    public ArrayList<LandCard> loadLandCardsFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);
        ArrayList<LandCard> loadedLandCards = new ArrayList<>();
        for (String[] row : fileRows) {
            LandCard lc = new LandCard(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]), row[4]);
            loadedLandCards.add(lc);
        }
        return loadedLandCards;
    }

    public ArrayList<AuraSkill> loadAuraSkillFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);

        ArrayList<AuraSkill> loadedAuraSkills = new ArrayList<>();
        for (String[] row : fileRows) {
             AuraSkill as = new AuraSkill(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                            row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]));
            loadedAuraSkills.add(as);
        }
        return loadedAuraSkills;
    }
}

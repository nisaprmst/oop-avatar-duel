package com.avatarduel.cards;

import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is loader for card data from given csv file path.
 */
public class CardLoader {
    private ArrayList<Card> loadedCards;

    public CardLoader() {
        loadedCards = new ArrayList<>();
    }

    public ArrayList<Card> getLoadedCards() {
        return loadedCards;
    }

    private List<String[]> readFromCSV(String path) throws URISyntaxException, IOException {
        File cardFile = new File(getClass().getResource(path).toURI());
        CSVReader CSVFile = new CSVReader(cardFile, "\t");
        CSVFile.setSkipHeader(true);

        return CSVFile.read();
    }

    /**
     * Read the data from csv file in given path and store the data to array list of Card
     * @param path path for Character Card csv file
     * @throws IOException exception when reading csv file. for example: file not found error
     * @throws URISyntaxException exception when a string could not  be parsed as a URI reference
     */
    public void loadCharacterCardsFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);
        for (String[] row : fileRows) {
            CharacterCard cc = new CharacterCard(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]), row[4],
                                Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]));
            loadedCards.add(cc);
        }
    }

    /**
     * Read the data from csv file in given path and store the data to array list of Card
     * @param path path for Land Card csv file
     * @throws IOException exception when reading csv file. for example: file not found error
     * @throws URISyntaxException exception when a string could not  be parsed as a URI reference
     * @return ArrayList of LandCard loaded from path
     */
    public ArrayList<LandCard> loadLandCardsFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);
        ArrayList<LandCard> loadedLandCards = new ArrayList<>();
        for (String[] row : fileRows) {
            LandCard lc = new LandCard(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]), row[4]);
            loadedLandCards.add(lc);
        }
        return loadedLandCards;
    }

    /**
     * Read the data from csv file in given path and store the data to array list of Card
     * @param path path for Character Card csv file
     * @throws IOException exception when reading csv file. for example: file not found error
     * @throws URISyntaxException exception when a string could not  be parsed as a URI reference
     */
    public void loadAuraSkillFromFile(String path) throws IOException, URISyntaxException {
        List<String[]> fileRows = readFromCSV(path);
        for (String[] row : fileRows) {
             AuraSkill as = new AuraSkill(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]),
                            row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]));
            loadedCards.add(as);
        }
    }
}
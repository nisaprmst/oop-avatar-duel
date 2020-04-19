package com.avatarduel.gamemanager.phase;

import com.avatarduel.cards.skills.SkillCard;
import com.avatarduel.exceptions.NoCardInFieldException;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.GameManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainPhaseTest {
    private MainPhase mainPhase;
    private GameManager gameManager;

    @Before
    public void setUp() {
        mainPhase = new MainPhase(GameManager.getGameManager());
        gameManager = GameManager.getGameManager();
    }

    @Test
    public void getType() {
        assertEquals(PhaseType.MAIN, mainPhase.getType());
    }

    @Test
    public void nextPhase() {
        mainPhase.nextPhase();
        assertEquals(PhaseType.BATTLE, GameManager.getGameManager().getPhase().getType());
    }

    @Test
    public void setSkillCard() {
        SkillCard skillCard = new SkillCard();
    }

    @Test
    public void setLandCard() {
    }

    @Test
    public void addAuratoCharacter() {
    }

    @Test
    public void destroyEnemyCharacter() {
    }

    @Test
    public void addPowerUptoCharacter() {
    }

    @Test
    public void changePositionCharacter() {
    }

    @Test
    public void removeSkillCard() {
    }
}
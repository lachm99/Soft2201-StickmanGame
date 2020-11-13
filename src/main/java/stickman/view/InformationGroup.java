package stickman.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import stickman.model.GameEngine;

public class InformationGroup {

    private final GameEngine gameEngine;
    private final Pane pane;

    private Label extraLives;
    private Label cumulativeScore;
    private Label levelScore;
    private Label levelTimer;

    public InformationGroup(GameEngine gameEngine, Pane pane) {
        this.gameEngine = gameEngine;
        this.pane = pane;


        extraLives = createInfoLabel();
        cumulativeScore = createInfoLabel();
        levelScore = createInfoLabel();
        levelTimer = createInfoLabel();

        pane.getChildren().addAll(extraLives, cumulativeScore, levelScore, levelTimer);

        extraLives.relocate(0, 0);

        cumulativeScore.relocate(pane.getPrefWidth() - cumulativeScore.getPrefWidth(), 0);

        levelTimer.relocate(0, pane.getPrefHeight() - levelTimer.getPrefHeight());

        levelScore.relocate(pane.getPrefWidth() - levelScore.getPrefWidth(), pane.getPrefHeight() - levelScore.getPrefHeight());
    }

    private static Label createInfoLabel() {
        Label infoLabel = new Label();
        infoLabel.setPrefSize(100, 40);
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        infoLabel.setTextFill(Color.WHITESMOKE);
        infoLabel.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, new CornerRadii(4), new Insets(4))));
        infoLabel.setOpacity(0.8);
        infoLabel.setGraphic(new ImageView());
        return infoLabel;
    }

    public void updateInformation() {
        extraLives.setText(String.format(" Lives: %4d", gameEngine.getExtraLivesRemaining()));
        cumulativeScore.setText(String.format(" Total: %04d", gameEngine.getCumulativeScore()));
        levelTimer.setText(String.format(" Timer: %03d", gameEngine.getCurrentLevel().getTimeElapsed()));
        levelScore.setText(String.format(" Score: %04d", gameEngine.getCurrentLevel().getCurrentScore()));
    }

}

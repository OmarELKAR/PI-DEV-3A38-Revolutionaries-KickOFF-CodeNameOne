/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import entities.Matche;
import services.MatcheService;

/**
 *
 * @author omare
 */
public class showMatcheForm extends Form {
    MatcheService ms = MatcheService.getInstance();
    
    public showMatcheForm(int id) {
        super("Matche", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt4) -> {
            new LoginForm().showBack();
        });
        for (Matche m : ms.showMatche(id)){
            showM(m);
        }
    }
    
    public void showM(Matche match){
        Label idLabel = new Label("ID: " + match.getId());
        this.add(idLabel);

        Label nameLabel = new Label("Name: " + match.getName());
        this.add(nameLabel);

        Label jmaxLabel = new Label("Jmax: " + match.getJmax());
        this.add(jmaxLabel);

        Label dateLabel = new Label("Date: " + match.getDate());
        this.add(dateLabel);

        Label timeLabel = new Label("Time: " + match.getTime());
        this.add(timeLabel);
        
        Label weatherLabel = new Label("Weather: ");
        this.add(weatherLabel);

        Label etatLabel = new Label("Etat: " + match.getEtat());
        this.add(etatLabel);
        

        SpanLabel team1Label = new SpanLabel("Team 1: " + match.getTeam1());
        this.add(team1Label);

        Label team2Label = new Label("Team 2: " + match.getTeam2());
        this.add(team2Label);

        this.refreshTheme();
    }
}

package app.ui.views.game.help;
import javax.swing.*;
import java.awt.*;

import app.ui.views.components.BaseJFrame;
public class HelpScreenFrame extends BaseJFrame {
    private JTextArea instructions;
    private JScrollPane scrollPane;

    public HelpScreenFrame(String title, Dimension size) {
        super(title, size);
        this.setLayout(new BorderLayout());
        initializeComponents();
        buildComponents();
    }

    @Override
    public void initializeComponents() {
        scrollPane = new JScrollPane(instructions);
        instructions = new JTextArea("""
                How to play:

                1. In Draft phase, place new units on your territories.
                2. In Attack phase, select the territory of the troops you will use in the attack and select the enemy territory.
                3. In Fortify phase, move any number of armies from one territory into an adjacent territory.
                4. Use your territory and army cards wisely to dominate the game.
                5. If a player lost all its territories, it will be defeated from the game.
                6. Eliminate all enemy players to gain a glorious victory, Sir Commander!""");
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);
        instructions.setEditable(false);
        this.setLocationRelativeTo(getRootFrame());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponents();

        this.refresh();
    }

    public void addComponents() {
        this.add(scrollPane);
        this.add(instructions);
    }

    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }
}

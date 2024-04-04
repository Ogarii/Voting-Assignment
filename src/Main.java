import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
    private JLabel statusLabel;
    private JButton voteButton;
    private char vote;

    //


    private ArrayList<Character> receivedVotes;

    public Main() {
        setTitle("Election Vote Casting App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusLabel = new JLabel("Cast your vote by clicking the button.");
        voteButton = new JButton("Vote");

        receivedVotes = new ArrayList<>();

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vote = JOptionPane.showInputDialog("Enter your vote (A or B)").toUpperCase().charAt(0);
                // Simulate multicast message passing to other electorates
                receivedVotes.add(vote);
                // Determine winner and update status label
                char winner = determineWinner();
                statusLabel.setText("Winner: " + winner + " | Your Vote: " + vote);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(voteButton);
        panel.add(statusLabel);

        add(panel);
        setVisible(true);
    }

    private char determineWinner() {
        int votesForA = 0;
        int votesForB = 0;
        for (char vote : receivedVotes) {
            if (vote == 'A') {
                votesForA++;
            } else if (vote == 'B') {
                votesForB++;
            }
        }
        if (votesForA > votesForB) {
            return 'A';
        } else if (votesForB > votesForA) {
            return 'B';
        } else {
            return 'T'; // T indicates a tie
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}

package ru.atom.files;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CowsAndBulls extends JFrame implements ActionListener {

    private final JTextArea textArea = new JTextArea();
    private final JTextField textField = new JTextField();
    private final JButton button = new JButton();
    private static String initWord;
    private int count;
    private static int cows;
    private static int bulls;

    public CowsAndBulls(){
        super("CowsAnaBulls");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(false);
        button.setLayout(null);
        textField.setLayout(null);
        textArea.setEditable(false);
        button.setBounds(475,10,100,40);
        button.setText("New Game");
        button.addActionListener(new TestActionListener());

        JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel j = new JPanel();
        j.setBounds(0,0,600,400);
        textField.setBounds(10,10,450,20);
        scrollPane.setBounds(10,50,450,300);
        add(button);
        add(textField);
        add(scrollPane);
        add(j);
        textField.addActionListener(this);
        initGame();
        setVisible(true);
    }

    class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            initGame();
        }
    }

    public static void main(String[] args) {
     CowsAndBulls cw =  new CowsAndBulls();
    }

    public void initGame() {
        List<String> lines = null;
        try {
            lines = ResourceReader.readFromResource("Dictionary.txt");
            Random random = new Random();
            int i = random.nextInt(ResourceReader.getSize() + 1);
            initWord = lines.get(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        count = 0;
        textField.setText("");
        textArea.setText("");
        textField.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = textField.getText();
        if (word.equals("")) return;
        if(word.equals(initWord)){
            textArea.append("\n" + word + "\n" + "Cows :" + (cows - bulls) + "  " + "Bulls :" + bulls +"You are Winner");
        }
        else {
            textField.setText(null);
            cows = cows(word);
            bulls = bulls(word);
            if(word.length() > initWord.length()){
            if (textArea.getText().equals("")) textArea.append(word.substring(0,initWord.length()) + "\n" + "Cows :" + (cows - bulls) + "  " + "Bulls :" + bulls);
            else textArea.append("\n" + word.substring(0,initWord.length()) + "\n" + "Cows :" + (cows - bulls) + "  " + "Bulls :" + bulls);
            }
            else{
                if (textArea.getText().equals("")) textArea.append(word + "\n" + "Cows :" + (cows - bulls) + "  " + "Bulls :" + bulls);
                else textArea.append("\n" + word + "\n" + "Cows :" + (cows - bulls) + "  " + "Bulls :" + bulls);
        }
            count++;
            if (count >= 10) {
                textArea.append("\n" + "You lose" + "   " + "the Word is :" + initWord);
                textField.removeActionListener(this);
            }
        }
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private static int cows(String word) {
        int c = 0;
        char[] chWord = stringToChar(word);
        if (word.length() <= initWord.length()) {
            FIRST_LOOP: for (int j = 0; j < word.length(); j++) {
                for (int i = 0; i < word.length(); i++) {
                    char ch = initWord.charAt(j);
                    char chCount[] = new char[word.length()];
                    if (chWord[i] == (ch) && chCount[j]!=ch) {
                        c++;
                        chCount[j]=ch;
                        continue FIRST_LOOP;
                    }
                }
            }
        }
        else {
                FIRST_LOOP: for (int j = 0; j < initWord.length(); j++) {
                    for (int i = 0; i < initWord.length(); i++) {
                        char ch = initWord.charAt(j);
                        char chCount[] = new char[word.length()];
                        if (chWord[i] == (ch) && chCount[j] != ch) {
                            c++;
                            chCount[j] = ch;
                            continue FIRST_LOOP;
                        }
                    }
                }
            }
        return c;
    }

    public static char[] stringToChar(String str){
        char[] ch = new char[str.length()];
        for (int i=0;i<str.length();i++){
            ch[i] = str.charAt(i);
        }
        return ch;
    }

    private static int bulls(String word) {
        int c = 0;
        if (word.length() <= initWord.length()) {
            for (int i = 0; i < word.length(); i++) {
                Character chWord = word.charAt(i);
                Character chInit = initWord.charAt(i);
                if (chWord.equals(chInit)) c++;
            }
        } else {
            for (int i = 0; i < initWord.length(); i++) {
                Character chWord = word.charAt(i);
                Character chInit = initWord.charAt(i);
                if (chWord.equals(chInit)) c++;
            }
        }
        return c;
    }
}

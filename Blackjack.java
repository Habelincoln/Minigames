import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
@SuppressWarnings("unused")
public class Blackjack extends JPanel implements ActionListener {

    static boolean menuIsOpen = false;
    static boolean running = false;
    static boolean won = false;
    static boolean lost = false;
    static boolean turn = false;
    static boolean muted = false;
    static boolean musicMuted = false;
    static boolean isDarkMode = false;
    static boolean dealersTurn = true;
    static int dealerCardCount = 0;
    static int playerCardCount = 0;
    static volatile int dealerHand = 0;
    static volatile int playerHand = 0;
    static volatile int bet = 0;
    static volatile int chips = 1000;
    static volatile JPanel betPanel = new JPanel();
    
    private Map<String, ImageIcon> cardImages = new HashMap<>();
    private List<String> deck = new ArrayList<>();


    Blackjack() {
        JFrame frame = new JFrame("Blackjack");
        JPanel game = new JPanel();
        JTextArea title = new JTextArea();
        JTextArea dealer = new JTextArea();
        JTextArea player = new JTextArea();
        JPanel menu = new JPanel();

        betPanel.setBounds(275,225,250,150);
        betPanel.setBackground(new Color(90,116,0));
        
        game.add(betPanel);

        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game.setSize(800,600);
        game.setBackground(new Color(3,116,0));

        menu.setSize(100,600);
        menu.setBackground(Color.BLACK);
        menu.setVisible(false);

        JButton hit = new JButton("HIT");
        JButton doubleDown = new JButton("Double");
        JButton split = new JButton("SPLIT");
        JButton stand = new JButton("STAND");
        JButton settings = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\settings.png"));
        JButton restart = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\restart.png"));
        JButton undo = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\undo.png"));
        JButton soundToggle = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\sound.png"));
        JButton musicToggle = new JButton (new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\music.png"));
        JButton darkMode = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\darkModeOff.png"));
        JButton hint = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\qMark.png"));
        JButton black = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipBlack.png"));
        JButton blue = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipBlue.png"));
        JButton green = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipGreen.png"));
        JButton teal = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipTeal.png"));
        JButton pink = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipPink.png"));
        JButton red = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipRed.png"));
        JButton white = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipWhite.png"));
        JButton yellow = new JButton(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Chips\\chipYellow.png"));
        JButton confirmBet = new JButton("Confirm");
        JButton resetBet = new JButton("Reset");

        JTextArea menuTitle = new JTextArea();
        menuTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        menuTitle.setText("MENU");
        menuTitle.setEditable(false);
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setBackground(Color.BLACK);
        menuTitle.setBounds(120,10,100,30);
        menu.add(menuTitle);

        hit.setBackground(Color.GREEN);
        hit.setForeground(Color.BLACK);
        hit.setBorderPainted(false);
        hit.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        stand.setBackground(Color.RED);
        stand.setForeground(Color.BLACK);
        stand.setBorderPainted(false);
        stand.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        split.setBackground(Color.ORANGE);
        split.setForeground(Color.BLACK);
        split.setBorderPainted(false);
        split.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        doubleDown.setBackground(Color.BLUE);
        doubleDown.setForeground(Color.BLACK);
        doubleDown.setBorderPainted(false);
        doubleDown.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        confirmBet.setBackground(Color.ORANGE);
        confirmBet.setForeground(Color.black);
        confirmBet.setBounds(225,450,150,100);
        game.add(confirmBet);
        confirmBet.setVisible(false);

        resetBet.setBackground(Color.red);
        resetBet.setForeground(Color.black);
        resetBet.setBounds(425,450,150,100);
        game.add(resetBet);
        resetBet.setVisible(false);

        black.setBounds(630,230,60,60);
        blue.setBounds(630,90,60,60);
        green.setBounds(700,160,60,60);
        teal.setBounds(700,230,60,60);
        pink.setBounds(630,160,60,60);
        red.setBounds(700,90,60,60);
        white.setBounds(630,20,60,60);
        yellow.setBounds(700,20,60,60);
        

        game.add(black);
        game.add(blue);
        game.add(green);
        game.add(teal);
        game.add(pink);
        game.add(red);
        game.add(white);
        game.add(yellow);

        restart.setBounds(60,55,60,60); //60 on left and right, buttons 60, 60 in between // for height, buttons are 240 (60x3 + gaps of 20), so 55 on each end.
        menu.add(restart);

        undo.setBounds(180, 55, 60, 60);
        menu.add(undo);

        soundToggle.setBounds(60, 135, 60, 60);
        menu.add(soundToggle);

        musicToggle.setBounds(180, 135, 60, 60);
        menu.add(musicToggle);

        darkMode.setBounds(60,215,60,60);
        menu.add(darkMode);

        hint.setBounds(180, 215, 60,60);
        menu.add(hint);
        

        hit.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                hit(game);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!menuIsOpen){
                hit.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuIsOpen){
                hit.setForeground(Color.BLACK);
            }
        }
        });

        stand.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!menuIsOpen){
                stand.setForeground(Color.WHITE);
            }
        }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuIsOpen){
                stand.setForeground(Color.BLACK);
            }
        }

        });

        split.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!menuIsOpen){
                split.setForeground(Color.WHITE);
            }
        }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuIsOpen){
                split.setForeground(Color.BLACK);
            }
        }

        });

        doubleDown.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!menuIsOpen){
                doubleDown.setForeground(Color.WHITE);
            }
        }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuIsOpen){
                doubleDown.setForeground(Color.BLACK);
            }
        }

        });

       settings.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("settings clicked.");
                if (!menuIsOpen) {
                    openMenu(frame, menu);

            } else {
                closeMenu(frame, menu);
            }
            }

            

            @Override
            public void mouseEntered(MouseEvent e) {
                settings.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\settingsDark.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settings.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\settings.png"));
            }

        });

        restart.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("restart clicked.");
                restartGame();
            }

            

            @Override
            public void mouseEntered(MouseEvent e) {
                restart.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\restartDark.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restart.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\restart.png"));
            }

        });

        undo.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("undo clicked.");
                undo();
            }

            

            @Override
            public void mouseEntered(MouseEvent e) {
                undo.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\undoDark.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                undo.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\undo.png"));
            }

        });

        soundToggle.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("sound toggle clicked.");
                
                if (!muted) {
                soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\muteDark.png"));
                } else {
                    soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\soundDark.png"));
                }
                soundToggle();
            }

            

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!muted) {
                soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\soundDark.png"));
                } else {
                    soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\muteDark.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!muted) {
                soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\sound.png"));
                } else {
                    soundToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\mute.png"));
                }
            }

        });

        musicToggle.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("music toggle clicked.");
                
                if (!musicMuted) {
                musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\musicMutedDark.png"));
                } else {
                    musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\musicDark.png"));
                }
                musicToggle();
            }

            

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!musicMuted) {
                musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\musicDark.png"));
                } else {
                    musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\musicMutedDark.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!musicMuted) {
                musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\music.png"));
                } else {
                    musicToggle.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\musicMuted.png"));
                }
            }

        });
        
        darkMode.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("dark mode clicked.");
                if (isDarkMode) {
                    darkMode.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\darkModeOff.png"));
                    isDarkMode = false;
                } else {
                    darkMode.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\darkModeOn.png"));
                    isDarkMode = true;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });

        hint.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("hint clicked.");
                hint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hint.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\qMarkDark.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hint.setIcon(new ImageIcon("C:\\GitHub\\Minigames\\blackjackFiles\\Buttons\\qMark.png"));
            }

        });

        
        
        white.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 10;
                bet += 10;
                System.out.println("Bet: " + bet + ", chips : " + chips);
                JLabel betLabel = new JLabel(Integer.toString(bet));
                betLabel.setFont(new Font("Arial", Font.PLAIN, 50));
                betLabel.setForeground(Color.WHITE);
                betPanel.remove(betLabel);
                betPanel.add(betLabel);
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        
        blue.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 50;
                bet += 50;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        
        pink.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 100;
                bet += 100;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        black.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 500;
                bet += 500;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        
       yellow.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 1000;
                bet += 1000;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
       red.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 2500;
                bet += 2500;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        green.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 5000;
                bet += 5000;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
       teal.addMouseListener(new MouseListener () {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                chips -= 10000;
                bet += 10000;
                game.revalidate();
                game.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {}
        

        });
        
        
        menu.setLayout(null);
        game.setLayout(null); //disable managers to do it manually
        hit.setBounds(150, 400, 100, 125);
        doubleDown.setBounds(40, 400, 100, 125);
        stand.setBounds(540, 400, 100, 125);
        split.setBounds(650, 400, 100, 125);
        settings.setBounds(10,10,60,60);
        
        game.add(hit);
        game.add(stand);
        game.add(doubleDown);
        game.add(split);
        game.add(settings);

        game.setFocusable(true);
        game.requestFocusInWindow();

        menu.setFocusable(true);
        menu.requestFocusInWindow();

        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLayout(null);
        
        menu.setBounds(250,100,300,350);

        frame.add(menu);
        frame.add(game);
        frame.revalidate();  
        frame.repaint();
        startGame();
    //     while(running){
    //         try {
    //              setupDeck();
    //             hit.setVisible(false);
    //             doubleDown.setVisible(false);
    //             stand.setVisible(false);
    //             split.setVisible(false);
                
    //             confirmBet.setVisible(true);
    //             resetBet.setVisible(true);






    //         hit(game);
    //         Thread.sleep(1000);
    //         dealersTurn = false;
    //         hit(game);
    //         Thread.sleep(1000);

    //         dealersTurn = true;
    //         hit(game);
    //         Thread.sleep(1000);

    //         dealersTurn = false;
    //         hit(game);
    //         Thread.sleep(1000);

    //         //end init game






    //     } catch (InterruptedException e){
    //         e.printStackTrace(System.err);
    //     }
    // }
    }

    public void openMenu(JFrame frame, JPanel menu) {
        running = false;
        menu.setVisible(true);
        frame.revalidate();  
        frame.repaint();
        menuIsOpen = true;
        System.out.println("opening menu");

    }

    public void closeMenu(JFrame frame, JPanel menu) {
        running = true;
        menu.setVisible(false);
        frame.revalidate();  
        frame.repaint();
        menuIsOpen = false;
        System.out.println("closing menu");
    }

    public void startGame(){
        if (!running){
            setupDeck();
            running = true;
        }
    }

    public void drawCard() {

    }

    public void drawDealerCard() {

    }

    public void checkWin() {
        if (!running) {
            if (won) {

            } else if (lost) {

            }
        }
    }

    public void restartGame() {

    }

    public void undo() {

    }

    public void hint() {

    }

    public void soundToggle() {
        if (muted){
            sound();
        } else if (!muted) {
            mute();
        }
    }

    public void mute() {
        muted = true;
    }

    public void sound() {
        muted = false;

    }

    public void musicToggle() {
        if (musicMuted){
            music();
        } else if (!musicMuted) {
            musicMute();
        }
    }

    public void musicMute() {
        musicMuted = true;
    }

    public void music() {
        musicMuted = false;

    }
    
    private void setupDeck() {
        // Create a deck with all the cards
        String[] suits = {"S", "D", "H", "C"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        
        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + suit);
                String card = value + suit; //make hashmap with images
                String imagePath = "C:\\GitHub\\Minigames\\blackjackFiles\\Playing Cards\\" + card + ".png";
                ImageIcon cardImage = new ImageIcon(imagePath);
                cardImages.put(card, cardImage);      
            }
        }
        
        
        Collections.shuffle(deck);
    }

    public void hit(JPanel game) {
        
        if (!deck.isEmpty()) {
            System.out.println("dealing card...");
            String card = deck.remove(0);
            System.out.println(card);//for debug
            ImageIcon cardImage = cardImages.get(card);
            
            JLabel cardLabel = new JLabel(cardImage);
            if (dealersTurn) {
                switch (dealerCardCount) {
                    case 0 -> cardLabel.setBounds(150,30 , 100, 145);
                    case 1 -> cardLabel.setBounds(180,30 , 100, 145);
                    case 2 -> cardLabel.setBounds(210,30 , 100, 145);
                    case 3 -> cardLabel.setBounds(240,30 , 100, 145);
                    case 4 -> cardLabel.setBounds(270,30 , 100, 145);
                    case 5 -> cardLabel.setBounds(300,30 , 100, 145);
                    case 6 -> cardLabel.setBounds(330,30 , 100, 145);
                    case 7 -> cardLabel.setBounds(360,30 , 100, 145);
                    case 8 -> cardLabel.setBounds(390,30 , 100, 145);
                    case 9 -> cardLabel.setBounds(420,30 , 100, 145);
                    case 10 -> cardLabel.setBounds(450,30 , 100, 145);
                    case 11 -> cardLabel.setBounds(480,30 , 100, 145);
                    case 12 -> cardLabel.setBounds(510,30 , 100, 145);

                }
            }
            game.add(cardLabel);
            dealerCardCount++;
            
            game.revalidate();
            game.repaint();
        } else { 
            System.out.println("Deck empty");
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }



    public static void main(String[] args) {
        new Blackjack();
    }
}
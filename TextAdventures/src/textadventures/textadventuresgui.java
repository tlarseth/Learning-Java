package textadventures;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class textadventuresgui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea gameTextArea;
	private JLabel playerHealthLabel;
	private JLabel enemyHealthLabel;
	
	// A panel to hold the different menu layouts using CardLayout
	private JPanel cardPanel;
	private CardLayout cardLayout;
	
	// Separate panels for each menu state
	private JPanel mainMenuPanel;
	private JPanel magicMenuPanel;
	
	private Player player;
	private List<Enemy> enemies;
	private int enemyIndex = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textadventuresgui frame = new textadventuresgui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public textadventuresgui() {
		setTitle("Text Adventures RPG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel healthPanel = new JPanel();
		healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.Y_AXIS));
		healthPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(healthPanel, BorderLayout.NORTH);

		playerHealthLabel = new JLabel("Your Health: 100");
		healthPanel.add(playerHealthLabel);

		enemyHealthLabel = new JLabel("Enemy's Health: 100");
		healthPanel.add(enemyHealthLabel);

		gameTextArea = new JTextArea();
		gameTextArea.setEditable(false);
		contentPane.add(gameTextArea, BorderLayout.CENTER);
		
		// Setup for the menu system using CardLayout
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		contentPane.add(cardPanel, BorderLayout.SOUTH);
		
		setupMainMenu();
		setupMagicMenu();
		
		initializeGame();
	}
	
	private void setupMainMenu() {
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton attackButton = new JButton("Attack");
		JButton magicButton = new JButton("Magic");
		JButton healButton = new JButton("Heal");

		mainMenuPanel.add(attackButton);
		mainMenuPanel.add(magicButton);
		mainMenuPanel.add(healButton);
		
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("attack");
			}
		});
		
		magicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "MagicMenu");
			}
		});
		
		healButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("heal");
			}
		});
		
		cardPanel.add(mainMenuPanel, "MainMenu");
	}
	
	private void setupMagicMenu() {
		magicMenuPanel = new JPanel();
		magicMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton fireButton = new JButton("Fire");
		JButton iceButton = new JButton("Ice");
		JButton lightningButton = new JButton("Lightning");
		JButton lightButton = new JButton("Light");
		JButton darknessButton = new JButton("Darkness");
		JButton backButton = new JButton("Back");
		
		magicMenuPanel.add(fireButton);
		magicMenuPanel.add(iceButton);
		magicMenuPanel.add(lightningButton);
		magicMenuPanel.add(lightButton);
		magicMenuPanel.add(darknessButton);
		magicMenuPanel.add(backButton);
		
		fireButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("fire");
			}
		});
		
		iceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("ice");
			}
		});
		
		lightningButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("lightning");
			}
		});
		
		lightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("light");
			}
		});
		
		darknessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePlayerAction("darkness");
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "MainMenu");
			}
		});
		
		cardPanel.add(magicMenuPanel, "MagicMenu");
	}
	
	private void initializeGame() {
		player = new Player();
		enemies = new ArrayList<>();
// Name, HP, Atk Base, Atk Bonus, Defense, Fire, Ice, Lightning, Light, Darkness Resistance
        enemies.add(new Enemy("Fire Elemental", 100, 8, 5, 2, 10, -25, 0, 0, 5));
        enemies.add(new Enemy("Ice Golem", 120, 10, 6, 5, -75, 10, 5, 0, 0));
        enemies.add(new Enemy("Thunder Dragon", 200, 15, 10, 5, -50, 5, 15, 5, 5));
        enemies.add(new Enemy("Shadow Beast", 80, 5, 15, 999, 0, 0, 0, -420, 10));

		Collections.shuffle(enemies);
		
		gameTextArea.append("You awaken in a dark forest. You sense danger ahead.\n");
		startNextBattle();
	}
	
	private void startNextBattle() {
		gameTextArea.setText("");
		
		if (enemyIndex < enemies.size()) {
			Enemy currentEnemy = enemies.get(enemyIndex);
			gameTextArea.append("-----------------------------------\n");
			gameTextArea.append("A wild " + currentEnemy.getName() + " appears!\n");
			gameTextArea.append("Its health is " + currentEnemy.getHealth() + ".\n");
			updateHealthLabels();
			// Switch back to the main menu at the start of each battle
			cardLayout.show(cardPanel, "MainMenu"); 
		} else {
			gameTextArea.append("-----------------------------------\n");
			gameTextArea.append("You have defeated all enemies! You are the champion! 🎉\n");
			disableButtons();
		}
	}
	
	private void handlePlayerAction(String action) {
		Enemy currentEnemy = enemies.get(enemyIndex);
		int damage = 0;
		String damageType = "";
		
		gameTextArea.append("-----------------------------------\n");
		
		if (action.equalsIgnoreCase("attack")) {
			damage = Math.max(0, player.attack() - currentEnemy.getDefense());
			damageType = "physical";
			gameTextArea.append("You strike the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("fire")) {
			damage = Math.max(0, player.castFireball() - currentEnemy.getFireResistance());
			damageType = "fire";
			gameTextArea.append("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("ice")) {
			damage = Math.max(0, player.castIceShard() - currentEnemy.getIceResistance());
			damageType = "ice";
			gameTextArea.append("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("lightning")) {
			damage = Math.max(0, player.castLightningBolt() - currentEnemy.getLightningResistance());
			damageType = "lightning";
			gameTextArea.append("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("light")) {
			damage = Math.max(0, player.castLightBeam() - currentEnemy.getLightResistance());
			damageType = "light";
			gameTextArea.append("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("darkness")) {
			damage = Math.max(0, player.castDarknessBlast() - currentEnemy.getDarknessResistance());
			damageType = "darkness";
			gameTextArea.append("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!\n");
		} else if (action.equalsIgnoreCase("heal")) {
			gameTextArea.append(player.heal());
			damage = -1;
		}
		
		if (damage >= 0) {
			currentEnemy.takeDamage(damage);
		}
		
		if (currentEnemy.getHealth() <= 0) {
			gameTextArea.append("You have defeated the " + currentEnemy.getName() + "!\n");
			enemyIndex++;
			startNextBattle();
			return;
		}

		int enemyDamage = Math.max(0, currentEnemy.attack() - player.getDefense());
		player.takeDamage(enemyDamage);
		gameTextArea.append("The " + currentEnemy.getName() + " strikes you for " + enemyDamage + " damage!\n");
		
		updateHealthLabels();

		if (player.getHealth() <= 0) {
			gameTextArea.append("-----------------------------------\n");
			gameTextArea.append("You have been defeated! Game Over.\n");
			disableButtons();
		}
		// Always go back to the main menu after an action
		cardLayout.show(cardPanel, "MainMenu"); 
	}
	
	private void updateHealthLabels() {
		Enemy currentEnemy = enemies.get(enemyIndex);
		playerHealthLabel.setText("Your Health: " + player.getHealth());
		enemyHealthLabel.setText(currentEnemy.getName() + "'s Health: " + currentEnemy.getHealth());
	}
	
	private void disableButtons() {
		for (java.awt.Component component : mainMenuPanel.getComponents()) {
			if (component instanceof JButton) {
				((JButton) component).setEnabled(false);
			}
		}
		for (java.awt.Component component : magicMenuPanel.getComponents()) {
			if (component instanceof JButton) {
				((JButton) component).setEnabled(false);
			}
		}
	}
}
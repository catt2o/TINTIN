import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List;      
import java.util.Scanner;
import java.util.Collections; 

public class MysteriousCave {

   
   static int playerHealth = 100;
   static int playerMaxHealth = 100; 
   static int playerAttack = 10;
   static int playerDefense = 0;    
   static List<String> playerInventory = new ArrayList<>(); 
   static boolean hasUsedAltar = false; 
   static List<String> puzzleSequence = Arrays.asList("Sun", "Moon", "Star"); 
   static List<String> playerPuzzleAttempt = new ArrayList<>();
   static boolean puzzleSolved = false;


   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      boolean gameOver = false;
      String playerLocation = "caveEntrance"; 
   
      System.out.println("You awaken in a dark, damp cave. A faint light flickers ahead.");
      System.out.println("Tip: In most situations, you can type '99' to quit the game (or the specified quit number).");
   
      
      while (!gameOver) {
         System.out.println("\n--- Player Status ---");
         System.out.println("Health: " + playerHealth + "/" + playerMaxHealth);
         System.out.println("Attack: " + playerAttack);
         System.out.println("Defense: " + playerDefense);
         System.out.println("Inventory: " + (playerInventory.isEmpty() ? "Empty" : String.join(", ", playerInventory)));
         System.out.println("---------------------");
      
         int choice; 
      
         switch (playerLocation) {
            case "caveEntrance":
               System.out.println("\nYou are at the cave entrance. It's dark and cold.");
               System.out.println("Do you:");
               System.out.println("[1] Go towards the flickering light ahead?");
               System.out.println("[2] Explore the dark passage to your left?");
               System.out.println("[3] Examine the entrance area carefully?");
               System.out.println("--- OR ---");
               System.out.println("If you know a special word, you may type it instead of a number.");
               System.out.print("Enter your choice (1-3, or 99 to Quit) or the special word: ");
            
               String rawInputAtEntrance = scanner.nextLine().trim(); 
            
               if (rawInputAtEntrance.equalsIgnoreCase("gela")) {
                  System.out.println("\n====================================================================");
                  System.out.println("My Dearest Gela,");
                  System.out.println();
                  System.out.println("Words can't truly express the depth of my feelings for you, but I'll try.");
                  System.out.println("You are one of the light that seeps through my being, the warmth in my coldest nights.");
                  System.out.println("Knowing you were willing to download jGRASP, to play this game and step into this world I built,");
                  System.out.println("even for a little while, means more to me than any treasure I could code into this game.");
                  System.out.println("Thank you for your patience, your support, and for being you.");
                  System.out.println("This silly made game i made as you were doing your plates last night is just a small token, but my love for you is the one who's grand.");
                  System.out.println();
                  System.out.println("With all my heart and my being");
                  System.out.println("Your nerd lover,");
                  System.out.println("Tintin."); 
                  System.out.println("====================================================================");
                  System.out.println("\nThank you for reading this hehe. The game will now conclude.");
                  gameOver = true;
               
               } else {
                  try {
                     choice = Integer.parseInt(rawInputAtEntrance);
                     if (choice == 1) {
                        playerLocation = "flickeringLight";
                     } else if (choice == 2) {
                        playerLocation = "darkPassage";
                     } else if (choice == 3) {
                        System.out.println("You scan the entrance. Tucked into a crevice, you find an Old Scroll!");
                        if (!playerInventory.contains("Old Scroll")) {
                           playerInventory.add("Old Scroll");
                           System.out.println("Picked up an Old Scroll.");
                        } else {
                           System.out.println("You've already found the scroll here.");
                        }
                     } else if (choice == 99) { 
                        System.out.println("You decide to leave the cave for now.");
                        gameOver = true;
                     } else {
                        System.out.println("Invalid numerical choice. Please select from the options listed or type the special word.");
                        
                     }
                  } catch (NumberFormatException e) {
                     System.out.println("Invalid input. Please enter a number (1, 2, 3, 99) or the special word.");
                     
                  }
               }
               break;
         
            case "flickeringLight":
               System.out.println("\nYou reach the light. It's a small campfire, long abandoned. There's a rusty old key and a small, glowing potion near it.");
               System.out.println("Do you:");
               System.out.println("[1] Take the Rusty Key?");
               System.out.println("[2] Take the Healing Potion?");
               System.out.println("[3] Take both?");
               System.out.println("[4] Leave everything and return to the crossroads planning spot?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 4, 99); 
               if (choice == 1) {
                  if (!playerInventory.contains("Rusty Key")) {
                     playerInventory.add("Rusty Key");
                     System.out.println("You picked up the Rusty Key.");
                  } else {
                     System.out.println("You already have a Rusty Key.");
                  }
               } else if (choice == 2) {
                  if (!playerInventory.contains("Healing Potion (Campfire)")) {
                     playerInventory.add("Healing Potion (Campfire)");
                     System.out.println("You picked up a Healing Potion.");
                  } else {
                     System.out.println("You already took the potion from here.");
                  }
               } else if (choice == 3) {
                  if (!playerInventory.contains("Rusty Key")) {
                     playerInventory.add("Rusty Key");
                     System.out.println("You picked up the Rusty Key.");
                  } else {
                     System.out.println("You already have a Rusty Key.");
                  }
                  if (!playerInventory.contains("Healing Potion (Campfire)")) {
                     playerInventory.add("Healing Potion (Campfire)");
                     System.out.println("You picked up a Healing Potion.");
                  } else {
                     System.out.println("You already took the potion from here.");
                  }
               } else if (choice == 4) {
                  
               } else if (choice == 99) {
                  gameOver = true;
                  break; 
               }
               if (!gameOver) playerLocation = "crossroads"; 
               break;
         
            case "darkPassage":
               System.out.println("\nThe dark passage is cold and narrow. You hear a low growl.");
               System.out.println("Do you:");
               System.out.println("[1] Bravely proceed?");
               System.out.println("[2] Quickly turn back to the cave entrance?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 2, 99);
               if (choice == 1) {
                  
                  if (encounterEnemy(scanner, "Goblin", 30, 5)) {
                     playerLocation = "goblinLair";
                  } else {
                     gameOver = true; 
                  }
               } else if (choice == 2) {
                  playerLocation = "caveEntrance";
               } else if (choice == 99) {
                  gameOver = true;
               }
               break;
         
            case "goblinLair":
               System.out.println("\nYou defeated the goblin! Beyond its lair, you find a crude wooden shield and a sharp stone dagger.");
               System.out.println("Do you:");
               System.out.println("[1] Take the Wooden Shield?");
               System.out.println("[2] Take the Stone Dagger?");
               System.out.println("[3] Take both?");
               System.out.println("[4] Leave them and proceed to the crossroads?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 4, 99);
               if (choice == 1) {
                  if (!playerInventory.contains("Wooden Shield")) {
                     playerInventory.add("Wooden Shield");
                     playerDefense += 3; 
                     System.out.println("You picked up the Wooden Shield. Your defense increased by 3!");
                  } else {
                     System.out.println("You already have a Wooden Shield.");
                  }
               } else if (choice == 2) {
                  if (!playerInventory.contains("Stone Dagger")) {
                     playerInventory.add("Stone Dagger");
                     playerAttack += 5; 
                     System.out.println("You picked up the Stone Dagger. Your attack increased by 5!");
                  } else {
                     System.out.println("You already have a Stone Dagger.");
                  }
               } else if (choice == 3) {
                  if (!playerInventory.contains("Wooden Shield")) {
                     playerInventory.add("Wooden Shield");
                     playerDefense += 3;
                     System.out.println("You picked up the Wooden Shield. Your defense increased by 3!");
                  } else {
                     System.out.println("You already have a Wooden Shield.");
                  }
                  if (!playerInventory.contains("Stone Dagger")) {
                     playerInventory.add("Stone Dagger");
                     playerAttack += 5;
                     System.out.println("You picked up the Stone Dagger. Your attack increased by 5!");
                  } else {
                     System.out.println("You already have a Stone Dagger.");
                  }
               } else if (choice == 4) {
               } else if (choice == 99) {
                  gameOver = true;
                  break; 
               }
               if (!gameOver) playerLocation = "crossroads"; 
               break;
         
            case "crossroads":
               System.out.println("\nYou are at a crossroads. Paths lead in multiple directions.");
               System.out.println("[1] Path towards the flickering light (abandoned campfire).");
               System.out.println("[2] Path back to the cave entrance.");
               System.out.println("[3] Path deeper into the cave (towards the Deep Cave).");
               System.out.println("[4] Path towards an Underground Lake.");
               System.out.println("[5] Path towards a narrow Chasm.");
               System.out.println("[6] Path leading to Echoing Caverns.");
               if (playerInventory.contains("Rusty Key")) {
                  System.out.println("[7] Use Rusty Key on a barely visible, locked hidden passage.");
               }
               System.out.println("[99] Quit Game");
               int maxChoiceCrossroads = playerInventory.contains("Rusty Key") ? 7 : 6;
               choice = getUserChoice(scanner, 1, maxChoiceCrossroads, 99);
            
               if (choice == 1) playerLocation = "flickeringLight";
               else if (choice == 2) playerLocation = "caveEntrance";
               else if (choice == 3) playerLocation = "deepCave";
               else if (choice == 4) playerLocation = "undergroundLake";
               else if (choice == 5) playerLocation = "chasm";
               else if (choice == 6) playerLocation = "echoingCaverns";
               else if (choice == 7 && playerInventory.contains("Rusty Key")) {
                  System.out.println("The Rusty Key fits perfectly! The hidden passage creaks open.");
                  playerLocation = "hiddenChamber";
               } else if (choice == 99) gameOver = true;
               break;
         
            case "hiddenChamber":
               System.out.println("\nDeep within the hidden chamber, you find a large, ornate chest!");
               boolean chestLooted = playerInventory.contains("Glowing Sword") || playerInventory.contains("Super Healing Potion (Chest)");
               
               if (playerInventory.contains("Rusty Key")) { 
                  System.out.println("You use the Rusty Key to open the chest. Inside, you find a Glowing Sword and a Super Healing Potion!");
                  playerInventory.remove("Rusty Key"); 
                  System.out.println("The Rusty Key crumbles to dust after use.");
                  chestLooted = true; 
               
                  System.out.println("Do you [1] take the Glowing Sword, [2] take the Super Healing Potion, [3] take both, or [4] leave them?");
                  choice = getUserChoice(scanner, 1, 4); 
                  if (choice == 1) {
                     if (!playerInventory.contains("Glowing Sword")) {
                        playerInventory.add("Glowing Sword");
                        playerAttack += 15;
                        System.out.println("You picked up the Glowing Sword. Your attack increased by 15!");
                     } else { System.out.println("You already have this.");}
                  } else if (choice == 2) {
                     if (!playerInventory.contains("Super Healing Potion (Chest)")) {
                        playerInventory.add("Super Healing Potion (Chest)");
                        System.out.println("You picked up a Super Healing Potion.");
                     } else { System.out.println("You already have this.");}
                  } else if (choice == 3) {
                     if (!playerInventory.contains("Glowing Sword")) {
                        playerInventory.add("Glowing Sword");
                        playerAttack += 15;
                        System.out.println("You picked up the Glowing Sword. Your attack increased by 15!");
                     } else { System.out.println("You already have the sword.");}
                     if (!playerInventory.contains("Super Healing Potion (Chest)")) {
                        playerInventory.add("Super Healing Potion (Chest)");
                        System.out.println("You picked up a Super Healing Potion.");
                     } else { System.out.println("You already have the potion.");}
                  }
               } else if (chestLooted) {
                  System.out.println("The chest has already been opened.");
               } else {
                  System.out.println("The chest is sealed, and you don't have the Rusty Key.");
               }
               System.out.println("\nWhat to do next?");
               System.out.println("[1] Return to the crossroads.");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 1, 99);
               if (choice == 1) playerLocation = "crossroads";
               else if (choice == 99) gameOver = true;
               break;
         
            case "deepCave":
               System.out.println("\nYou venture deeper into the cave. It's eerily quiet. Suddenly, a Giant Spider drops from the ceiling!");
               if (encounterEnemy(scanner, "Giant Spider", 60, 12)) { 
                  System.out.println("You defeated the Giant Spider! You find a Shiny Breastplate on its web-covered nest and a Crystal Shard.");
                  System.out.println("Do you [1] take the Shiny Breastplate, [2] take the Crystal Shard, [3] take both, or [4] leave and go to the Forgotten Altar?");
                  System.out.println("[99] Quit Game (from this decision)");
                  choice = getUserChoice(scanner, 1, 4, 99); 
                  if (choice == 1) {
                     if (!playerInventory.contains("Shiny Breastplate")) {
                        playerInventory.add("Shiny Breastplate");
                        playerDefense += 7; 
                        System.out.println("You picked up the Shiny Breastplate. Your defense increased by 7!");
                     } else { System.out.println("You already have this."); }
                  } else if (choice == 2) {
                     if (!playerInventory.contains("Crystal Shard")) {
                        playerInventory.add("Crystal Shard");
                        System.out.println("You picked up a Crystal Shard. It hums with energy.");
                     } else { System.out.println("You already have this."); }
                  } else if (choice == 3) {
                     if (!playerInventory.contains("Shiny Breastplate")) {
                        playerInventory.add("Shiny Breastplate");
                        playerDefense += 7;
                        System.out.println("You picked up the Shiny Breastplate. Your defense increased by 7!");
                     } else { System.out.println("You already have the breastplate."); }
                     if (!playerInventory.contains("Crystal Shard")) {
                        playerInventory.add("Crystal Shard");
                        System.out.println("You picked up a Crystal Shard.");
                     } else { System.out.println("You already have the shard."); }
                  } else if (choice == 99) {
                     gameOver = true;
                     break; 
                  }
                  if (choice != 4 && !gameOver) {
                     System.out.println("You decide what to do next from the spider's nest.");
                     playerLocation = "forgottenAltar";
                  } else if (choice == 4) { 
                     playerLocation = "forgottenAltar";
                  }
               } else { 
                  gameOver = true;
               }
               break;
               
            case "undergroundLake":
               System.out.println("\nYou arrive at a vast, dark Underground Lake. The water is eerily still.");
               System.out.println("A faint glint comes from the center of the lake.");
               System.out.println("[1] Try to swim towards the glint?");
               System.out.println("[2] Skirt around the edge of the lake?");
               System.out.println("[3] Return to the crossroads?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 3, 99);
               if (choice == 1) {
                  System.out.println("You dive into the cold water. As you swim, something brushes your leg!");
                  if (encounterEnemy(scanner, "Giant Leech", 40, 8)) {
                     System.out.println("You fend off the Leech and reach a small, rocky island. You find Sturdy Gauntlets!");
                     if (!playerInventory.contains("Sturdy Gauntlets")) {
                        playerInventory.add("Sturdy Gauntlets");
                        playerAttack += 3;
                        playerDefense += 1;
                        System.out.println("Picked up Sturdy Gauntlets! Attack +3, Defense +1.");
                     } else { System.out.println("You already have these gauntlets.");}
                     System.out.println("You swim back to shore.");
                  } else {
                     gameOver = true; 
                     break; 
                  }
               } else if (choice == 2) {
                  System.out.println("You carefully make your way around the lake's edge. You spot a discarded pouch.");
                  System.out.println("Inside, you find a Healing Potion!");
                  if(!playerInventory.contains("Healing Potion (Lake)")){ 
                     playerInventory.add("Healing Potion (Lake)");
                     System.out.println("Picked up a Healing Potion.");
                  } else {
                     System.out.println("You've already found the potion here.");
                  }
               } else if (choice == 3) {
                  playerLocation = "crossroads";
               } else if (choice == 99) {
                  gameOver = true;
               }
               
               if (!gameOver && choice != 3) { 
                  System.out.println("Having explored the lake area, you decide to head back to the crossroads.");
                  playerLocation = "crossroads";
               }
               break;
         
            case "chasm":
               System.out.println("\nYou stand at the edge of a wide, dark Chasm. It seems impossible to cross.");
               System.out.println("A cold wind blows up from the depths.");
               System.out.println("[1] Look for a way to cross?");
               System.out.println("[2] Return to the crossroads?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 2, 99);
               if (choice == 1) {
                  if (playerInventory.contains("Rope")) {
                     System.out.println("You have a Rope! You find a sturdy rock and manage to secure the rope, creating a makeshift bridge.");
                     System.out.println("Do you [1] Cross the chasm using the rope, or [2] Not yet?");
                     int crossChoice = getUserChoice(scanner, 1, 2); 
                     if (crossChoice == 1) {
                        System.out.println("You carefully cross the chasm.");
                        playerLocation = "stonePuzzleRoom"; 
                     }
                  } else {
                     System.out.println("You search but find no way to cross without something like a rope.");
                     System.out.println("You find a single 'Stone Tablet Piece 1' near the edge!");
                     if (!playerInventory.contains("Stone Tablet Piece 1")) {
                        playerInventory.add("Stone Tablet Piece 1");
                        System.out.println("Picked up Stone Tablet Piece 1.");
                     } else { System.out.println("You already have this piece.");}
                  }
               } else if (choice == 2) {
                  playerLocation = "crossroads";
               } else if (choice == 99) {
                  gameOver = true;
               }
               break;
         
            case "echoingCaverns":
               System.out.println("\nYou enter a series of Echoing Caverns. Your footsteps reverberate strangely.");
               System.out.println("You hear faint whispers, but can't tell where they're coming from.");
               System.out.println("[1] Follow the most prominent echo?");
               System.out.println("[2] Try to find the source of a specific whisper?");
               System.out.println("[3] Search a quiet alcove?");
               System.out.println("[4] Return to the crossroads?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 4, 99);
               if (choice == 1) {
                  System.out.println("You follow a loud echo and stumble into a Giant Bat's nest!");
                  if (encounterEnemy(scanner, "Swarm of Giant Bats", 45, 10)) {
                     System.out.println("After dealing with the bats, you find a tattered Rope!");
                     if (!playerInventory.contains("Rope")) {
                        playerInventory.add("Rope");
                        System.out.println("Picked up a Rope. This could be useful!");
                     } else { System.out.println("You already have a rope.");}
                  } else {
                     gameOver = true;
                  }
               } else if (choice == 2) {
                  System.out.println("You try to pinpoint a whisper. It leads you in circles, and you feel a bit disoriented but find nothing.");
               } else if (choice == 3) {
                  System.out.println("In a quiet alcove, you find 'Stone Tablet Piece 2'!");
                  if (!playerInventory.contains("Stone Tablet Piece 2")) {
                     playerInventory.add("Stone Tablet Piece 2");
                     System.out.println("Picked up Stone Tablet Piece 2.");
                  } else { System.out.println("You already have this piece.");}
               } else if (choice == 4) {
                  playerLocation = "crossroads";
               } else if (choice == 99) {
                  gameOver = true;
               }
               if (!gameOver && choice != 4) { 
                  System.out.println("The echoes fade as you decide to return to the crossroads.");
                  playerLocation = "crossroads";
               }
               break;
         
            case "forgottenAltar":
               System.out.println("\nYou find a Forgotten Altar, covered in strange carvings. It emanates a faint hum.");
               System.out.println("[1] Examine the carvings?");
               System.out.println("[2] Touch the altar?");
               System.out.println("[3] Leave it alone and head towards the Boss Lair (requires Ancient Key)?");
               System.out.println("[4] Return to the Deep Cave path (near spider's nest)?");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 4, 99);
               if (choice == 1) {
                  System.out.println("The carvings depict ancient rituals. One seems to show a figure gaining power.");
                  System.out.println("You also spot 'Stone Tablet Piece 3' wedged into a crack!");
                  if (!playerInventory.contains("Stone Tablet Piece 3")) {
                     playerInventory.add("Stone Tablet Piece 3");
                     System.out.println("Picked up Stone Tablet Piece 3.");
                  } else { System.out.println("You already have this piece.");}
               } else if (choice == 2) {
                  if (!hasUsedAltar) {
                     System.out.println("As you touch the altar, a surge of energy flows into you!");
                     playerMaxHealth += 20;
                     playerHealth += 20; 
                     if(playerHealth > playerMaxHealth) playerHealth = playerMaxHealth;
                     playerAttack += 2;
                     System.out.println("Your Max Health increased by 20! Attack by 2! You feel invigorated.");
                     hasUsedAltar = true;
                     if (!playerInventory.contains("Enchanted Amulet (Altar)") && !playerInventory.contains("Enchanted Amulet (Chest)")) { 
                        playerInventory.add("Enchanted Amulet (Altar)"); 
                        playerMaxHealth += 10; 
                        playerHealth +=10;
                        if(playerHealth > playerMaxHealth) playerHealth = playerMaxHealth;
                        System.out.println("An Enchanted Amulet (Altar) materializes! Max Health +10.");
                     } else if (playerInventory.contains("Enchanted Amulet (Chest)")) {
                        System.out.println("You already have a powerful amulet. The altar grants a lesser blessing of vitality.");
                     } else { 
                        System.out.println("You already received an amulet from an altar, or the altar's power is focused elsewhere.");
                     }
                  } else {
                     System.out.println("The altar is now inert. Its power has been spent.");
                  }
               } else if (choice == 3) {
                  if (playerInventory.contains("Ancient Key")) {
                     System.out.println("You feel ready to face whatever lies in the Boss Lair.");
                     playerLocation = "bossLair";
                  } else {
                     System.out.println("A massive, sealed door blocks the way to the Boss Lair. It needs a special key.");
                  }
               } else if (choice == 4) {
                  playerLocation = "deepCave"; 
               } else if (choice == 99) {
                  gameOver = true;
               }
               break;
         
            case "stonePuzzleRoom":
               System.out.println("\nYou are in a room with three pedestals. Slots on a nearby stone door seem to await tablet pieces.");
               if (!puzzleSolved) {
                  System.out.println("You have the following tablet pieces: " +
                     (playerInventory.contains("Stone Tablet Piece 1") ? "[Piece 1] " : "") +
                     (playerInventory.contains("Stone Tablet Piece 2") ? "[Piece 2] " : "") +
                     (playerInventory.contains("Stone Tablet Piece 3") ? "[Piece 3] " : ""));
               
                  if (playerInventory.contains("Stone Tablet Piece 1") &&
                     playerInventory.contains("Stone Tablet Piece 2") &&
                     playerInventory.contains("Stone Tablet Piece 3")) {
                     System.out.println("You have all three tablet pieces! Time to place them.");
                     playerPuzzleAttempt.clear();
                     List<String> availablePiecesForPuzzle = new ArrayList<>(Arrays.asList("Stone Tablet Piece 1", "Stone Tablet Piece 2", "Stone Tablet Piece 3"));
                     Collections.shuffle(availablePiecesForPuzzle); 
                  
                     for (int i = 0; i < 3; i++) {
                        System.out.println("\nPlace which piece on pedestal " + (i + 1) + "?");
                        for(int j=0; j < availablePiecesForPuzzle.size(); j++){
                           System.out.println("["+(j+1)+"] " + availablePiecesForPuzzle.get(j).replace("Stone Tablet Piece ", "Piece "));
                        }
                        if (availablePiecesForPuzzle.isEmpty()){ 
                           System.out.println("All pieces placed.");
                           break;
                        }
                        System.out.println("["+(availablePiecesForPuzzle.size()+1)+"] Stop arranging for now.");
                        int pieceChoiceInPuzzle = getUserChoice(scanner, 1, availablePiecesForPuzzle.size()+1); 
                        
                        if(pieceChoiceInPuzzle > availablePiecesForPuzzle.size()){ 
                           System.out.println("You step back from the pedestals. The pieces reset.");
                           playerPuzzleAttempt.clear(); 
                           break; 
                        }
                        String chosenPieceFull = availablePiecesForPuzzle.remove(pieceChoiceInPuzzle-1); 
                        
                        
                        if (chosenPieceFull.equals("Stone Tablet Piece 1")) playerPuzzleAttempt.add("Sun");
                        else if (chosenPieceFull.equals("Stone Tablet Piece 2")) playerPuzzleAttempt.add("Moon");
                        else if (chosenPieceFull.equals("Stone Tablet Piece 3")) playerPuzzleAttempt.add("Star");
                        System.out.println("You placed " + chosenPieceFull.replace("Stone Tablet Piece ", "Piece ") + " on pedestal " + (i+1) + " (" + playerPuzzleAttempt.get(playerPuzzleAttempt.size()-1) + ")");
                     }
                  
                     if (playerPuzzleAttempt.size() == 3 && playerPuzzleAttempt.equals(puzzleSequence)) {
                        System.out.println("The pieces glow brightly! The stone door rumbles open with a satisfying click!");
                        System.out.println("Behind it, you find the Ancient Key!");
                        if(!playerInventory.contains("Ancient Key")){
                           playerInventory.add("Ancient Key");
                           System.out.println("Picked up the Ancient Key.");
                        }
                        puzzleSolved = true;
                     } else if (playerPuzzleAttempt.size() == 3) { 
                        System.out.println("A dull thud. Nothing happens. The sequence must be wrong. The pieces on the pedestals reset.");
                        playerPuzzleAttempt.clear();
                     }
                  } else {
                     System.out.println("You don't have all the tablet pieces yet. Keep exploring!");
                  }
               } else { 
                  System.out.println("The puzzle is solved, and the stone door to the ancient chamber remains open.");
               }
               System.out.println("\nWhat to do?");
               System.out.println("[1] Go through the stone door (if open).");
               System.out.println("[2] Go back across the chasm (requires Rope).");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 2, 99);
               if (choice == 1) {
                  if (puzzleSolved) {
                     System.out.println("You step through the doorway into an ancient, dusty chamber.");
                     playerLocation = "ancientChamberTreasure"; 
                  } else {
                     System.out.println("The stone door is sealed shut. The puzzle awaits.");
                  }
               } else if (choice == 2) {
                  if (playerInventory.contains("Rope")){ 
                     System.out.println("You carefully make your way back across the chasm using the rope.");
                     playerLocation = "chasm";
                  } else {
                     System.out.println("You can't cross the chasm back without a rope from this side either!");
                      
                  }
               } else if (choice == 99) {
                  gameOver = true;
               }
               break;
         
            case "ancientChamberTreasure":
               System.out.println("\nThis ancient chamber holds a single, glowing chest. You also see an exit tunnel leading further into the unknown.");
               if (!playerInventory.contains("Enchanted Amulet (Chest)")) { 
                  System.out.println("You open the chest. Inside, you find a powerful Enchanted Amulet (Chest)!");
                  playerInventory.add("Enchanted Amulet (Chest)");
                  playerMaxHealth += 25; 
                  playerHealth += 25;
                  if(playerHealth > playerMaxHealth) playerHealth = playerMaxHealth;
                  System.out.println("Picked up an Enchanted Amulet (Chest)! Max Health increased by 25.");
               } else {
                  System.out.println("The chest in this chamber is now empty.");
               }
               System.out.println("[1] Go to the Exit Tunnel (leads to Boss Lair access path).");
               System.out.println("[2] Return to the Puzzle Room.");
               System.out.println("[99] Quit Game");
               choice = getUserChoice(scanner, 1, 2, 99);
               if (choice == 1) {
                  System.out.println("The tunnel from the ancient chamber leads you back towards the area of the Forgotten Altar, where the Boss Lair might be accessed.");
                  playerLocation = "forgottenAltar"; 
               }
               else if (choice == 2) playerLocation = "stonePuzzleRoom";
               else if (choice == 99) gameOver = true;
               break;
         
            case "bossLair":
               System.out.println("\nYou enter a colossal cavern. In the center, a terrifying Cave Hydra stirs, its multiple heads snapping towards you!");
               System.out.println("This is it. The ultimate challenge of the Mysterious Cave!");
               if (encounterEnemy(scanner, "Cave Hydra", 150, 18)) { 
                  System.out.println("With a final, earth-shattering roar, the Cave Hydra collapses!");
                  System.out.println("You have conquered the beast and cleared the Mysterious Cave!");
                  System.out.println("Behind its nest, you see the true exit, bathed in sunlight.");
                  playerLocation = "exitTunnel"; 
               } else {
                  gameOver = true; 
               }
               break;
          
         
            case "exitTunnel":
               System.out.println("\nAfter traversing a long, winding tunnel, you see brilliant light at the end!");
               System.out.println("You step out of the cave, blinking in the sunlight. You've escaped!");
               System.out.println("CONGRATULATIONS, YOU HAVE BRAVED THE MYSTERIOUS CAVE AND SURVIVED!");
               gameOver = true;
               break;
         
            default:
               System.out.println("Error: Unknown location '" + playerLocation + "'. Returning to cave entrance.");
               playerLocation = "caveEntrance";
               break;
         }
      }
      System.out.println("\n--- GAME OVER ---");
      System.out.println("Thanks for playing 'The Mysterious Cave' (Extended Edition)!");
      scanner.close();
   }

   public static int getUserChoice(Scanner scanner, int min, int max, int quitOption) {
      int choice = -1;
      while (true) {
         System.out.print("Enter your choice: ");
         String line = scanner.nextLine().trim(); 
         try {
            choice = Integer.parseInt(line); 
            if ((choice >= min && choice <= max) || (quitOption != -1 && choice == quitOption) ) { 
               break; 
            } else {
               if (quitOption != -1) {
                  System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ", or " + quitOption + " to quit.");
               } else {
                  System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
               }
            }
         } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numerical value.");
         }
      }
      return choice;
   }

   public static int getUserChoice(Scanner scanner, int min, int max) {
      return getUserChoice(scanner, min, max, -1); 
   }


   public static boolean encounterEnemy(Scanner scanner, String enemyName, int enemyHealth, int enemyAttackPower) {
      System.out.println("\n--- COMBAT INITIATED! ---");
      System.out.println("A " + enemyName + " appears! (Health: " + enemyHealth + ", Attack: " + enemyAttackPower + ")");
      int originalEnemyHealth = enemyHealth;
   
      while (playerHealth > 0 && enemyHealth > 0) {
         System.out.println("\nYour Health: " + playerHealth + "/" + playerMaxHealth + " | " + enemyName + " Health: " + enemyHealth);
         System.out.println("Combat Options:");
         System.out.println("[1] Attack with your weapon");
         System.out.println("[2] Use a Healing Item");
         int nextOption = 3;
         if (playerInventory.contains("Crystal Shard")) {
            System.out.println("[" + nextOption + "] Use Crystal Shard (deals 25% of enemy's original max health, one-time use per shard)");
            nextOption++;
         }
         System.out.println("[" + nextOption + "] Try to Flee");
        
      
         int combatChoiceMax = nextOption;
         int choice = getUserChoice(scanner, 1, combatChoiceMax); 
      
         int fleeChoiceValue = playerInventory.contains("Crystal Shard") ? 4 : 3;
         int crystalShardChoiceValue = playerInventory.contains("Crystal Shard") ? 3 : -1; 
      
         if (choice == 1) { 
            int damageDealt = playerAttack + (int) (Math.random() * 5); 
            enemyHealth -= damageDealt;
            System.out.println("You strike the " + enemyName + " for " + damageDealt + " damage!");
         } else if (choice == 2) { 
            if (!useHealingItemInCombat(scanner)) { 
               System.out.println("You fumbled and couldn't use an item! The " + enemyName + " takes advantage!");
               
            }
         } else if (choice == crystalShardChoiceValue && crystalShardChoiceValue != -1) { 
            int shardDamage = originalEnemyHealth / 4; 
            enemyHealth -= shardDamage;
            playerInventory.remove("Crystal Shard"); 
            System.out.println("You hurl the Crystal Shard! It explodes, dealing " + shardDamage + " damage to the " + enemyName + "!");
            if (enemyHealth <= 0) {
               System.out.println("The Crystal Shard finished off the " + enemyName + "!");
            }
         }
         else if (choice == fleeChoiceValue ) { 
            System.out.println("You attempt to flee!");
            if (Math.random() > 0.5) { 
               System.out.println("You successfully escaped from the " + enemyName + "!");
               return true; 
            } else {
               System.out.println("You failed to escape!");
               
            }
         }
      
         
         if (enemyHealth > 0 && playerHealth > 0) { 
            int damageTaken = enemyAttackPower + (int) (Math.random() * 3) - playerDefense;
            damageTaken = Math.max(0, damageTaken); 
            playerHealth -= damageTaken;
            System.out.println("The " + enemyName + " attacks you for " + damageTaken + " damage!");
         }
      
         if (playerHealth <= 0) {
            System.out.println("You were defeated by the " + enemyName + "!");
            return false; 
         }
         if (enemyHealth <= 0 && playerHealth > 0) { 
            System.out.println("You defeated the " + enemyName + "!");
            return true; 
         }
      }
      return playerHealth > 0; 
   }

   public static boolean useHealingItemInCombat(Scanner scanner) {
      List<String> healingItems = new ArrayList<>();
      for (String item : playerInventory) {
         if (item.toLowerCase().contains("potion")) {
            healingItems.add(item);
         }
      }
   
      if (healingItems.isEmpty()) {
         System.out.println("You have no healing items.");
         return false;
      }
   
      System.out.println("Choose a healing item to use:");
      for (int i = 0; i < healingItems.size(); i++) {
         System.out.println("[" + (i + 1) + "] " + healingItems.get(i));
      }
      System.out.println("[" + (healingItems.size() + 1) + "] Cancel");
   
      int choice = getUserChoice(scanner, 1, healingItems.size() + 1); 
      if (choice > healingItems.size()) { 
         System.out.println("Cancelled item usage.");
         return false;
      }
   
      String itemToUse = healingItems.get(choice - 1);
      int healAmount = 0;
      if (itemToUse.contains("Super Healing Potion")) {
         healAmount = 50;
      } else if (itemToUse.contains("Healing Potion")) { 
         healAmount = 25; 
      }
   
      playerHealth += healAmount;
      if (playerHealth > playerMaxHealth) {
         playerHealth = playerMaxHealth;
      }
      System.out.println("You used " + itemToUse + ". Your health is now " + playerHealth + "/" + playerMaxHealth + ".");
      playerInventory.remove(itemToUse); 
      return true;
   }


   public static boolean useGeneralItem(Scanner scanner) {
      if (playerInventory.isEmpty()) {
         System.out.println("Your inventory is empty.");
         return false;
      }
   
      System.out.println("Your Inventory (for general use/examination):");
      for (int i = 0; i < playerInventory.size(); i++) {
         System.out.println("[" + (i + 1) + "] " + playerInventory.get(i));
      }
      System.out.println("Enter the number of the item you want to use/examine (or 0 to cancel):");
      int itemChoice = getUserChoice(scanner, 0, playerInventory.size()); 
   
      if (itemChoice == 0) {
         System.out.println("Cancelled item usage.");
         return false;
      }
   
      String itemToUse = playerInventory.get(itemChoice - 1);
      switch (itemToUse) {
         case "Healing Potion (Campfire)": 
         case "Healing Potion (Lake)":
         case "Super Healing Potion (Chest)":
            System.out.println("You should generally use potions during combat or when health is low.");
            System.out.println("Do you want to use it now anyway? [1] Yes [2] No");
            int confirmUse = getUserChoice(scanner, 1,2);
            if(confirmUse == 1){
               int healAmount = 0;
               if (itemToUse.contains("Super")) healAmount = 50; 
               else healAmount = 25;
               playerHealth += healAmount;
               if (playerHealth > playerMaxHealth) playerHealth = playerMaxHealth;
               System.out.println("You used " + itemToUse + ". Health: " + playerHealth + "/" + playerMaxHealth);
               playerInventory.remove(itemChoice - 1); 
               return true;
            }
            return false;
         case "Old Scroll":
            System.out.println("You unroll the Old Scroll. It reads: 'Where shadows deepen and stone sleeps, the path to power and ruin weeps. Three symbols mark the way, Sun, Moon, then Star, lest you forever stray.'");
            return true; 
         case "Rusty Key":
         case "Ancient Key":
            System.out.println(itemToUse + ": This key looks important. It might open a specific lock.");
            return false; 
         case "Wooden Shield":
         case "Stone Dagger":
         case "Glowing Sword":
         case "Shiny Breastplate":
         case "Sturdy Gauntlets":
         case "Enchanted Amulet": 
         case "Enchanted Amulet (Altar)":
         case "Enchanted Amulet (Chest)":
            System.out.println(itemToUse + ": This is equipment. Its effects are passive once acquired.");
            return false;
         case "Rope":
            System.out.println("A sturdy Rope. Might be useful for crossing gaps.");
            return false;
         case "Crystal Shard":
            System.out.println("A pulsating Crystal Shard. It feels like it could be thrown with force in combat.");
            return false;
         case "Stone Tablet Piece 1":
         case "Stone Tablet Piece 2":
         case "Stone Tablet Piece 3":
            System.out.println(itemToUse + ": A fragment of an ancient tablet. It seems to be part of a set.");
            return false;
         default:
            System.out.println("You can't use '" + itemToUse + "' in this way right now, or it has no special general use.");
            return false;
      }
   }
}

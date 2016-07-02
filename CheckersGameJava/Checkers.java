import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math;

public class Checkers extends JFrame implements ActionListener
//class to create a checkerboard with buttons
{
   int count = 0;
   int i, j;
   boolean winP1, winP2;
   public JButton[][] b;
   private JPanel grid;
   private String player;
   private boolean move = true;
   ///public boolean isPossible;
   private ArrayList<Integer> mylist = new ArrayList<Integer>(4);
   private Icon returned1, returned2;
   private JTextField number = new JTextField(24);
   private Icon buttonIcon1, buttonIcon2;
   public Checkers()
   {	
   // first 3 lines are the usual way to start the JFrame (window) construction:
   	setTitle("Checkers");
   	setSize(1280, 800);
   	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	
   	grid = new JPanel();
   	setLayout(new BorderLayout() );
   	add(grid, BorderLayout.CENTER);
   	
   	grid.setLayout( new GridLayout(8,8) ); //each button is 158x95
   	b = new JButton[8][8]; // there are 64 blocks on the checkerboard
      boolean black = true;
   	for (i = 0; i < 8; i++)
      {
         for(j = 0; j < 8; j++)
         {
            b[i][j] = new JButton(); 
            if(black) 
   		  	{
               if(i != 3 && i != 4 && i < 5 )
               {
                  buttonIcon1 = new ImageIcon("pic1.jpeg");
                  b[i][j] = new JButton(buttonIcon1);
                  b[i][j].setBackground(Color.BLACK);
                  grid.add(b[i][j]);
                  black = false;
   		      }
               else if(i != 3 && i != 4 && i >= 5 )
               {
                  buttonIcon2 = new ImageIcon("pic2.jpeg");
                  b[i][j] = new JButton(buttonIcon2);
                  b[i][j].setBackground(Color.BLACK);
                  grid.add(b[i][j]);
                  black = false;
               }
               else
               {
                  b[i][j].setBackground(Color.BLACK);
                  grid.add(b[i][j]);
                  black = false;
               }
   		   }
            else
            {
               b[i][j].setBackground(Color.RED);
               grid.add(b[i][j]);
               black = true;
            }
         b[i][j].setText(Integer.toString(i) + Integer.toString(j));
         b[i][j].addActionListener(this);
         }
         black = !(black);
      }
      player = "player 1: move";
      System.out.println(player);
   }
   public void actionPerformed(ActionEvent e)
   ///This is the jist of this game
   ///***comments
   ///***comments
   ///***comments
   ///***comments
   {
      Icon iconOne, iconTwo;
      String pick = e.getActionCommand();
      for(int p = 0; p < pick.length(); p++)
      {
         mylist.add(count, Integer.valueOf(String.valueOf(pick.charAt(p))));
         count += 1;
      }
      if(count == 4)
      {
         returned1 = getIcon(b[mylist.get(0)][mylist.get(1)]);
         returned2 = getIcon(b[mylist.get(2)][mylist.get(3)]);
         if(!(String.valueOf(returned1).equals("pic3.jpeg")) && !(String.valueOf(returned1).equals("pic4.jpeg")))
         {
         if((mylist.get(2) != 7 && String.valueOf(returned1).equals("pic1.jpeg")) || (mylist.get(2) != 0 && String.valueOf(returned1).equals("pic2.jpeg")))
         {
            if(ValidateMove(mylist.get(0), mylist.get(1), mylist.get(2), mylist.get(3)) && (returned1 != null && returned2 == null))
            {
               if(String.valueOf(returned1).equals("pic1.jpeg") && mylist.get(2) - mylist.get(0) == 1 && player.equals("player 2: move"))
               {
                  if(mylist.get(0) < mylist.get(2))
                  {
                     normalMove(returned1);
                     move = true;
                  }
                  else
                  {
                     noMove();
                     move = false;
                  }
               }
               else if(String.valueOf(returned1).equals("pic1.jpeg") && mylist.get(2) - mylist.get(0) == 2 && player.equals("player 2: move"))
               {
                  if(rightIsBigger(mylist.get(1), mylist.get(3)))
                  {
                     if(String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg"))
                     {
                        rGreaterMove1(returned1);
                        move = true;
                     }
                     else
                     {
                        noMove();
                        move = false;
                     }
                  }
                  else if(!(rightIsBigger(mylist.get(1), mylist.get(3)))) 
                  {
                     if(String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic4.jpeg"))
                     {
                        lGreaterMove1(returned1);
                        move = true;
                     }
                     else
                     {
                        noMove();
                        move = false;
                     } 
                  } 
               }
               else if(String.valueOf(returned1).equals("pic2.jpeg") && mylist.get(0) - mylist.get(2) == 1 && player.equals("player 1: move"))
               {
                  if(mylist.get(0) > mylist.get(2))
                  {
                     normalMove(returned1);
                     move = true;
                  }
                  else
                  {
                     noMove(); 
                     move = false;
                  }
               }
               else if(String.valueOf(returned1).equals("pic2.jpeg") && mylist.get(0) - mylist.get(2) == 2 && player.equals("player 1: move"))
               {
                  if(!(rightIsBigger(mylist.get(1), mylist.get(3))))
                  {
                     if(String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))-1])).equals("pic3.jpeg"))
                     {
                        lGreaterMove2(returned1);
                        move = true;
                     }
                     else
                     {
                        noMove();
                        move = false;
                     }
                  }
                  else if(rightIsBigger(mylist.get(1), mylist.get(3)))
                  {
                     if(String.valueOf(getIcon(b[mylist.get(0)-1][mylist.get(1)+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[mylist.get(0)-1][mylist.get(1)+1])).equals("pic3.jpeg"))
                     {
                        rGreaterMove2(returned1);
                        move = true;
                     }
                     else
                     {
                        noMove();
                        move = false;
                     }
                  }
               }
               else
               {
                  noMove();
                  move = false;  
               }
            }
            else
            {
               noMove();  
               move = false;
            }
         }
         else if(String.valueOf(returned1).equals("pic1.jpeg") && mylist.get(2) == 7 &&  player.equals("player 2: move"))
         {
            if(mylist.get(2) - mylist.get(0) == 1) 
            {
               if((returned1 != null && returned2 == null) && (Math.abs(mylist.get(1) - mylist.get(3)) == 1 && mylist.get(2) - mylist.get(0) == 1) && mylist.get(0) < mylist.get(2))
               {
                  normalMove(new ImageIcon("pic3.jpeg"));
                  move = true;
               }
               else
               {
                  noMove();
                  move = false;
               }
            }
            else if(mylist.get(2) - mylist.get(0) == 2)
            {
               if(rightIsBigger(mylist.get(1), mylist.get(3)))
               {
                  if(String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg"))
                  {
                     rGreaterMove1(new ImageIcon("pic3.jpeg"));
                     move = true;
                  }
                  else
                  {
                     noMove();
                     move = false;
                  }
               }
               else if(!(rightIsBigger(mylist.get(1), mylist.get(3))))
               {
                  if(String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic4.jpeg"))
                  {
                     lGreaterMove1(new ImageIcon("pic3.jpeg"));
                     move = true;
                  }
                  else
                  {
                     noMove();
                     move = false;
                  }
               }
            }
         }
         else if(String.valueOf(returned1).equals("pic2.jpeg") && mylist.get(2) == 0 && player.equals("player 1: move"))
         {
            if(mylist.get(0) - mylist.get(2) == 1)
            {
               if((returned1 != null && returned2 == null) && (Math.abs(mylist.get(1) - mylist.get(3)) == 1 && mylist.get(0) - mylist.get(2) == 1) && mylist.get(0) > mylist.get(2))
               {
                  normalMove(new ImageIcon("pic4.jpeg"));
                  move = true;
               }
               else 
               {
                  noMove();
                  move = false;
               }
            }
            else if(mylist.get(0) - mylist.get(2) == 2) 
            {
               if(!(rightIsBigger(mylist.get(1), mylist.get(3))))
               {
                 if(String.valueOf(getIcon(b[(mylist.get(0))-1][mylist.get(1)-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))-1])).equals("pic3.jpeg"))
                 {
                    lGreaterMove2(new ImageIcon("pic4.jpeg"));
                    move = true;
                 }
                 else
                 {
                    noMove();
                    move = false;
                 }
               }
               else if(rightIsBigger(mylist.get(1), mylist.get(3)))
               {
                  if(String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))+1])).equals("pic3.jpeg"))
                  {
                     rGreaterMove2(new ImageIcon("pic4.jpeg"));
                     move = true;
                  }
                  else
                  {
                     noMove();
                     move = false;
                  }
               }
            }
         }
         }
         else if(String.valueOf(returned1).equals("pic3.jpeg") || String.valueOf(returned1).equals("pic4.jpeg"))
         {
            if((Math.abs(mylist.get(2) - mylist.get(0)) == 1 || Math.abs(mylist.get(3) - mylist.get(1)) == 1) && (mylist.get(2) != mylist.get(0) && mylist.get(3) != mylist.get(1)))
            {
               if((String.valueOf(returned1).equals("pic3.jpeg") && returned2 == null) || (String.valueOf(returned1).equals("pic4.jpeg") && returned2 == null))
               {
                  if(String.valueOf(returned1).equals("pic3.jpeg") && player.equals("player 2: move"))
                  {   
                     normalMove(returned1);
                     move = true;
                  }
                  else if(String.valueOf(returned1).equals("pic4.jpeg") && player.equals("player 1: move"))
                  {
                     normalMove(returned1);
                     move = true;
                  }
                  else
                  {
                     noMove();
                     move = false;
                  }
               }
               else
               {
                  noMove();
                  move = false;
               }
            }
            else if(String.valueOf(returned1).equals("pic3.jpeg") && (Math.abs(mylist.get(2) - mylist.get(0)) == 2 || Math.abs(mylist.get(3) - mylist.get(1)) == 2) && (mylist.get(2) != mylist.get(0) && mylist.get(3) != mylist.get(1)))
            {
            if(player.equals("player 2: move") && returned2 == null)
            {
               if(mylist.get(2) > mylist.get(0) && mylist.get(3) > mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg")))
               {
                  rGreaterMove1(returned1);
                  move = true;
               }
               else if(mylist.get(2) > mylist.get(0) && mylist.get(3) < mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg")))
               {
                  lGreaterMove1(returned1);
                  move = true;
               }
               else if(mylist.get(2) < mylist.get(0) && mylist.get(3) > mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg")))
               {
                  rGreaterMove2(returned1);
                  move = true;
               }
               else if(mylist.get(2) < mylist.get(0) && mylist.get(3) < mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))-1][mylist.get(1)-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic4.jpeg")))
               {
                  lGreaterMove2(returned1);
                  move = true;
               }
               else
               {
                  noMove();
                  move = false;
               }
            }
            else
            {
               noMove();
               move = false;
            }
            }   
            else if(String.valueOf(returned1).equals("pic4.jpeg") && (Math.abs(mylist.get(2) - mylist.get(0)) == 2 || Math.abs(mylist.get(3) - mylist.get(1)) == 2) && (mylist.get(2) != mylist.get(0) && mylist.get(3) != mylist.get(1)))
            {
            if(player.equals("player 1: move") && returned2 == null)
            {
               if(mylist.get(2) > mylist.get(0) && mylist.get(3) > mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic3.jpeg")))
               {
                  rGreaterMove1(returned1);
                  move = true;
               }
               else if(mylist.get(2) > mylist.get(0) && mylist.get(3) < mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic3.jpeg")))
               {
                  lGreaterMove1(returned1);
                  move = true;
               }
               else if(mylist.get(2) < mylist.get(0) && mylist.get(3) > mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))-1][(mylist.get(1))+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic3.jpeg")))
               {
                  rGreaterMove2(returned1);
                  move = true;
               }
               else if(mylist.get(2) < mylist.get(0) && mylist.get(3) < mylist.get(1) && (String.valueOf(getIcon(b[(mylist.get(0))-1][mylist.get(1)-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[(mylist.get(0))+1][(mylist.get(1))+1])).equals("pic3.jpeg")))
               {
                  lGreaterMove2(returned1);
                  move = true;
               }
               else
               {
                  noMove();
                  move = false;
               }
            }
            else
            {
               noMove();
               move = false;
            }
            }
            else
            {
               noMove();
               move = false;
            }
         }
         moveRecorder(returned1, move);
      }
   }	
     
   public static void main(String[] args)
   {
      Checkers w = new Checkers();
   	w.setVisible(true);
   }
   
   public boolean ValidateMove(int index1, int index2, int index3, int index4)
   {
      boolean condition = true;
      if(index1 == index3 || index2 == index4)
      {
         condition = false;
      }
      else if((Math.abs(index2 - index4) == 1 || Math.abs(index2 - index4) == 2) && (index3 - index1 == 1 || index3 - index1 == 2))
      {
         condition = true;      
      }
   return condition;
   }
   
   public boolean rightIsBigger(int num1, int num2)
   {
      if(num2 > num1)
         return true;
      else
         return false;
   }
   
   public void normalMove(Icon image)
   {
      b[mylist.get(0)][mylist.get(1)].setIcon(null);
      b[mylist.get(2)][mylist.get(3)].setIcon(image);
      count = 0;
      
   }
   
   public void noMove()
   {
      class FirstWindow
      {
         int width = 300;
         int height = 200;
         public FirstWindow()
         {
            JFrame window = new JFrame( );
            window.setSize(width, height);
            window.setTitle("Move Not Permitted");
            JLabel message = new JLabel("I am sorry but you cannot do that");
            window.add(message);
            window.setVisible(true);
         }
      }
      count = 0;
      
   }
   
   public void rGreaterMove1(Icon image)
   {
      b[mylist.get(0)][mylist.get(1)].setIcon(null);
      b[(mylist.get(0))+1][(mylist.get(1))+1].setIcon(null);
      b[mylist.get(2)][mylist.get(3)].setIcon(image);
      count = 0;
      
   }
   
   public void rGreaterMove2(Icon image)
   {
      b[mylist.get(0)][mylist.get(1)].setIcon(null);
      b[(mylist.get(0))-1][(mylist.get(1))+1].setIcon(null);
      b[mylist.get(2)][mylist.get(3)].setIcon(image);
      count = 0;
      
   }
   
   public void lGreaterMove1(Icon image)
   {
      b[mylist.get(0)][mylist.get(1)].setIcon(null);
      b[(mylist.get(0))+1][(mylist.get(1))-1].setIcon(null);
      b[mylist.get(2)][mylist.get(3)].setIcon(image);
      count = 0;
      
   }
   
   public void lGreaterMove2(Icon image)
   {
       b[mylist.get(0)][mylist.get(1)].setIcon(null);
       b[(mylist.get(0))-1][(mylist.get(1))-1].setIcon(null);
       b[mylist.get(2)][mylist.get(3)].setIcon(image);
       count = 0;
       
   }
   
   public Icon getIcon(JButton selected)
   {
      Icon ret = selected.getIcon();
      return ret;
   }
   
   public void moveRecorder(Icon image, boolean didMove)
   {
      if(player.equals("player 1: move") && move && (String.valueOf(image).equals("pic2.jpeg") || String.valueOf(image).equals("pic4.jpeg")))       
      {
         player = "player 2: move";
         if(checkWinP1() || (!(normalMove(new ImageIcon("pic1.jpeg"), new ImageIcon ("pic3.jpeg"))) && !(jumpMove(new ImageIcon("pic1.jpeg"), new ImageIcon ("pic3.jpeg")))))
         {
            System.out.println("Player 1 has won the game!");
         }
         else
         {
            System.out.println(player);
         }
         mylist.clear();
      }
      else if(!(move))
      {
         System.out.println(player);
      }  
      else if(player.equals("player 2: move") && move && (String.valueOf(image).equals("pic1.jpeg") || String.valueOf(image).equals("pic3.jpeg")))
      {
         player = "player 1: move";
         if(checkWinP2() || (!(normalMove(new ImageIcon("pic2.jpeg"), new ImageIcon ("pic4.jpeg"))) && !(jumpMove(new ImageIcon("pic2.jpeg"), new ImageIcon ("pic4.jpeg")))))
         {
            System.out.println("Player 2 has won the game!");
         }
         else
         {
            System.out.println(player);
         }
         mylist.clear();
      } 
   }
   
   public boolean normalMove(Icon image1, Icon image2)
   { 
      int x, y;
      boolean isPossible = false;
      for(x = 0; x < 8; x++)
      {
         if(isPossible)
         {
            break;
         }
         for(y = 0; y < 8; y++)
         {
            if(isPossible)
         {
            break;
         }
            if(String.valueOf(image1).equals("pic2.jpeg"))  //player 1 moves
            {
            if((String.valueOf(getIcon(b[x][y]))).equals("pic2.jpeg") || (String.valueOf(getIcon(b[x][y]))).equals("pic4.jpeg"))
            {
              if((x != 7) && (y != 0 && y != 7))   //single diagonal moves upwards to both right and left for both non-king and king pieces(player 1)
              {
                 if(getIcon(b[x-1][y-1]) == null || getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null && !(getIcon(b[x-1][y+1]) == null)))
                 {
                    isPossible = false;
                 }
              }    
              else if(y == 0)
              {
                 if(getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y+1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(y == 7)
              {
                 if(getIcon(b[x-1][y-1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(x == 7 && y != 7)
              {
                 if(getIcon(b[x-1][y-1]) == null || getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null) && !(getIcon(b[x-1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }
           }
           else if(String.valueOf(getIcon(b[x][y])).equals(image2))   //king's moves diagonally downwards to the left and right(player 1)
           {
              if((x != 7) && (y != 0 && y != 7))
              {
                 if(getIcon(b[x+1][y-1]) == null || getIcon(b[x+1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y-1]) == null) && !(getIcon(b[x+1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }     
              else if(y == 0)
              {
                 if(getIcon(b[x+1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y+1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(y == 7)
              {
                 if(getIcon(b[x+1][y-1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y-1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(x == 7 && y != 0)
              {
                 if(getIcon(b[x+1][y-1]) == null || getIcon(b[x+1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y-1]) == null) && !(getIcon(b[x+1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }
           }
           }
           else if(String.valueOf(image1).equals("pic1.jpeg")) //player 2 moves
           {
           if((String.valueOf(getIcon(b[x][y]))).equals("pic1.jpeg") || (String.valueOf(getIcon(b[x][y]))).equals("pic3.jpeg"))   ////single diagonal moves downwards to both right and left for both non-king and king pieces(player 2)

           {
              if((x != 7) && (y != 0 && y != 7))
              {
                 if(getIcon(b[x+1][y-1]) == null || getIcon(b[x+1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y-1]) == null) && !(getIcon(b[x+1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }     
              else if(y == 0)
              {
                 if(getIcon(b[1][1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[1][1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(y == 7)
              {
                 if(getIcon(b[x+1][y-1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x+1][y-1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(x == 7 && y != 7)
              {
                 if(getIcon(b[x-1][y-1]) == null || getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null) && !(getIcon(b[x-1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }   
           }
           else if((String.valueOf(getIcon(b[x][y]))).equals(image2))   //king's moves diagonally upwards to the right and left(player 2)
           {
              if((x != 7) && (y != 0 && y != 7))
              {
                 if(getIcon(b[x-1][y-1]) == null || getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null) && !(getIcon(b[x-1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }    
              else if(y == 0)
              {
                 if(getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y+1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(y == 7)
              {
                 if(getIcon(b[x-1][y-1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y+1]) == null))
                 {
                  isPossible = false;
                 }
              }
              else if(x == 7 && y != 7)
              {
                 if(getIcon(b[x-1][y-1]) == null || getIcon(b[x-1][y+1]) == null)
                 {
                    isPossible = true;
                 }
                 else if(!(getIcon(b[x-1][y-1]) == null) && !(getIcon(b[x-1][y+1]) == null))
                 {
                    isPossible = false;
                 }
              }
           }
           }  
         } 
      }
      return isPossible;
   }
  
  public boolean jumpMove(Icon image1, Icon image2)
  {
     //function to check whether a jump move is still possible or not
     boolean isPossible = false;
     for(int x = 0; x < 8; x++)
      {
         if(isPossible)
         {
            break;
         }
         for(int y = 0; y < 8; y++)
         {
            if(isPossible)
         {
            break;
         }
            if(String.valueOf(image1).equals("pic2.jpeg"))  //player 1 moves
            {
               if((String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image2)))
               {
                  if((y >= 0 && y <=5) && x <= 5)  //king & non-king diagonal moves upwards to the right(player 1)
                  {
                     if(String.valueOf(getIcon(b[x-1][y+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[x-1][y+1])).equals("pic3.jpeg"))
                     {
                        if(getIcon(b[x-2][y+2]) == null || getIcon(b[x-2][y+2]) == null)
                        {
                           isPossible = true;  
                        } 
                        else if(!(getIcon(b[x-2][y+2]) == null) || !(getIcon(b[x-2][y+2]) == null))
                        {
                           isPossible = false;
                        }
                     }
                     else if(getIcon(b[x-1][y+1]) == null)
                     {
                        isPossible = true;
                     }
                     else if((String.valueOf(getIcon(b[x-1][y+1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x-1][y+1]))).equals(String.valueOf(image2)))
                     {
                        isPossible = false;
                     }
                  }
                  else if(y >= 2 && x <= 5)  //king's & non-king's diagonal moves upwards to the left(player 1)
                  {
                     if(String.valueOf(getIcon(b[x-1][y-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[x-1][y-1])).equals("pic3.jpeg"))
                     {
                        if(getIcon(b[x-2][y-2]) == null || getIcon(b[x-2][y-2]) == null)
                        {
                           isPossible = true;  
                        } 
                        else if(!(getIcon(b[x-2][y-2]) == null) || !(getIcon(b[x-2][y-2]) == null))
                        {
                           isPossible = false;
                        }
                     }
                     else if(getIcon(b[x-1][y-1]) == null)
                     {
                        isPossible = true;
                     }
                     else if((String.valueOf(getIcon(b[x-1][y-1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x-1][y-1]))).equals(String.valueOf(image2)))
                     {
                        isPossible = false;
                     }
                  }   
               }
               else if((String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image2)))  //king's diagonal moves downwards to the left(player 1)
               {
               if(y >= 2 && x >= 2)
               {
                  if(String.valueOf(getIcon(b[x+1][y-1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[x+1][y-1])).equals("pic3.jpeg"))
                  {
                     if(getIcon(b[x+2][y-2]) == null || getIcon(b[x+2][y-2]) == null)
                     {
                        isPossible = true;  
                     } 
                     else if(!(getIcon(b[x+2][y-2]) == null) || !(getIcon(b[x+2][y-2]) == null))
                     {
                        isPossible = false;
                     }
                  }
                  else if(getIcon(b[x+1][y-1]) == null)
                  {
                     isPossible = true;
                  }
                  else if((String.valueOf(getIcon(b[x+1][y-1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x+1][y-1]))).equals(String.valueOf(image2)))
                  {
                     isPossible = false;
                  }
               }
               else if(y <= 5 && x >= 2)  //king's diagonal moves downwards to the right(player 1)
               {
                  if(String.valueOf(getIcon(b[x-1][y+1])).equals("pic1.jpeg") || String.valueOf(getIcon(b[x-1][y+1])).equals("pic3.jpeg"))
                  {
                     if(getIcon(b[x+2][y+2]) == null || getIcon(b[x+2][y+2]) == null)
                     {
                        isPossible = true;  
                     } 
                     else if(!(getIcon(b[x+2][y+2]) == null) || !(getIcon(b[x+2][y+2]) == null))
                     {
                        isPossible = false;
                     }
                  }
                  else if(getIcon(b[x+1][y+1]) == null)
                  {
                     isPossible = true;
                  }
                  else if((String.valueOf(getIcon(b[x+1][y+1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x+1][y+1]))).equals(String.valueOf(image2)))
                  {
                     isPossible = false;
                  }
               }
               }
            }
            else if(String.valueOf(image1).equals("pic1.jpeg"))   //player 2 moves
            {
               if((String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image2)))
               {
               if(y >= 2 && x >= 2)   //non-king's diagonal moves downwards to the left(player 2)
               {
                  if(String.valueOf(getIcon(b[x-1][y-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[x-1][y-1])).equals("pic4.jpeg"))
                  {
                     if(getIcon(b[x-2][y-2]) == null || getIcon(b[x-2][y-2]) == null)
                     {
                        isPossible = true;  
                     } 
                     else if(!(getIcon(b[x-2][y-2]) == null) || !(getIcon(b[x-2][y-2]) == null))
                     {
                        isPossible = false;
                     }
                  }
                  else if(getIcon(b[x-1][y-1]) == null)
                  {
                     isPossible = true;
                  }
                  else if((String.valueOf(getIcon(b[x-1][y-1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x-1][y-1]))).equals(String.valueOf(image2)))
                  {
                     isPossible = false;
                  }
               }
               else if(y <= 5 && x >= 2)  //non-king's diagonal moves downwards to the right(player 2)
               {
                  if(String.valueOf(getIcon(b[x-1][y+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[x-1][y+1])).equals("pic4.jpeg"))
                  {
                     if(getIcon(b[x-2][y+2]) == null || getIcon(b[x-2][y+2]) == null)
                     {
                        isPossible = true;  
                     } 
                     else if(!(getIcon(b[x-2][y+2]) == null) || !(getIcon(b[x-2][y+2]) == null))
                     {
                        isPossible = false;
                     }
                  }
                  else if(getIcon(b[x-1][y+1]) == null)
                  {
                     isPossible = true;
                  }
                  else if((String.valueOf(getIcon(b[x-1][y+1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x-1][y+1]))).equals(String.valueOf(image2)))
                  {
                     isPossible = false;
                  }
               }
               }
            }
            else if((String.valueOf(getIcon(b[x][y]))).equals(String.valueOf(image2)))
            {
               if((y >= 0 && y <=5) && x <= 5)  //king's diagonal moves upwards to the right(player 2)
                  {
                     if(String.valueOf(getIcon(b[x+1][y+1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[x+1][y+1])).equals("pic4.jpeg"))
                     {
                        if(getIcon(b[x+2][y+2]) == null || getIcon(b[x+2][y+2]) == null)
                        {
                           isPossible = true;  
                        } 
                        else if(!(getIcon(b[x+2][y+2]) == null) || !(getIcon(b[x+2][y+2]) == null))
                        {
                           isPossible = false;
                        }
                     }
                     else if(getIcon(b[x+1][y+1]) == null)
                     {
                        isPossible = true;
                     }
                     else if((String.valueOf(getIcon(b[x+1][y+1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x+1][y+1]))).equals(String.valueOf(image2)))
                     {
                        isPossible = false;
                     }
                  }
                  else if(y >= 2 && x <= 5)  //king's diagonal moves upwards to the left(player 2)
                  {
                     if(String.valueOf(getIcon(b[x+1][y-1])).equals("pic2.jpeg") || String.valueOf(getIcon(b[x+1][y-1])).equals("pic4.jpeg"))
                     {
                        if(getIcon(b[x+2][y-2]) == null || getIcon(b[x+2][y-2]) == null)
                        {
                           isPossible = true;  
                        } 
                        else if(!(getIcon(b[x+2][y-2]) == null) || !(getIcon(b[x+2][y-2]) == null))
                        {
                           isPossible = false;
                        }
                     }
                     else if(getIcon(b[x+1][y-1]) == null)
                     {
                        isPossible = true;
                     }
                     else if((String.valueOf(getIcon(b[x+1][y-1]))).equals(String.valueOf(image1)) || (String.valueOf(getIcon(b[x+1][y-1])).equals(String.valueOf(image2))))
                     {
                        isPossible = false;
                     }
                  }
            }
         }
      }
      return isPossible;
   }
   
   public void initializeGame()
   {
      Checkers w = new Checkers();
   	w.setVisible(true);
   }
   
   public boolean checkWinP1()
   {
      int countP2Pieces = 0;
      for(int i = 0; i < 8; i++)
      {
         for(int j = 0; j < 8; j++)
         {
            if(String.valueOf(getIcon(b[i][j])).equals("pic1.jpeg") || String.valueOf(getIcon(b[i][j])).equals("pic3.jpeg"))
            {
               countP2Pieces += 1;
            }
            else
            {
               countP2Pieces += 0;
            }  
         }
      }
      if(countP2Pieces == 0)
      {
         winP1 = true;
      }
      else
      {
         winP1 = false;
      }
      return winP1;
   }
   
    public boolean checkWinP2()
    {
      int countP1Pieces = 0;
      for(int i = 0; i < 8; i++)
      {
         for(int j = 0; j < 8; j++)
         {
            if(String.valueOf(getIcon(b[i][j])).equals("pic2.jpeg") || String.valueOf(getIcon(b[i][j])).equals("pic4.jpeg"))
            {
               countP1Pieces += 1;
            }
            else
            {
               countP1Pieces += 0;
            }  
         }
      }
      if(countP1Pieces == 0)
      {
         winP2 = true;
      }
      else
      {
         winP2 = false;
      }
      return winP2;
   }
}
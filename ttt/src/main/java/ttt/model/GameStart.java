package ttt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameStart
{
	Random ran = new Random();

	List<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
	String Tied = null;
	String won = null;
	
    public Random getRan() {
		return ran;
	}

	public void setRan(Random ran) {
		this.ran = ran;
	}

	public List<ArrayList<String>> getMatrix() {
		return matrix;
	}

	public void setMatrix(List<ArrayList<String>> matrix) {
		this.matrix = matrix;
	}

	public String getTied() {
		return Tied;
	}

	public void setTied(String tied) {
		Tied = tied;
	}

	public String getWon() {
		return won;
	}

	public void setWon(String won) {
		this.won = won;
	}

	public GameStart() 
    {
    	for(int i=0; i < 3; i++)
		{
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("_"); temp.add("_"); temp.add("_");
			matrix.add(temp);
		}
    }

	public void doGet(int i , int j) 
	{
		int c = 0;
		for(int k = 0; k < 3; k++)
		{
			for( int l = 0; l < 3; l++)
			{
				if(!matrix.get(k).get(l).equalsIgnoreCase("_"))
				{
					c++;
				}
			}
		}
		
		if(c==8)
		{
			Tied = "Tied";
			matrix.get(i).set(j, "X");
		}
		else
		if(i!= -1 && j!=-1)
		{
						matrix.get(i).set(j, "X");
			if(!CheckWhoWon(matrix))
			if(!checkForWin(matrix))
			{ 
				if(!CheckWhoWon(matrix))
				if(!stopWin(matrix))
				{
					if(!CheckWhoWon(matrix))
					randomMove(matrix);
				}
				else
					CheckWhoWon(matrix);
			}
			else
				CheckWhoWon(matrix);
		}
	}

	public boolean CheckWhoWon(List<ArrayList<String>> matrix) 
	{
		for(int i =0; i<3;i++)
		{
			int ocount=0,xcount=0;
			for(int j=0;j<3;j++)
			{
				if(matrix.get(i).get(j).equalsIgnoreCase("O"))
				{
					ocount++;
				}
				if(matrix.get(i).get(j).equalsIgnoreCase("x"))
				{
					xcount++;
				}
			}
			if(ocount == 3)
			{
				won = "I";
				System.out.println("I won");
				return true;
			}
			if(xcount == 3)
			{
				won = "You";
				System.out.println("You won");
				return true;
			}
			else
			{
				ocount =0; xcount=0;
			}
		}
		for(int i =0; i<3;i++)
		{
			int ocount=0,xcount=0;
			for(int j=0;j<3;j++)
			{
				if(matrix.get(j).get(i).equalsIgnoreCase("O"))
				{
					ocount++;
				}
				if(matrix.get(j).get(i).equalsIgnoreCase("x"))
				{
					xcount++;
				}
			}
			if(ocount == 3)
			{
				won = "I";
				System.out.println("I won");
				return true;
			}
			if(xcount == 3)
			{
				won = "You";
				System.out.println("You won");
				return true;
			}
			else
			{
				ocount =0; xcount=0;
			}
		}
		
		if(matrix.get(0).get(0).equalsIgnoreCase("x") && matrix.get(1).get(1).equalsIgnoreCase("x") && matrix.get(2).get(2).equalsIgnoreCase("x"))
		{
			won = "You";
			System.out.println("You won");
			return true;
		}
		if(matrix.get(0).get(0).equalsIgnoreCase("o") && matrix.get(1).get(1).equalsIgnoreCase("o") && matrix.get(2).get(2).equalsIgnoreCase("o"))
		{
			won = "I";
			System.out.println("I won");
			return true;
		}
		if(matrix.get(0).get(2).equalsIgnoreCase("x") && matrix.get(1).get(1).equalsIgnoreCase("x") && matrix.get(2).get(0).equalsIgnoreCase("x"))
		{
			won = "You";
			System.out.println("You won");
			return true;
		}
		if(matrix.get(0).get(2).equalsIgnoreCase("o") && matrix.get(1).get(1).equalsIgnoreCase("o") && matrix.get(2).get(0).equalsIgnoreCase("o"))
		{
			won = "I";
			System.out.println("I won");
			return true;
		}
		
		return false;
	}

	public void randomMove(List<ArrayList<String>> matrix) 
	{
		//System.out.println("Random");
		int i1 = ran.nextInt(3);
		int j1 = ran.nextInt(3), count = 0, c = 0;
		for(int i = 0; i < 3; i++)
		{
			for( int j = 0; j < 3; j++)
			{
				if(!matrix.get(i).get(j).equalsIgnoreCase("_"))
				{
					c++;
				}
			}
		}
		
		if(c<8)
		{
				if(matrix.get(i1).get(j1).equalsIgnoreCase("_"))
				{
					matrix.get(i1).set(j1, "O");
					return;
				}
				else
				{
					count++;
				}
		}
		else
		{
			for(int i = 0; i < 3; i++)
			{
				for( int j = 0; j < 3; j++)
				{
					if(matrix.get(i).get(j).equalsIgnoreCase("_"))
					{
						matrix.get(i).set(j, "O");
					}
				}
			}
		}
				
		if(count > 0)
		{
			randomMove(matrix);
		}
	}

	public boolean stopWin(List<ArrayList<String>> matrix) 
	{
		for(int i = 0 ; i < 3; i++)
		{
			int temp=0;
		if((matrix.get(i).get(0).equalsIgnoreCase("X") && matrix.get(i).get(1).equalsIgnoreCase("X")) || 
				(matrix.get(i).get(0).equalsIgnoreCase("X") && matrix.get(i).get(2).equalsIgnoreCase("X")) ||
				(matrix.get(i).get(1).equalsIgnoreCase("X") && matrix.get(i).get(2).equalsIgnoreCase("X")))
		{
			if((matrix.get(i).get(0).equalsIgnoreCase("X") && matrix.get(i).get(1).equalsIgnoreCase("X")))
			{
				if(matrix.get(i).get(2).equalsIgnoreCase("_"))
				matrix.get(i).set(2,"O");
				else
					temp++;
			}
			if((matrix.get(i).get(0).equalsIgnoreCase("X") && matrix.get(i).get(2).equalsIgnoreCase("X")))
			{
				if(matrix.get(i).get(1).equalsIgnoreCase("_"))
				matrix.get(i).set(1,"O");
				else
					temp++;
			}
			if((matrix.get(i).get(2).equalsIgnoreCase("X") && matrix.get(i).get(1).equalsIgnoreCase("X")))
			{
				if(matrix.get(i).get(0).equalsIgnoreCase("_"))
				matrix.get(i).set(0,"O");
				else
					temp++;
			}
			if(temp>0)
			{
				randomMove(matrix);
			}
			return true;
		}
		}
		
			for(int i = 0 ; i < 3; i++)
			{
				int temp = 0;
			if((matrix.get(0).get(i).equalsIgnoreCase("X") && matrix.get(1).get(i).equalsIgnoreCase("X")) || 
					(matrix.get(0).get(i).equalsIgnoreCase("X") && matrix.get(2).get(i).equalsIgnoreCase("X")) ||
					(matrix.get(1).get(i).equalsIgnoreCase("X") && matrix.get(2).get(i).equalsIgnoreCase("X")))
			{
				if((matrix.get(0).get(i).equalsIgnoreCase("X") && matrix.get(1).get(i).equalsIgnoreCase("X")))
				{
					if(matrix.get(2).get(i).equalsIgnoreCase("_"))
						matrix.get(2).set(i,"O");
					else
						temp++;
				}
				if((matrix.get(0).get(i).equalsIgnoreCase("X") && matrix.get(2).get(i).equalsIgnoreCase("X")))
				{
					if(matrix.get(1).get(i).equalsIgnoreCase("_"))
						matrix.get(1).set(i,"O");
					else
						temp++;
				}
				if((matrix.get(2).get(i).equalsIgnoreCase("X") && matrix.get(1).get(i).equalsIgnoreCase("X")))
				{
					if(matrix.get(0).get(i).equalsIgnoreCase("_"))
						matrix.get(0).set(i,"O");
					else
						temp++;
				}
				if(temp>0)
						randomMove(matrix);
				return true;
			}
			}
			
			int i =0, j=0;

			if(matrix.get(i).get(j).equalsIgnoreCase("X") || matrix.get(i + 1).get(j + 1).equalsIgnoreCase("X") || matrix.get(i + 2).get(j + 2).equalsIgnoreCase("X"))
			{
				if(matrix.get(i).get(j).equalsIgnoreCase("X"))
				{
				i++;j++;
			if( matrix.get(i).get(j).equalsIgnoreCase("X") && matrix.get(i + 1).get(j + 1).equalsIgnoreCase("_"))
			{
				matrix.get(i + 1).set(j + 1, "O");
				return true;
			}
			else
			{
				i++;j++;
				if( matrix.get(i).get(j).equalsIgnoreCase("x") && matrix.get(i - 1).get(j - 1).equalsIgnoreCase("_"))
				{
					matrix.get(i - 1).set(j - 1, "O");

					return true;
					
				}

			}
				}
				else
				{
					if(matrix.get(i+1).get(j+1).equalsIgnoreCase("x"))
					{
				if( matrix.get(i).get(j).equalsIgnoreCase("x") && matrix.get(i + 2).get(j + 2).equalsIgnoreCase("_"))
				{
					matrix.get(i + 2).set(j + 2, "O");

					return true;
					
				}
				else
				{
					if( matrix.get(i + 2).get(j + 2).equalsIgnoreCase("x") && matrix.get(i).get(j).equalsIgnoreCase("_"))
					{
						matrix.get(i).set(j, "O");

						return true;
						
					}

				}

				}
					else
					{
						if(matrix.get(i+2).get(j+2).equalsIgnoreCase("x"))
						{
					if( matrix.get(i).get(j).equalsIgnoreCase("x") && matrix.get(i + 1).get(j + 1).equalsIgnoreCase("_"))
					{
						matrix.get(i + 1).set(j + 1, "O");
						return true;
						
					}
					else
					{
						if( matrix.get(i + 1).get(j + 1).equalsIgnoreCase("x") && matrix.get(i).get(j).equalsIgnoreCase("_"))
						{
							matrix.get(i).set(j, "O");
							return true;
							
						}

					}

					}
					}
			}
			}
	
			
		return false;
	}

	public boolean checkForWin(List<ArrayList<String>> matrix) 
	{
		for(int i = 0 ; i < 3; i++)
		{
			int temp = 0;
		if((matrix.get(i).get(0).equalsIgnoreCase("O") && matrix.get(i).get(1).equalsIgnoreCase("O")) || 
				(matrix.get(i).get(0).equalsIgnoreCase("O") && matrix.get(i).get(2).equalsIgnoreCase("O")) ||
				(matrix.get(i).get(1).equalsIgnoreCase("O") && matrix.get(i).get(2).equalsIgnoreCase("O")))
		{
			if((matrix.get(i).get(0).equalsIgnoreCase("o") && matrix.get(i).get(1).equalsIgnoreCase("o")))
			{
				if(matrix.get(i).get(2).equalsIgnoreCase("_"))
				matrix.get(i).set(2,"O");
				else
					temp++;
			}
			if((matrix.get(i).get(0).equalsIgnoreCase("o") && matrix.get(i).get(2).equalsIgnoreCase("o")))
			{
				if(matrix.get(i).get(1).equalsIgnoreCase("_"))
				matrix.get(i).set(1,"O");
				else
					temp++;
			}
			if((matrix.get(i).get(2).equalsIgnoreCase("o") && matrix.get(i).get(1).equalsIgnoreCase("o")))
			{
				if(matrix.get(i).get(0).equalsIgnoreCase("_"))
				matrix.get(i).set(0,"O");
				else
					temp++;
			}
			if(temp>0)
				randomMove(matrix);
			return true;
		}
		}
			for(int i = 0 ; i < 3; i++)
			{
			int temp= 0;
			if((matrix.get(0).get(i).equalsIgnoreCase("O") && matrix.get(1).get(i).equalsIgnoreCase("O")) || 
					(matrix.get(0).get(i).equalsIgnoreCase("O") && matrix.get(2).get(i).equalsIgnoreCase("O")) ||
					(matrix.get(1).get(i).equalsIgnoreCase("O") && matrix.get(2).get(i).equalsIgnoreCase("O")))
			{
				if((matrix.get(0).get(i).equalsIgnoreCase("o") && matrix.get(1).get(i).equalsIgnoreCase("o")))
				{
					if(matrix.get(2).get(i).equalsIgnoreCase("_"))
						matrix.get(2).set(i,"O");
						else
							temp++;
							//randomMove(matrix);
				}
				if((matrix.get(0).get(i).equalsIgnoreCase("o") && matrix.get(2).get(i).equalsIgnoreCase("o")))
				{
					if(matrix.get(1).get(i).equalsIgnoreCase("_"))
						matrix.get(1).set(i,"O");
						else
							temp++;//randomMove(matrix);
				}
				if((matrix.get(2).get(i).equalsIgnoreCase("o") && matrix.get(1).get(i).equalsIgnoreCase("o")))
				{
					if(matrix.get(0).get(i).equalsIgnoreCase("_"))
						matrix.get(0).set(i,"O");
						else
							temp++;//randomMove(matrix);
				}
				if(temp>0)
					randomMove(matrix);
				return true;
			}
			}
			
			int i =0, j=0;

			if(matrix.get(i).get(j).equalsIgnoreCase("O") || matrix.get(i + 1).get(j + 1).equalsIgnoreCase("O") || matrix.get(i + 2).get(j + 2).equalsIgnoreCase("O"))
			{
				if(matrix.get(i).get(j).equalsIgnoreCase("O"))
				{
				i++;j++;
			if( matrix.get(i).get(j).equalsIgnoreCase("O") && matrix.get(i + 1).get(j + 1).equalsIgnoreCase("_"))
			{
				matrix.get(i + 1).set(j + 1, "O");
				//won = "I";
			//	System.out.println(won + "won!");

				return true;
				
			}
			else
			{
				i++;j++;
				if( matrix.get(i).get(j).equalsIgnoreCase("O") && matrix.get(i - 1).get(j - 1).equalsIgnoreCase("_"))
				{
					matrix.get(i - 1).set(j - 1, "O");
			//		won = "I";
				//	System.out.println(won + "won!");

					return true;
					
				}

			}
				}
				else
				{
					if(matrix.get(i+1).get(j+1).equalsIgnoreCase("O"))
					{
				if( matrix.get(i).get(j).equalsIgnoreCase("O") && matrix.get(i + 2).get(j + 2).equalsIgnoreCase("_"))
				{
					matrix.get(i + 2).set(j + 2, "O");
			//		won = "I";
				//	System.out.println(won + "won!");

					return true;
					
				}
				else
				{
					if( matrix.get(i + 2).get(j + 2).equalsIgnoreCase("O") && matrix.get(i).get(j).equalsIgnoreCase("_"))
					{
						matrix.get(i).set(j, "O");
			//			won = "I";
		//				System.out.println(won + "won!");

						return true;
						
					}

				}

				}
					else
					{
						if(matrix.get(i+2).get(j+2).equalsIgnoreCase("O"))
						{
					if( matrix.get(i).get(j).equalsIgnoreCase("O") && matrix.get(i + 1).get(j + 1).equalsIgnoreCase("_"))
					{
						matrix.get(i + 1).set(j + 1, "O");
			//			won = "I";
				//		System.out.println(won + "won!");

						return true;
						
					}
					else
					{
						if( matrix.get(i + 1).get(j + 1).equalsIgnoreCase("O") && matrix.get(i).get(j).equalsIgnoreCase("_"))
						{
							matrix.get(i).set(j, "O");
			//				won = "I";
			//				System.out.println(won + "won!");

							return true;
							
						}

					}

					}
					}
			}
			}
	
		
		return false;
	}

}

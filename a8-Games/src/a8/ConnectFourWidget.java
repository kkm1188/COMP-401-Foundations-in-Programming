package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectFourWidget extends JPanel implements ActionListener, SpotListener {

	private enum Player {RED, BLACK};

	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */

	public Spot winner1 = null;
	public Spot winner2 = null;
	public Spot winner3 = null;
	public Spot winner4 = null;



	public ConnectFourWidget(){
		/* Create SpotBoard and message label. */

		_board = new JSpotBoard(7,6);
		_message = new JLabel();

		/* Set layout and place SpotBoard at center. */

		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		/* Create subpanel for message area and reset button. */

		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */

		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_message_panel.add(reset_button, BorderLayout.EAST);
		reset_message_panel.add(_message, BorderLayout.CENTER);

		/* Add subpanel in south area of layout. */

		add(reset_message_panel, BorderLayout.SOUTH);

		/* Add ourselves as a spot listener for all of the
		 * spots on the spot board.
		 */
		_board.addSpotListener(this);

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				if(i%2 == 0) {
					_board.getSpotAt(i, j).setBackground(Color.LIGHT_GRAY);
				} else {
					_board.getSpotAt(i, j).setBackground(Color.DARK_GRAY);
				}
			}
		}
		/* Reset game. */
		resetGame();
	}

	public void resetGame() {
		for (Spot s : _board) {
			s.clearSpot();
		}
		for(int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				_board.getSpotAt(i,j).unhighlightSpot();
			}
		}
		/* Reset game won and next to play fields */
		_game_won = false;
		_next_to_play = Player.RED;		

		/* Display game start message. */

		_message.setText("Welcome to Connect Four. Red to play");
	}

	private boolean checkRedColumns() {
		int counter = 0;

		for(int i = 0; i < 7; i++) {
			counter = 0;
			for(int j = 0; j < 6; j++) {
				if(_board.getSpotAt(i, j).getSpotColor() == Color.RED && !_board.getSpotAt(i, j).isEmpty()) {
					counter++;
					if(counter == 1) {
						winner1 = _board.getSpotAt(i, j);
					} else if (counter == 2) {
						winner2 = _board.getSpotAt(i, j);
					} else if(counter == 3) {
						winner3 = _board.getSpotAt(i, j);
					} else if(counter == 4) {
						winner4 = _board.getSpotAt(i, j);
					}
				} else {
					counter = 0;
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkBlackColumns() {
		int counter = 0;

		for(int i = 0; i < 7; i++) {
			counter = 0;
			for(int j = 0; j < 6; j++) {
				if(_board.getSpotAt(i, j).getSpotColor() == Color.BLACK && !_board.getSpotAt(i, j).isEmpty()) {
					counter++;
					if(counter == 1) {
						winner1 = _board.getSpotAt(i, j);
					} else if (counter == 2) {
						winner2 = _board.getSpotAt(i, j);
					} else if(counter == 3) {
						winner3 = _board.getSpotAt(i, j);
					} else if(counter == 4) {
						winner4 = _board.getSpotAt(i, j);
					}
				} else {
					counter = 0;
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkRedRows() {
		int counter = 0;

		for(int i = 0; i < 6; i++) {
			counter = 0;
			for(int j = 0; j < 7; j++) {
				if(_board.getSpotAt(j, i).getSpotColor() == Color.RED && !_board.getSpotAt(j, i).isEmpty()) {
					counter++;
					if(counter == 1) {
						winner1 = _board.getSpotAt(j, i);
					} else if (counter == 2) {
						winner2 = _board.getSpotAt(j, i);
					} else if(counter == 3) {
						winner3 = _board.getSpotAt(j, i);
					} else if(counter == 4) {
						winner4 = _board.getSpotAt(j, i);
					}
				} else {
					counter = 0;
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkBlackRows() {
		int counter = 0;

		for(int i = 0; i < 6; i++) {
			counter = 0;
			for(int j = 0; j < 7; j++) {
				if(_board.getSpotAt(j, i).getSpotColor() == Color.BLACK && !_board.getSpotAt(j, i).isEmpty()) {
					counter++;
					if(counter == 1) {
						winner1 = _board.getSpotAt(j, i);
					} else if (counter == 2) {
						winner2 = _board.getSpotAt(j, i);
					} else if(counter == 3) {
						winner3 = _board.getSpotAt(j, i);
					} else if(counter == 4) {
						winner4 = _board.getSpotAt(j, i);
					}
				} else {
					counter = 0;
				}
				if(counter == 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkNegativeDiagonal(JSpotBoard spot) {
		for(int i = 6; i >= 3; i--) {
			for(int j = 5; j >= 3; j--) {
				if(spot.getSpotAt(i,j).getSpotColor() == spot.getSpotAt(i-1,j-1).getSpotColor() 
						&& spot.getSpotAt(i-1,j-1).getSpotColor() == spot.getSpotAt(i-2,j-2).getSpotColor()
						&& spot.getSpotAt(i-2,j-2).getSpotColor() == spot.getSpotAt(i-3,j-3).getSpotColor()
						&& !_board.getSpotAt(i,j).isEmpty()
						&& !_board.getSpotAt(i-1,j-1).isEmpty() 
						&& !_board.getSpotAt(i-2,j-2).isEmpty() 
						&& !_board.getSpotAt(i-3,j-3).isEmpty()) {
					
					winner1 = _board.getSpotAt(i,j);
					winner2 = _board.getSpotAt(i-1,j-1);
					winner3 = _board.getSpotAt(i-2,j-2);
					winner4 = _board.getSpotAt(i-3,j-3);
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkPositiveDiagonal(JSpotBoard spot) {
		for(int i = 0; i <= 3; i++) {
			for(int j = 5; j >= 3; j--) {
				if(spot.getSpotAt(i,j).getSpotColor() == spot.getSpotAt(i+1,j-1).getSpotColor() 
						&& spot.getSpotAt(i+1,j-1).getSpotColor() == spot.getSpotAt(i+2,j-2).getSpotColor()
						&& spot.getSpotAt(i+2,j-2).getSpotColor() == spot.getSpotAt(i+3,j-3).getSpotColor()
						&& !_board.getSpotAt(i,j).isEmpty()
						&& !_board.getSpotAt(i+1,j-1).isEmpty() 
						&& !_board.getSpotAt(i+2,j-2).isEmpty() 
						&& !_board.getSpotAt(i+3,j-3).isEmpty()) {
					
					winner1 = _board.getSpotAt(i,j);
					winner2 = _board.getSpotAt(i+1,j-1);
					winner3 = _board.getSpotAt(i+2,j-2);
					winner4 = _board.getSpotAt(i+3,j-3);
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkDraw() {
		if(!_board.getSpotAt(0,0).isEmpty() && !_board.getSpotAt(0,1).isEmpty() && !_board.getSpotAt(0,2).isEmpty() 
				&& !_board.getSpotAt(0,3).isEmpty() && !_board.getSpotAt(0,4).isEmpty() && !_board.getSpotAt(0,5).isEmpty() 
				&& !_board.getSpotAt(1,0).isEmpty() && !_board.getSpotAt(1,1).isEmpty() && !_board.getSpotAt(1,2).isEmpty()
				&& !_board.getSpotAt(1,3).isEmpty() && !_board.getSpotAt(1,4).isEmpty() && !_board.getSpotAt(1,5).isEmpty() 
				&& !_board.getSpotAt(2,0).isEmpty() && !_board.getSpotAt(2,1).isEmpty() && !_board.getSpotAt(2,2).isEmpty() 
				&& !_board.getSpotAt(2,3).isEmpty() && !_board.getSpotAt(2,4).isEmpty() && !_board.getSpotAt(2,5).isEmpty()
				&& !_board.getSpotAt(3,0).isEmpty() && !_board.getSpotAt(3,1).isEmpty() && !_board.getSpotAt(3,2).isEmpty() 
				&& !_board.getSpotAt(3,3).isEmpty() && !_board.getSpotAt(3,4).isEmpty() && !_board.getSpotAt(3,5).isEmpty()
				&& !_board.getSpotAt(4,0).isEmpty() && !_board.getSpotAt(4,1).isEmpty() && !_board.getSpotAt(4,2).isEmpty() 
				&& !_board.getSpotAt(4,3).isEmpty() && !_board.getSpotAt(4,4).isEmpty() && !_board.getSpotAt(4,5).isEmpty()
				&& !_board.getSpotAt(5,0).isEmpty() && !_board.getSpotAt(5,1).isEmpty() && !_board.getSpotAt(5,2).isEmpty() 
				&& !_board.getSpotAt(5,3).isEmpty() && !_board.getSpotAt(5,4).isEmpty() && !_board.getSpotAt(5,5).isEmpty()
				&& !_board.getSpotAt(6,0).isEmpty() && !_board.getSpotAt(6,1).isEmpty() && !_board.getSpotAt(6,2).isEmpty() 
				&& !_board.getSpotAt(6,3).isEmpty() && !_board.getSpotAt(6,4).isEmpty() && !_board.getSpotAt(6,5).isEmpty()) {
					return true;
			}
		return false;
	}

	@Override
	public void spotClicked(Spot spot) {
		if (_game_won) {
			return;
		}

		/* Set up player and next player name strings,
		 * and player color as local variables to
		 * be used later.
		 */

		String player_name = null;
		String next_player_name = null;
		Color player_color = null;

		if (_next_to_play == Player.RED) {
			player_color = Color.RED;
			player_name = "Red";
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "Black";
			next_player_name = "Red";
			_next_to_play = Player.RED;			
		}


		/* Set color of spot clicked and toggle. */
		int xValue = spot.getSpotX();
		int yValue = spot.getSpotY();

		for(int i = 5; i >= 0; i--) {
			if(_board.getSpotAt(xValue,i).isEmpty()) {
				_board.getSpotAt(xValue,i).setSpotColor(player_color);
				_board.getSpotAt(xValue,i).toggleSpot();
				_message.setText(next_player_name + " to play.");
				break;
		}
		}
		
		 if (checkRedRows() || checkBlackRows() || checkRedColumns() || checkBlackColumns() 
				|| checkPositiveDiagonal(_board) || checkNegativeDiagonal(_board)) {
			_game_won = true;
		} 
		
		
		if(_game_won) {
			_message.setText(player_name + " wins!");
			winner1.highlightSpot();
			winner2.highlightSpot();
			winner3.highlightSpot();
			winner4.highlightSpot();
			} else if(checkDraw()) {
				_message.setText("Draw.");
		}
}


	@Override
	public void spotEntered(Spot spot) {
		if (_game_won) {
			return;
		}
		int column = spot.getSpotX();

		if(spot.isEmpty() && !(_game_won)) {
			spot.highlightSpot();
		}
		for(int i = 0; i < 6; i++) {
			if(_board.getSpotAt(column, i).isEmpty() && !(_game_won)) {
				_board.getSpotAt(column, i).highlightSpot();
			}
		}
	}

	@Override
	public void spotExited(Spot spot) {
		int column = spot.getSpotX();

		if(_game_won) {
			for(int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if(_board.getSpotAt(i, j) != winner1 
							&& _board.getSpotAt(i, j) != winner2 
							&& _board.getSpotAt(i, j) != winner3 
							&& _board.getSpotAt(i, j) != winner4) {
						_board.getSpotAt(i,j).unhighlightSpot();
					}
				}
			}
		}

		spot.unhighlightSpot();
		for(int i = 0; i < 6; i++) {
			if(_board.getSpotAt(column, i).isEmpty() && !(_game_won)) {
				_board.getSpotAt(column, i).unhighlightSpot();
			}
		}

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				if(!_board.getSpotAt(i,j).isEmpty() && !_game_won) {
					_board.getSpotAt(i,j).unhighlightSpot();
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		resetGame();
	}
}
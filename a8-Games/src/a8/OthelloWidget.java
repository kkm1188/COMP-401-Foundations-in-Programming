package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OthelloWidget extends JPanel implements ActionListener, SpotListener {

	private enum Player {BLACK, WHITE};

	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */
	public Spot spot_1 = null;
	public Spot spot_2 = null;
	public int numWhite = 0;
	public int numBlack = 0;
	public Player winner = null;
	public boolean over = false;

	public OthelloWidget() {

		/* Create SpotBoard and message label. */

		_board = new JSpotBoard(8,8);
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

		/* Reset game. */
		resetGame();
	}

	private void resetGame() {
		/* Clear all spots on board. Uses the fact that SpotBoard
		 * implements Iterable<Spot> to do this in a for-each loop.
		 */

		for (Spot s : _board) {
			s.clearSpot();
		}
		_game_won = false;
		_next_to_play = Player.BLACK;
		
		_board.getSpotAt(3, 3).setSpotColor(Color.WHITE);
		_board.getSpotAt(3, 3).toggleSpot();
		_board.getSpotAt(4, 4).setSpotColor(Color.WHITE);
		_board.getSpotAt(4, 4).toggleSpot();

		_board.getSpotAt(3, 4).setSpotColor(Color.BLACK);
		_board.getSpotAt(3, 4).toggleSpot();
		_board.getSpotAt(4, 3).setSpotColor(Color.BLACK);
		_board.getSpotAt(4, 3).toggleSpot();

		/* Display game start message. */

		_message.setText("Welcome to Othello. Black goes first.");
	}

	private boolean isLegalMove(Spot spot) {
		int x = spot.getSpotX();
		int y = spot.getSpotY();

		Color player_color = null;
		Color opp_color = null;

		if (_next_to_play == Player.WHITE) {
			player_color = Color.WHITE;
			opp_color = Color.BLACK;
		} else {
			player_color = Color.BLACK;
			opp_color = Color.WHITE;		
		}

		if(spot.isEmpty() && !(x < 0 || x >= 8 || y < 0 || y >= 8)) {

			if((x+1) < 7 && y < 8) {
				if(_board.getSpotAt(x+1, y).getSpotColor().equals(opp_color)) {
					int i = x + 1;
					while(i < 7) {
						if(_board.getSpotAt(i + 1, y).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i ,y);
							return true;
						} else if(_board.getSpotAt(i + 1, y).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i + 1, y).getSpotColor().equals(opp_color)) {
							i++;
						} else {
							break;
						}	
					}
				}
			}
			if((y+1) < 7) {
				if(_board.getSpotAt(x, y+1).getSpotColor().equals(opp_color)) {
					int i = y+1;
					while(i < 7) {
						if(_board.getSpotAt(x, i+1).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(x,i+1);
							return true;
						} else if(_board.getSpotAt(x,i+1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(x, i+1).getSpotColor().equals(opp_color)) {
							i++;
						} 	
					}

				}
			}
			if((x+1) < 7 && (y+1) < 7) {
				if(_board.getSpotAt(x+1, y+1).getSpotColor().equals(opp_color)) {
					int i = x + 1;
					int j = y + 1;
					while(i < 7 && j < 7) {
						if(_board.getSpotAt(i, j).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i,j);
							return true;
						} else if(_board.getSpotAt(i + 1, j+1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i + 1, j+1).getSpotColor().equals(opp_color)) {
							i++;
							j++;
						} 	
					}
				}
			}
			if(x-1 > 0) {
				if(_board.getSpotAt(x-1, y).getSpotColor().equals(opp_color)) {
					int i = x - 1;
					while(i > 0) {
						if(_board.getSpotAt(i - 1, y).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i,y);
							return true;
						} else if(_board.getSpotAt(i - 1, y).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i - 1, y).getSpotColor().equals(opp_color)) {
							i--;
						} 
					}
				}
			}
			if((y-1) > 0) {
				if(_board.getSpotAt(x, y-1).getSpotColor().equals(opp_color)) {
					int i = y - 1;
					while(i > 0) {
						if(_board.getSpotAt(x, i-1).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(x,i);
							return true;
						} else if(_board.getSpotAt(x, i-1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(x, i-1).getSpotColor().equals(opp_color)) {
							i--;
						}	
					}
				}
			}
			if((x-1) > 0 && (y-1) > 0) {
				if(_board.getSpotAt(x-1, y -1).getSpotColor().equals(opp_color)) {
					int i = x - 1;
					int j = y -1;
					while(i > 0 && j > 0) {
						if(_board.getSpotAt(i , j).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i ,j);
							return true;
						} else if(_board.getSpotAt(i - 1, j-1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i - 1, j-1).getSpotColor().equals(opp_color)) {
							i--;
							j--;
						} 
					}
				}
			}
			if((x-1) > 0 && (y+1) <7) {
				if(_board.getSpotAt(x, y ).getSpotColor().equals(opp_color)) {
					int i = x - 1;
					int j = y + 1;
					while(i > 0 && j < 7) {
						if(_board.getSpotAt(i-1 , j+1).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i ,j);
							return true;
						} else if(_board.getSpotAt(i - 1, j + 1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i - 1, j+1).getSpotColor().equals(opp_color)) {
							i--;
							j++;
						}	
					}
				}
			}
			if((x+1) < 7 && (y-1) > 0) {
				if(_board.getSpotAt(x + 1, y -1).getSpotColor().equals(opp_color)) {
					int i = x + 1;
					int j = y - 1;
					while(i < 7 && j > 0) {
						if(_board.getSpotAt(i, j).getSpotColor().equals(player_color)) {
							spot_1 = spot;
							spot_2 = _board.getSpotAt(i,j);
							return true;
						} else if(_board.getSpotAt(i + 1, j-1).isEmpty()) {
							break;
						} else if(_board.getSpotAt(i + 1, j-1).getSpotColor().equals(opp_color)) {
							i++;
							j--;
						} 
					}
				}
			}
		}
		return false;
	}
	
	
	 

	public boolean gameOver(JSpotBoard spot) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(spot.getSpotAt(i, j).isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	private void score() {
		for(Spot s : _board) {
			if(s.getSpotColor() == Color.BLACK) {
				numBlack++;
			} else {
				numWhite++;
			}
		}
	}

	@Override
	public void spotClicked(Spot spot) {
		if (over) {
			return;
		}

		String player_name = null;
		String next_player_name = null;
		Color player_color = null;

		if (_next_to_play == Player.WHITE) {
			player_color = Color.WHITE;
			player_name = "White";
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "Black";
			next_player_name = "White";
			_next_to_play = Player.WHITE;			
		}

		if(gameOver(_board)) {
			over = true;
			score();
		} else {
			_message.setText(next_player_name + "'s turn");
		}

		/*if(isLegalMove(spot) == false) {
			over = true;
			score();
		}
		*/
		if(over) {
			if(numWhite > numBlack) {
				_message.setText("White wins " + numWhite + "-" + numBlack);
			} else if(numBlack > numWhite) {
				_message.setText("Blackwins " + numBlack + "-" + numWhite);
			} else {
				_message.setText("Draw.");
			}
		}
		

		int x = spot.getSpotX();
		int y = spot.getSpotY();

		
		spot.setSpotColor(player_color);
		spot.toggleSpot();
		
		if(isLegalMove(spot) && spot.isEmpty()) {
			int x_2 = spot_2.getSpotX();
			int y_2 = spot_2.getSpotY();
			
			

			if(x_2 >= x && y_2 >= y) {
				while(x_2 >= x && y_2 >= y) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					
					x_2--;
					y_2--;
				}
			} else if(x_2 >= x && y_2 <= y) {
				while(x_2 >= x && y_2 <= y) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					x_2--;
					y_2++;
				}
			} else if(x_2 <= x && y_2 <= y) {
				while(x_2 <= x && y_2 <= y) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).toggleSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					x_2++;
					y_2++;
				}

			} else if(x_2 < x && y_2 > y) {
				while(x_2 < x && y_2 > y) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).toggleSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					x_2++;
					y_2--;
				}

			}else if(x_2 > x ) {
				while(x_2 > x) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).toggleSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					x_2--;
				}

			} else if(x_2 < x ) {
				while(x_2 < x) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).toggleSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					x_2++;
				}

			} else if(y_2 > x ) {
				while(y_2 > x) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).toggleSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					y_2--;
				}

			} else if(y_2 < x ) {
				while(y_2 < x) {
					_board.getSpotAt(x_2, y_2).clearSpot();
					_board.getSpotAt(x_2, y_2).setSpotColor(player_color);
					_board.getSpotAt(x_2, y_2).toggleSpot();
					y_2++;
				}

			}
		}		
	}


	@Override
	public void spotEntered(Spot spot) {
		if (over) {
			return;
		}
		if(isLegalMove(spot)) {
			spot.highlightSpot();
		}
	}

	@Override
	public void spotExited(Spot spot) {
		spot.unhighlightSpot();

	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		resetGame();
	}
}
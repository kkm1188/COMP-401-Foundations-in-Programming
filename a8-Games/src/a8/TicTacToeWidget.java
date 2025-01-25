package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToeWidget extends JPanel implements ActionListener, SpotListener {
	
	
	private enum Player {WHITE, BLACK};
	
	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Color _bg;  /* Needed to reset the background of the secret spot. */
	private Player _next_to_play;
	
	public TicTacToeWidget() {
		
		/* Create SpotBoard and message label. */
		
		_board = new JSpotBoard(3,3);
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
		
		_board.getSpotAt(0,0).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(0,1).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(0,2).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(1,0).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(1,1).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(1,2).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(2,0).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(2,1).setBackground(Color.LIGHT_GRAY);
		_board.getSpotAt(2,2).setBackground(Color.LIGHT_GRAY);
		
		
		/* Reset game. */
		resetGame();
	}

	/* resetGame
	 * 
	 * Resets the game by clearing all the spots on the board,
	 * picking a new secret spot, resetting game status fields, 
	 * and displaying start message.
	 * 
	 */

	private void resetGame() {
		/* Clear all spots on board. Uses the fact that SpotBoard
		 * implements Iterable<Spot> to do this in a for-each loop.
		 */

		for (Spot s : _board) {
			s.clearSpot();
		}
		/* Reset game won and next to play fields */
		_game_won = false;
		_next_to_play = Player.WHITE;		
		
		/* Display game start message. */
		
		_message.setText("Welcome to TicTacToe. White to play");
	}
	private boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if(_board.getSpotAt(i,0).getSpotColor() == _board.getSpotAt(i,1).getSpotColor()
				&& _board.getSpotAt(i,1).getSpotColor() == _board.getSpotAt(i,2).getSpotColor()
				&& !_board.getSpotAt(i,0).isEmpty() && !_board.getSpotAt(i,1).isEmpty()
				&& !_board.getSpotAt(i,2).isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if(_board.getSpotAt(0,i).getSpotColor() == _board.getSpotAt(1,i).getSpotColor()
				&& _board.getSpotAt(1,i).getSpotColor() == _board.getSpotAt(2,i).getSpotColor() &&
				!_board.getSpotAt(0,i).isEmpty() && !_board.getSpotAt(1,i).isEmpty()
				&& !_board.getSpotAt(2,i).isEmpty()) {
				return true;
			}
		}
		return false;
	}
	private boolean checkDiagonals() {
			if((_board.getSpotAt(0,0).getSpotColor() == _board.getSpotAt(1,1).getSpotColor() 
				&& _board.getSpotAt(1,1).getSpotColor() == _board.getSpotAt(2,2).getSpotColor() 
				&& !_board.getSpotAt(0,0).isEmpty() && !_board.getSpotAt(1,1).isEmpty()
				&& !_board.getSpotAt(2,2).isEmpty()) ||
				(_board.getSpotAt(0,2).getSpotColor() == _board.getSpotAt(1,1).getSpotColor() 
				&& _board.getSpotAt(1,1).getSpotColor() == _board.getSpotAt(2,0).getSpotColor() 
				&& !_board.getSpotAt(0,2).isEmpty() && !_board.getSpotAt(1,1).isEmpty()
				&& !_board.getSpotAt(2,0).isEmpty())) {
				return true;
			}
		return false;
	}
	private boolean checkDraw() {
			if(!_board.getSpotAt(0,0).isEmpty() && !_board.getSpotAt(0,1).isEmpty() && !_board.getSpotAt(0,2).isEmpty() 
				&& !_board.getSpotAt(1,0).isEmpty() && !_board.getSpotAt(1,1).isEmpty() && !_board.getSpotAt(1,2).isEmpty()
				&& !_board.getSpotAt(2,0).isEmpty() && !_board.getSpotAt(2,1).isEmpty() && !_board.getSpotAt(2,2).isEmpty()) {
					return true;
			}
		return false;
	}
	
	@Override
	public void spotClicked(Spot spot) {
		/* If game already won, do nothing. */
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
				
		
		/* Set color of spot clicked and toggle. */
		spot.setSpotColor(player_color);
		spot.toggleSpot();
		
		
		if (checkRows() || checkColumns() || checkDiagonals()) {
			_game_won = true;
		}
		
		if(_game_won) {
			_message.setText(player_name + " wins!");
			
			} else if(checkDraw()) {
				_message.setText("Draw.");
				
				} else {
					_message.setText(next_player_name + " to play.");
			}
	}
		

	@Override
	public void spotEntered(Spot spot) {
		if (_game_won) {
			return;
		}
		if(spot.isEmpty() && !(_game_won)) {
			spot.highlightSpot();
		}
	}

	@Override
	public void spotExited(Spot spot) {
		/* Unhighlight spot. */
		
		spot.unhighlightSpot();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		resetGame();
	}
}
	

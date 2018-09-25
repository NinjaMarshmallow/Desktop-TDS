package engine.behaviors;

import java.util.List;

import engine.entities.mobs.Player;

public interface PlayerObserver {
	
	public void notifyOfPlayers(List<Player> players);

}

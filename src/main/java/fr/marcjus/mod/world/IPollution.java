package fr.marcjus.mod.world;

@Deprecated
public interface IPollution {
	
	public default void removePollution(int amount){
		addPollution(-amount);
	}
	
	public default void addPollution(int amount){
		setPollution(this.getPollution() + amount);
	}
	
	public void setPollution(int polution);
	
	public int getPollution();

}

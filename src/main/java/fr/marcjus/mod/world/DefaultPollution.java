package fr.marcjus.mod.world;

public class DefaultPollution implements IPollution{
	
	protected int pollution = 0;
	
	public DefaultPollution() {
		this.pollution = 0;
	}

	@Override
	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	@Override
	public int getPollution() {
		return pollution;
	}

}

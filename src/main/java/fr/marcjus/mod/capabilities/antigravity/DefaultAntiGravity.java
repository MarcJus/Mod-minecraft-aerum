package fr.marcjus.mod.capabilities.antigravity;

public class DefaultAntiGravity implements IAntiGravity{
	
	protected boolean gravity = true;

	@Override
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	@Override
	public boolean hasGravity() {
		return gravity;
	}

}

package common;

public abstract class Sac{
	
	private Tuile[] sac;

	public abstract void melange();
	public abstract Tuile[] getSac();
	public abstract Tuile getSac(int i);
}
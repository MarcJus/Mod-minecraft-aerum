package fr.marcjus.mod.utils;

import net.minecraft.util.text.TextComponentString;

public class StringToComponent{
	
	private StringToComponent(){}
	
	public static TextComponentString convert(String t){
		return new TextComponentString(t.replaceAll("§", "\247"));
	}

}

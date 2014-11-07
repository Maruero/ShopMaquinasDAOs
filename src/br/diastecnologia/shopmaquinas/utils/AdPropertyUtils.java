package br.diastecnologia.shopmaquinas.utils;

import java.util.List;

import br.diastecnologia.shopmaquinas.bean.AdProperty;
import br.diastecnologia.shopmaquinas.bean.AdPropertyValue;
import br.diastecnologia.shopmaquinas.dao.Dao;
import br.diastecnologia.shopmaquinas.enums.AdProperties;


public class AdPropertyUtils {

	private static Dao dao = new Dao();
	private static AdPropertyValue highlightedPropery;
	private static AdProperty imageProperty;
	
	static{
		List<AdProperty> props = dao.adProperties().toList();
		
		AdProperty prop = props.stream().filter( p-> p.getName().equals( AdProperties.HIGHLIGHTED.toString() )).findFirst().get();
		highlightedPropery = new AdPropertyValue();
		highlightedPropery.setAdProperty( prop );
		highlightedPropery.setAdPropertyID( prop.getAdPropertyID() );
		highlightedPropery.setValue( "true" );
		
		imageProperty = props.stream().filter( p-> p.getName().equals( AdProperties.IMAGE.toString() )).findFirst().get();
	}
	
	public static AdPropertyValue getHighlightProperty(){
		return highlightedPropery;
	}
	
	public static AdProperty getImageProperty(){
		return imageProperty;
	}
	
	
}
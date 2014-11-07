package br.diastecnologia.shopmaquinas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jinq.orm.stream.JinqStream;

import br.diastecnologia.shopmaquinas.bean.Ad;
import br.diastecnologia.shopmaquinas.bean.AdPropertyValue;
import br.diastecnologia.shopmaquinas.utils.AdPropertyUtils;

public class AdDao extends Dao {
		
	public List<Ad> listAds( List<AdPropertyValue> props , int pageNumber ){
		
		long skip = (pageNumber -1) * adPageSize;
		JinqStream<Ad> stream = ads();
		if( props != null ){
			for( AdPropertyValue prop : props ){
				stream = stream.where( a -> a.getAdPropertyValues().stream().filter( 
						p-> p.getAdPropertyID() == prop.getAdPropertyID() 
						&& p.getValue().equals( prop.getValue() )
					).count() > 0
				);
			}
		}
		
		stream = stream.skip(skip).limit(adPageSize);
		return stream.toList();
	}
	
	public Ad getAd( int adID ){
		Optional<Ad> ad = ads().filter( a->a.getAdID() == adID ).findFirst();
		if( ad.isPresent() ){
			return ad.get();
		}else{
			return null;
		}
	}
	
	public Ad saveAd( Ad ad, List<String> images ) throws Exception{
		if( ad.getAdPropertyValues() == null ){
			ad.setAdPropertyValues(new ArrayList<AdPropertyValue>());
		}
		if( images != null ){
			for( String image : images ){
				AdPropertyValue prop = new AdPropertyValue();
				prop.setAdProperty( AdPropertyUtils.getImageProperty() );
				prop.setAdPropertyID( prop.getAdProperty().getAdPropertyID() );
				prop.setValue( image );
			}
		}
	
		
		try{
			this.beginTransaction();
			if( ad.getAdID() > 0 ){
				em.merge(ad);
			}else{
				em.persist(ad);
			}
			this.commitTransaction();
		}catch(Exception ex){
			this.rollbackTransaction();
			throw new Exception(ex);
		}
		
		return ad;
	}
	
}
